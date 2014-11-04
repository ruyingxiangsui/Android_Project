package lee.activity;

import lee.engine.MsgEngine;
import lee.message.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTemplateActivity extends Activity {

	private EditText addTitle;
	private EditText addContnet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_add_template);

		addTitle = (EditText) findViewById(R.id.add_template_title);
		addContnet = (EditText) findViewById(R.id.add_template_content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_template, menu);
		return true;
	}

	public void addCancel(View v) {
		setResult(RESULT_CANCELED);
		this.finish();
	}

	public void addConfirm(View v) {
		String title = addTitle.getText().toString();
		String content = addContnet.getText().toString();
		if (title == null || content == null || title.equals("")
				|| content.equals("")) {
			Toast.makeText(this,
					getResources().getText(R.string.title_content_cannot_null),
					Toast.LENGTH_SHORT).show();
			return;
		}

		Intent i = new Intent();
		i.putExtra(MsgEngine.TITLE, title);
		i.putExtra(MsgEngine.CONTENT, content);
		setResult(RESULT_OK, i);
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
