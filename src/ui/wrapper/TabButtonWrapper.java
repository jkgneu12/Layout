package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.TabButtonView;
import android.view.View;

public class TabButtonWrapper extends ButtonWrapper {

	
	private int tabIndex;
	private ContainerWrapper containerWrapper;

	public TabButtonWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		super(activity, parent, id);
	}

	@Override
	public void createLayoutAndAddWrappers() {
		view = new TabButtonView(activity, this, getTabWrapper());
		setText(getTitle());
		view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
		super.createLayoutAndAddWrappers();
	}
	
	public TabWrapper getTabWrapper(){
		return (TabWrapper)parentWrapper;
	}

	public void onClick(View v) {
		getTabWrapper().setActiveTab(this);
	}
	
	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int index) {
		tabIndex = index;
	}

	public ContainerWrapper getContainerWrapper() {
		return containerWrapper;
	}
	
	public void setContainerWrapper(ContainerWrapper wrapper) {
		containerWrapper = wrapper;
	}
	
}

