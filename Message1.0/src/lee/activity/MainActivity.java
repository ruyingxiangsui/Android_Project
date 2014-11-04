package lee.activity;

import java.util.ArrayList;

import lee.engine.MsgEngine;
import lee.message.R;
import lee.task.SendMsgAsyncTask;
import lee.util.ContactsUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import bean.Msg;
import bean.MyContacts;

public class MainActivity extends Activity {

	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.msg_content);
			
	}

	public void template(View v) {
		Intent i = new Intent(MainActivity.this, TemplateActivity.class);
		startActivityForResult(i, MsgEngine.REQUEST_CODE_FOR_TEMPLATE);
	}

	public void preview(View v) {
		
		if(ContactsUtil.selectedList.size()==0){
			Toast.makeText(this, getResources().getString(R.string.contacts_cannot_be_null), Toast.LENGTH_SHORT).show();
			return;
		}
		if(editText.getText().toString()==null || editText.getText().toString().trim().equals("")){
			Toast.makeText(this, getResources().getString(R.string.content_cannot_be_null), Toast.LENGTH_SHORT).show();
			return;
		}
		Intent i = new Intent(this,PreviewActivity.class);
		i.putExtra(MsgEngine.CONTENT, editText.getText().toString().trim());
		startActivityForResult(i, MsgEngine.REQUEST_CODE_FOR_PREVIEW);
		
		
	}

	@SuppressWarnings("unchecked")
	public void send(View v) {

		if (ContactsUtil.getSelectContact().size() == 0) {
			Toast.makeText(MainActivity.this,
					getString(R.string.contacts_cannot_be_null),
					Toast.LENGTH_SHORT).show();
			return;
		}

		String sendContent = editText.getText().toString().trim();

		if (sendContent == null || sendContent.equals("")) {
			Toast.makeText(MainActivity.this,
					getResources().getString(R.string.content_cannot_be_null),
					Toast.LENGTH_SHORT).show();
			return;
			
		}

		ArrayList<Msg> msgs = new ArrayList<Msg>();
		
		for(MyContacts each:ContactsUtil.getSelectContact()){
			String toWhom = each.getName();
			String toPhoneNum = each.getPhone();
			Msg msg = new Msg();
			msg.setToWhom(toWhom);
			msg.setMsgContent(sendContent);
			msg.setPhoneNumber(toPhoneNum);
			msgs.add(msg);
		}
		
		SendMsgAsyncTask task = new SendMsgAsyncTask(MainActivity.this);
		task.execute(msgs);
		Toast.makeText(MainActivity.this, getString(R.string.sending), Toast.LENGTH_LONG).show();
		editText.setText("");
		ContactsUtil.clearSelectedList();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_new:
			Intent i1 = new Intent(MainActivity.this, ContactsActivity.class);
			startActivityForResult(i1, MsgEngine.REQUEST_CODE_FOR_CONTACTS);
			return true;
		case R.id.menu_record_list:
			/*Intent i2 = new Intent(MainActivity.this,MsgRecordActivity.class);
			startActivity(i2);*/
			Intent intent = new Intent(); 
			intent.setAction(Intent.ACTION_MAIN); 
			intent.setType("vnd.android-dir/mms-sms"); 
            startActivity(intent); 
			
            return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == MsgEngine.REQUEST_CODE_FOR_CONTACTS) {
			if (resultCode == RESULT_OK) {
				// doSomething()
			}
		} else if (requestCode == MsgEngine.REQUEST_CODE_FOR_TEMPLATE) {
			if (resultCode == RESULT_OK) {
				editText.setText(editText.getText().toString()
						+ data.getStringExtra(MsgEngine.CONTENT));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
