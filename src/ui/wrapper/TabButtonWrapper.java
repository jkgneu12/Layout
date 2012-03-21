package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.TabButtonView;
import android.view.View;
import config.WrapperConfig;

public class TabButtonWrapper extends ButtonWrapper {

	
	private int tabIndex;
	private FragmentContainerWrapper containerWrapper;

	public TabButtonWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}

	@Override
	public Wrapper createWrapper() {
		view = new TabButtonView(activity, this, getTabWrapper());
		return super.createWrapper();
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

	public FragmentContainerWrapper getContainerWrapper() {
		return containerWrapper;
	}
	
	public void setContainerWrapper(FragmentContainerWrapper wrapper) {
		containerWrapper = wrapper;
	}
	
}

