package lee.engine;

public class MsgEngine {
	// 联系人姓名和号码
	public static final String CONTACT_NAME = "contact_name";
	public static final String CONTACT_PHONE_NUM = "contact_phone_num";

	// Activity的请求标号
	public static final int REQUEST_CODE_FOR_CONTACTS = 0;
	public static final int REQUEST_CODE_FOR_TEMPLATE = 1;
	public static final int REQUEST_CODE_FOR_EDIT_TEMPLATE = 2;
	public static final int REQUEST_CODE_FOR_ADD_TEMPLATE = 3;
	public static final int REQUEST_CODE_FOR_PREVIEW = 4;

	// database中的一些常量
	public static final String DATABASE_NAME = "lee.message.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_TEMPLATE = "table_message";
	public static final String COLUMN_TITLE = "column_title";
	public static final String COLUMN_CONTENT = "column_content";

	public static final String TITLE = "title";
	public static final String CONTENT = "content";

	public static final String SENT_SMS_ACTION = "SENT_SMS_ACTION";
	public static final String SENT_SMS_ACTION_EXTRA_KEY = "SENT_SMS_ACTION_EXTRA_KEY";

	public static final String LEFT_WORD_OF_CONTRACT1 = "";

	public static final String RIGHT_WORD_OF_CONTRACTS1 = ",";
	public static final String RIGHT_WORD_OF_CONTRACTS2 = "\n";
	public static final String RIGHT_WORD_OF_CONTRACTS3 = ":";

	public static final int RANDOM_NUMBER = Integer.MAX_VALUE;

	public static final int MAX_SEND_MESSAGE_COUNT = 99;

	public static final String SMS_URI_ALL = "content://sms/";
	public static final String SMS_URI_INBOX = "content://sms/inbox";
	public static final String SMS_URI_SEND = "content://sms/sent";
	public static final String SMS_URI_DRAFT = "content://sms/draft";
	
	public static final String RECORD_ID = "_id";
	public static final String RECORD_PERSON = "person";
	public static final String RECORD_BODY = "body";
	public static final String RECORD_DATE = "date";
	
	
	public static final String RECORD_SORT = "date desc";
	

}
