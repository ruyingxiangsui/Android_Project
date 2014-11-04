package lee.activity;

import lee.engine.MsgEngine;
import lee.message.R;
import lee.util.RecordUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MsgRecordActivity extends Activity {

	private ListView listview;

	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_record);

		listview = (ListView) findViewById(R.id.list_msg_record);
		adapter = new SimpleCursorAdapter(this, R.layout.record_item,
				RecordUtil.getCursor(this), new String[] {
						MsgEngine.RECORD_PERSON, MsgEngine.RECORD_BODY,
						MsgEngine.RECORD_DATE }, new int[] { R.id.record_name,
						R.id.record_content, R.id.record_date });

		listview.setAdapter(adapter);

	}

	public void return_back(View v){
		MsgRecordActivity.this.finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.msg_record, menu);
		return true;
	}

}
