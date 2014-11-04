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

public class EditTemplateActivity extends Activity {

	private EditText et_title;
	private EditText et_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_edit_template);
		String old_title = getIntent().getStringExtra(MsgEngine.TITLE);
		String old_content = getIntent().getStringExtra(MsgEngine.CONTENT);

		et_title = (EditText) findViewById(R.id.edit_template_title);
		et_content = (EditText) findViewById(R.id.edit_template_content);

		et_title.setText(old_title);
		et_content.setText(old_content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_template, menu);
		return true;
	}

	public void editConfirm(View v) {
		String title = et_title.getText().toString();
		String content = et_content.getText().toString();
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

		EditTemplateActivity.this.setResult(RESULT_OK, i);
		EditTemplateActivity.this.finish();
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

	public void editCancel(View v) {
		EditTemplateActivity.this.setResult(RESULT_CANCELED);
		EditTemplateActivity.this.finish();
	}

}
