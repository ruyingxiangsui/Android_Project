package lee.dbhelper;

import lee.engine.MsgEngine;
import lee.message.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessageDBHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_TABLE = "CREATE TABLE "
			+ MsgEngine.TABLE_TEMPLATE + " (" + MsgEngine.COLUMN_TITLE
			+ TEXT_TYPE + COMMA_SEP + MsgEngine.COLUMN_CONTENT + TEXT_TYPE
			+ " )";

	private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
			+ MsgEngine.TABLE_TEMPLATE;

	private Context context;

	public MessageDBHelper(Context context) {
		super(context, MsgEngine.DATABASE_NAME, null,
				MsgEngine.DATABASE_VERSION);

		this.context = context;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE);
		initData(db);
	}

	public int initData(SQLiteDatabase db) {
		String[] titles = context.getResources().getStringArray(
				R.array.init_templetes_title);
		String[] contents = context.getResources().getStringArray(
				R.array.init_templetes_content);
		for (int i = 0; i < titles.length; i++) {
			db.execSQL("insert into " + MsgEngine.TABLE_TEMPLATE
					+ " values(?,?)", new String[] { titles[i], contents[i] });
		}
		return 0;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_TABLE);
		onCreate(db);
	}

}
