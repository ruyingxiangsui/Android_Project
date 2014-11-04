package lee.util;

import lee.engine.MsgEngine;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class RecordUtil {

	/**
	 * 
	 * @return ·µ»Ø ¶ÌÐÅCursor
	 * 
	 * */
	public static Cursor getCursor(Context context){
		
		 ContentResolver cr = context.getContentResolver();   
	        String[] projection = new String[]{MsgEngine.RECORD_ID,  MsgEngine.RECORD_PERSON,    
	                MsgEngine.RECORD_BODY, MsgEngine.RECORD_DATE};   
	        Uri uri = Uri.parse(MsgEngine.SMS_URI_SEND);   
	        Cursor cur = cr.query(uri, projection, null, null, MsgEngine.RECORD_SORT);  
		
		return cur;
	}
	
}
