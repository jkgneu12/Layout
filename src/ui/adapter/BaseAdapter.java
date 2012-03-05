package ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ui.layout.Layout;
import ui.wrapper.ListWrapper;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BaseAdapter extends ArrayAdapter<HashMap<String, Object>> { 

	protected List<HashMap<String, Object>> items;
	private ListWrapper wrapper;
	
	public BaseAdapter(Context ctx, ListWrapper wrapper, ArrayList<HashMap<String, Object>> results) {
		super(ctx, 0, results);
		this.items = results;
		this.wrapper = wrapper;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		HashMap<String, Object> o = items.get(position);
        if (v == null) {
            v = createView(position, parent, o);
        }
        return initializeView(position, v, o);
	}

	protected View createView(int position, ViewGroup parentView, HashMap<String, Object> data){
		return wrapper.createRow();
	}
	
	protected View initializeView(int position, View v, HashMap<String, Object> data){
		((Layout)v).getWrapper().updateData(data);
		return v;
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	public void updateData(ArrayList<HashMap<String, Object>> data) {
		this.items = data;
		notifyDataSetChanged();
	}

}
