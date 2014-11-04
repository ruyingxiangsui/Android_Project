package lee.util;

import java.util.ArrayList;

import lee.dbhelper.MessageDBHelper;
import lee.engine.MsgEngine;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bean.Template;

public class DatabaseUtil {

	private MessageDBHelper helper = null;
	private SQLiteDatabase db = null;

	public DatabaseUtil(Context context) {
		helper = new MessageDBHelper(context);
		db = helper.getWritableDatabase();
	}

	public int insert(Template tmp_msg) {

		db.execSQL("insert into " + MsgEngine.TABLE_TEMPLATE + " values(?,?)",
				new String[] { tmp_msg.getTitle(), tmp_msg.getContent() });
		return 0;
	}

	public SQLiteDatabase getReadableDatabase() {
		return helper.getReadableDatabase();
	}

	public boolean contains(Template tmp_msg) {

		return false;
	}

	public void remove(Template tmp_msg) {

		db.execSQL("delete from " + MsgEngine.TABLE_TEMPLATE + " where "
				+ MsgEngine.COLUMN_TITLE + " = ? and "
				+ MsgEngine.COLUMN_CONTENT + " = ?",
				new String[] { tmp_msg.getTitle(), tmp_msg.getContent() });
	}

	public void upDate(Template old_tmp, Template new_tmp) {

		if (old_tmp.getTitle().equals(new_tmp.getTitle())
				&& old_tmp.getContent().equals(new_tmp.getContent())) {
			// Toast.makeText(context, "ПаµИ", Toast.LENGTH_SHORT).show();
			return;
		}

		/*
		 * Toast.makeText( context, old_tmp.getTitle() + " " +
		 * old_tmp.getContent() + "\n" + new_tmp.getTitle() + " " +
		 * new_tmp.getContent(), Toast.LENGTH_SHORT).show();
		 */
		remove(old_tmp);
		insert(new_tmp);
	}

	public ArrayList<Template> query() {
		ArrayList<Template> tmps = new ArrayList<Template>();
		Cursor c = getCursor();

		while (c.moveToNext()) {
			Template tmp = new Template();
			String title = c
					.getString(c.getColumnIndex(MsgEngine.COLUMN_TITLE));
			String content = c.getString(c
					.getColumnIndex(MsgEngine.COLUMN_CONTENT));
			tmp.setTitle(title);
			tmp.setContent(content);

			tmps.add(tmp);
		}

		return tmps;
	}

	public Cursor getCursor() {
		Cursor c = getReadableDatabase().rawQuery(
				"select * from " + MsgEngine.TABLE_TEMPLATE, null);
		return c;
	}

	public void DBClose() {
		db.close();
	}

}
