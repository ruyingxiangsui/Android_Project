package lee.activity;

import lee.adapter.AdapterPreview;
import lee.engine.MsgEngine;
import lee.message.R;
import lee.util.ContactsUtil;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class PreviewActivity extends Activity {

	private ListView listView;
	private AdapterPreview adapter;
	private String content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_preview);

		listView = (ListView) findViewById(R.id.list_preview);
		content = getIntent().getStringExtra(MsgEngine.CONTENT);
		adapter = new AdapterPreview(this, ContactsUtil.selectedList, content);
		listView.setAdapter(adapter);
	}
	
	public void confirm(View v){
		this.finish();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
