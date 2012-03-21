package ui.wrapper;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.factory.MeasureFactory;
import ui.factory.WrapperFactory;
import ui.view.CustomListView;
import android.view.View;
import config.WrapperConfig;

public class ListWrapper extends Wrapper {

	public ListWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}
	
	@Override
	public Wrapper createWrapper() {
		view = new CustomListView(activity, this);
		return super.createWrapper();
	}

	public View createRow() {
		ListRowWrapper row = (ListRowWrapper)new WrapperFactory().buildWrapper(activity, null, config.rowId);
		row.getView().setLayoutParams(new android.widget.AbsListView.LayoutParams(MeasureFactory.getMeasuredWidth(row), MeasureFactory.getMeasuredHeight(row)));
		return row.getView();
	}
	
	@Override
	public void layoutWrapper() {}

	@Override
	public void finishLayoutWrapper() {}
	
	@Override
	public void finializeWrapper() {}

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
