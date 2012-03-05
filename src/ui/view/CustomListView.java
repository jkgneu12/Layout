package ui.view;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.adapter.BaseAdapter;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.ListWrapper;
import ui.wrapper.Wrapper;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

public class CustomListView extends ListView {

	private Wrapper wrapper;

	public CustomListView(BaseActivity activity, Wrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
		
		setBackgroundColor(Color.GREEN);
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<String, Object>();
		row1.put("13", "a");
		row1.put("11", "b");
		row1.put("12", "c");
		HashMap<String, Object> row2 = new HashMap<String, Object>();
		row2.put("13", "d");
		row2.put("11", "e");
		row2.put("12", "f");
		HashMap<String, Object> row3 = new HashMap<String, Object>();
		row3.put("13", "g");
		row3.put("11", "h");
		row3.put("12", "i");
		HashMap<String, Object> row4 = new HashMap<String, Object>();
		row4.put("13", "j");
		row4.put("11", "k");
		row4.put("12", "l");
		HashMap<String, Object> row5 = new HashMap<String, Object>();
		row5.put("13", "m");
		row5.put("11", "n");
		row5.put("12", "o");
		data.add(row1);
		data.add(row2);
		data.add(row3);
		data.add(row4);
		data.add(row5);
		
		setAdapter(new BaseAdapter(activity, (ListWrapper)wrapper, data));
	}
	
	public void layoutWrappers(ContainerWrapper containerWrapper, int maxWidth, int maxHeight) {}

	public void resetLayout() {}
	
	public View self() {
		return this;
	}

	public BaseAdapter getBaseAdapter() {
		return (BaseAdapter)getAdapter();
	}

}
