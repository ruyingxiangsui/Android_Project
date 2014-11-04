package lee.activity;

import java.util.ArrayList;

import lee.adapter.AdapterContacts;
import lee.message.R;
import lee.myview.SideBar;
import lee.myview.SideBar.OnTouchingLetterChangedListener;
import lee.util.ContactsUtil;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import bean.MyContacts;

public class ContactsActivity extends Activity implements OnQueryTextListener {

	private ArrayList<MyContacts> data;

	private ListView listview;

	private AdapterContacts adapter;

	private SearchView searchView;

	private SideBar sideBar;
	private TextView showLetteView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_contacts);

		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setDisplayShowTitleEnabled(true);
		listview = (ListView) findViewById(R.id.list_contacts);

		sideBar = (SideBar) findViewById(R.id.sidebar);
		showLetteView = (TextView) findViewById(R.id.showLetterView);
		sideBar.setShowView(showLetteView);

		data = ContactsUtil.getContacts(this);
		adapter = new AdapterContacts(this, data);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				searchView.clearFocus();
				CheckBox check = (CheckBox) view.findViewById(R.id.checkbox);
				if (check.isChecked()) {
					check.setChecked(false);
				} else {
					check.setChecked(true);
				}
			}
		});

		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String letter) {
				// 该字母首次出现的位置
				searchView.clearFocus();
				int position = adapter.getPositionForSection(letter.charAt(0));
				if (position != -1) {
					listview.setSelection(position);
				}else{
					listview.setSelectionAfterHeaderView();
				}

			}
		});

	}

	public void selectCancel(View v) {
		ContactsUtil.clearSelectedList();
		this.finish();
	}

	public void selectConfirm(View v) {
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_contacts, menu);

		searchView = (SearchView) menu.findItem(R.id.menu_search_contact)
				.getActionView();
		searchView.setOnQueryTextListener(this);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			this.finish();
			return true;

		case R.id.menu_cancel_all:
			ContactsUtil.clearSelectedList();
			adapter.notifyDataSetChanged();
			return true;

		case R.id.menu_select_all_contacts:
			ContactsUtil.selectAll();
			adapter.notifyDataSetChanged();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onQueryTextChange(String str) {
		if (str == null || str.equals("")) {
			data.clear();
			data.addAll(ContactsUtil.getContacts(this));
			adapter.notifyDataSetChanged();
		} else {
			data.clear();
			for (MyContacts mc : ContactsUtil.getContacts(this)) {
				String name = mc.getName();
				if (name.indexOf(str.toString()) != -1
						|| mc.getName().startsWith(str)) {
					data.add(mc);
				}
			}
			adapter.notifyDataSetChanged();
		}
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {

		return true;
	}

}
