package lee.activity;

import java.util.ArrayList;

import lee.adapter.AdapterTemplate;
import lee.engine.MsgEngine;
import lee.message.R;
import lee.util.DatabaseUtil;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import bean.Template;

public class TemplateActivity extends Activity {

	private ListView templateList;

	private AdapterTemplate adapter;

	private ArrayList<Template> data;

	private DatabaseUtil dbUtil;

	private Template old;

	private String mTitle = "";

	private String mContent = "";

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_template);
		templateList = (ListView) findViewById(R.id.template_list);
		dbUtil = new DatabaseUtil(this);

		data = dbUtil.query();

		adapter = new AdapterTemplate(this, data);

		templateList.setAdapter(adapter);

		templateList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {

				TextView tv_title = (TextView) view
						.findViewById(R.id.template_title);

				TextView tv_content = (TextView) view
						.findViewById(R.id.template_content);

				/*
				 * Toast.makeText(getApplicationContext(), mTitle + "\n" +
				 * mContent, Toast.LENGTH_SHORT).show();
				 */

				Intent intent = new Intent();// 数据是使用Intent返回

				intent.putExtra(MsgEngine.TITLE, tv_title.getText().toString());// 把返回数据存入Intent
				intent.putExtra(MsgEngine.CONTENT, tv_content.getText()
						.toString());
				TemplateActivity.this.setResult(RESULT_OK, intent);// 设置返回数据

				TemplateActivity.this.finish();// 关闭Activity

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.template, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		case R.id.menu_new_template:
			Intent i = new Intent(this, AddTemplateActivity.class);
			startActivityForResult(i, MsgEngine.REQUEST_CODE_FOR_ADD_TEMPLATE);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent retdata) {
		if (requestCode == MsgEngine.REQUEST_CODE_FOR_EDIT_TEMPLATE) {
			if (resultCode == RESULT_OK) {

				String title = retdata.getStringExtra(MsgEngine.TITLE);
				String content = retdata.getStringExtra(MsgEngine.CONTENT);

				Template new_tmp = new Template(title, content);
				old = new Template(mTitle, mContent);
				dbUtil.upDate(old, new_tmp);

				data.clear();
				data.addAll(dbUtil.query());
				adapter.notifyDataSetChanged();
			}
		} else if (requestCode == MsgEngine.REQUEST_CODE_FOR_ADD_TEMPLATE) {
			if (resultCode == RESULT_OK) {
				String title = retdata.getStringExtra(MsgEngine.TITLE);
				String content = retdata.getStringExtra(MsgEngine.CONTENT);

				Template new_tmp = new Template(title, content);

				dbUtil.insert(new_tmp);

				data.clear();
				data.addAll(dbUtil.query());
				adapter.notifyDataSetChanged();
			}
		}
		super.onActivityResult(requestCode, resultCode, retdata);
	}
}
