package ui.wrapper;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.factory.MeasureFactory;
import ui.factory.WrapperFactory;
import ui.view.CustomListView;
import android.view.View;
import config.ViewConfig;

public class ListWrapper extends Wrapper {

	public ListWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		super(activity, parent, config);
	}
	
	@Override
	public void createAndLayoutAndAddWrappers() {
		view = new CustomListView(activity, this);
		super.createAndLayoutAndAddWrappers();
	}

	public View createRow() {
		ListRowWrapper row = (ListRowWrapper)new WrapperFactory().createAndInitWrapper(activity, null, config.rowId);
		row.getView().setLayoutParams(new android.widget.AbsListView.LayoutParams(MeasureFactory.getMeasuredWidth(row), MeasureFactory.getMeasuredHeight(row)));
		return row.getView();
	}

	@Override
	public void finializeWrappers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateData(ArrayList<HashMap<String, Object>> data) {
		getListView().getBaseAdapter().updateData(data);
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}
	
	public CustomListView getListView(){
		return (CustomListView)view;
	}


}
