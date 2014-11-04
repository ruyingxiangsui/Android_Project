package lee.adapter;

import java.util.ArrayList;

import lee.engine.MsgEngine;
import lee.message.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import bean.MyContacts;

public class AdapterPreview extends BaseAdapter {

	private ArrayList<MyContacts> list;

	private LayoutInflater inflater;

	private String commonStr;

	public AdapterPreview(Context context,
			ArrayList<MyContacts> list, String commonStr) {
		if (list != null)
			this.list = list;
		else
			this.list = new ArrayList<MyContacts>();

		inflater = LayoutInflater.from(context);
		this.commonStr = commonStr;
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
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ViewHolder holder = null;
		
		
		if (row == null) {
			row = inflater.inflate(R.layout.preview_item, null);
			holder = new ViewHolder(row);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		holder.getTextview().setText(
				MsgEngine.LEFT_WORD_OF_CONTRACT1
						+ list.get(position).getName()
						+ MsgEngine.RIGHT_WORD_OF_CONTRACTS1 + commonStr);

		return row;
	}

	class ViewHolder {
		TextView textview;

		View base;

		public ViewHolder(View base) {
			this.base = base;
		}

		public TextView getTextview() {
			if (textview == null) {
				textview = (TextView) base.findViewById(R.id.preview_item);
				if(textview==null) Log.d("textview", "is null");
			}
			return textview;
		}

	}

}
