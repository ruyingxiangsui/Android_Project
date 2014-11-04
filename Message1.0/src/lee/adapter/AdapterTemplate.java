package lee.adapter;

import java.util.ArrayList;

import lee.activity.EditTemplateActivity;
import lee.activity.TemplateActivity;
import lee.engine.MsgEngine;
import lee.message.R;
import lee.util.DatabaseUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import bean.Template;

@SuppressLint("UseSparseArrays")
public class AdapterTemplate extends BaseAdapter {

	private ArrayList<Template> list;

	private LayoutInflater inflater;

	private TemplateActivity context;
	
	private DatabaseUtil db;

	public AdapterTemplate(TemplateActivity context, ArrayList<Template> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		db = new DatabaseUtil(context);
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
			row = inflater.inflate(R.layout.template_item, null);
			holder = new ViewHolder(row);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		final String str_title=list.get(position).getTitle();
		final String str_content = list.get(position).getContent();
		
		holder.getTitle().setText(str_title);

		holder.getContent().setText(str_content);
		holder.getDelete().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				db.remove(new Template(str_title, str_content));
				list.remove(position);
				AdapterTemplate.this.notifyDataSetChanged();
			}
		});
		
		holder.getEdit().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, EditTemplateActivity.class);
				i.putExtra(MsgEngine.TITLE, str_title);
				i.putExtra(MsgEngine.CONTENT, str_content);
				context.setmTitle(str_title);
				context.setmContent(str_content);
				context.startActivityForResult(i, MsgEngine.REQUEST_CODE_FOR_EDIT_TEMPLATE);
				context.setResult(Activity.RESULT_OK, i);
			}
		});

		return row;
	}

	class ViewHolder {

		View base;
		TextView title;
		TextView content;
		TextView delete;
		TextView edit;

		public ViewHolder(View base) {
			this.base = base;
		}

		public TextView getEdit() {
			if (edit == null) {
				edit = (TextView) base.findViewById(R.id.tmp_edit);
			}
			return edit;
		}

		public View getBase() {
			return base;
		}

		public void setBase(View base) {
			this.base = base;
		}

		public TextView getTitle() {
			if (title == null) {
				title = (TextView) base.findViewById(R.id.template_title);
			}
			return title;
		}

		public TextView getContent() {
			if (content == null) {
				content = (TextView) base.findViewById(R.id.template_content);
			}
			return content;
		}

		public TextView getDelete() {
			if (delete == null) {
				delete = (TextView) base.findViewById(R.id.tmp_delete);
			}
			return delete;
		}

	}

}
