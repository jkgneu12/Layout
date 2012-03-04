package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.TabButtonView;
import android.view.View;
import config.Config;

public class TabButtonWrapper extends ButtonWrapper {

	
	private int tabIndex;
	private ContainerWrapper containerWrapper;

	public TabButtonWrapper(BaseActivity activity, ContainerWrapper parent, Config config) {
		super(activity, parent, config);
	}

	@Override
	public void createAndLayoutAndAddWrappers() {
		view = new TabButtonView(activity, this, getTabWrapper());
		super.createAndLayoutAndAddWrappers();
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

