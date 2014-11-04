package lee.util;

import java.util.ArrayList;
import java.util.Collections;

import lee.compare.PinyinComparator;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import bean.MyContacts;

public class ContactsUtil {

	public static ArrayList<MyContacts> allList = new ArrayList<MyContacts>();

	public static ArrayList<MyContacts> selectedList = new ArrayList<MyContacts>();

	public static PinyinComparator pinyinComparator =  new PinyinComparator();
	
	public static ArrayList<MyContacts> getContacts(Context context) {

		if(allList.size()>0)return allList;
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);

		allList = new ArrayList<MyContacts>();
		while (cursor != null && cursor.moveToNext()) {

			MyContacts con = new MyContacts();

			int nameID = cursor.getColumnIndex(Phone.DISPLAY_NAME);
			int numID = cursor.getColumnIndex(Phone.NUMBER);

			String name = cursor.getString(nameID);
			String num = cursor.getString(numID);

			con.setName(name);
			con.setPhone(num);
			con.setSortLetters(PinyinUtil.getPinYin(name));

			allList.add(con);

		}
		/*
		 * Toast.makeText(context, allList.size()+"",
		 * Toast.LENGTH_SHORT).show();
		 */
		
		Collections.sort(allList, new PinyinComparator());
		
		return allList;
	}

	public static ArrayList<MyContacts> getSelectContact() {
		return selectedList;
	}

	public static void addSelectedContact(MyContacts data) {
		if (!selectedList.contains(data))
			selectedList.add(data);
	}

	public static void removeSelectedContact(MyContacts data) {
		selectedList.remove(data);
	}

	/**
	 * 
	 * 注意事项：当ArrayList容器盛放对象类型时，需重写该对象的equals方法
	 * 参考：http://blog.csdn.net/witsmakemen/article/details/7323604
	 * 
	 * @param MyContacts
	 * 
	 * 
	 * */
	public static boolean contains(MyContacts data) {

		return selectedList.contains(data);
	}

	public static void clearSelectedList() {
		selectedList.clear();
	}

	public static void selectAll() {
		selectedList.addAll(allList);
	}
}
