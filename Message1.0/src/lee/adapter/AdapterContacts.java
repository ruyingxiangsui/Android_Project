package lee.adapter;

import java.util.ArrayList;
import java.util.Locale;

import lee.message.R;
import lee.util.ContactsUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import bean.MyContacts;

public class AdapterContacts extends BaseAdapter {

	private ArrayList<MyContacts> list;

	private LayoutInflater inflater;

	public AdapterContacts(Context context,
			ArrayList<MyContacts> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ViewHolder holder = null;
		if (row == null) {
			row = inflater.inflate(R.layout.contacts_item, null);
			holder = new ViewHolder(row);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		holder.getName().setText(
				list.get(position).getName());

		holder.getPhone().setText(
				list.get(position).getPhone());
		
		holder.getCheck().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					
					ContactsUtil.addSelectedContact(list.get(position));
				}else{
					ContactsUtil.removeSelectedContact(list.get(position));
				}
			}
		});
		holder.getCheck().setChecked(ContactsUtil.contains(list.get(position)));
		
		return row;
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar =sortStr.toUpperCase(Locale.US).charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	
	
	
	class ViewHolder {

		View base;
		TextView name;
		TextView phone;
		CheckBox check;

		public ViewHolder(View base) {
			this.base = base;
		}

		public TextView getName() {
			if (name == null) {
				name = (TextView) base.findViewById(R.id.contacts_name);
			}
			return name;
		}

		public TextView getPhone() {
			if (phone == null) {
				phone = (TextView) base.findViewById(R.id.contacts_number);
			}
			return phone;
		}

		public CheckBox getCheck() {
			if (check == null) {
				check = (CheckBox) base.findViewById(R.id.checkbox);
			}
			return check;
		}

	}

}
