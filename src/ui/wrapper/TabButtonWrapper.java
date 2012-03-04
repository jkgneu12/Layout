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
	public void createLayoutAndAddWrappers() {
		view = new TabButtonView(activity, this, getTabWrapper());
		setText(config.title);
		view.setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom);
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

