package ui.wrapper;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.factory.WrapperFactory;
import config.WrapperConfig;

/**
 * A TabWrapper(s) Layout holds TabButtonWrapper(s).
 * They also hold references to the ContainerWrapper(s) that map to each tab
 *
 */
public class TabWrapper extends FragmentContainerWrapper {
	
	private int currentTabIndex;
	private ArrayList<TabButtonWrapper> tabs;
	
	public TabWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
		tabs = new ArrayList<TabButtonWrapper>();
	}
	
	@Override
	public void createChildWrappers() {
		super.createChildWrappers();
		int tabCount = 0;
		for(Wrapper childWrapper : childWrappers){
			if(childWrapper instanceof TabButtonWrapper){
				TabButtonWrapper tab = (TabButtonWrapper)childWrapper;
				tab.setTabIndex(tabCount);
				tabs.add(tab);
				tabCount++;
			}
		}
	}
	
	@Override
	public void finializeWrapper(){
		setCurrentTab(0);
		super.finializeWrapper();
	}
	
	private void setCurrentTab(int index) {
		getCurrentTabButtonWrapper().getButtonView().setSelected(false);
		currentTabIndex = index;
		getCurrentTabButtonWrapper().getButtonView().setSelected(true);
		getCurrentTabButtonWrapper().setContainerWrapper(getCurrentTargetContainerWrapper());
	}

	@Override
	public void setText(String text) {}

	public void setActiveTab(TabButtonWrapper tab) {
		int index = tab.getTabIndex();
		FragmentContainerWrapper newWrapper = tab.getContainerWrapper();
		
		if(newWrapper == null)
			newWrapper = initContainerWrapper(index);
		else if(newWrapper == getCurrentTargetContainerWrapper())
			return;
		
		boolean success = activity.replaceFragment(newWrapper, getCurrentTargetContainerWrapper());
		
		if(success){
			if(parentWrapper instanceof FragmentContainerWrapper)
				((FragmentContainerWrapper)parentWrapper).replaceChildWrapper(getCurrentTargetContainerWrapper(), newWrapper);
			setCurrentTab(index);
			activity.relayout(true);
		}
	}

	private FragmentContainerWrapper initContainerWrapper(int index) {
		TabButtonWrapper tab = getTabButtonWrapper(index);
		
		FragmentContainerWrapper wrapper = (FragmentContainerWrapper) new WrapperFactory().buildWrapper(activity, parentWrapper, config.targetWrapperIds.get(index));
		
		tab.setContainerWrapper(wrapper);
		
		return wrapper;
	}
	
	protected TabButtonWrapper getCurrentTabButtonWrapper(){
		return getTabButtonWrapper(currentTabIndex);
	}
	
	protected TabButtonWrapper getTabButtonWrapper(int index) {
		return tabs.get(index);
	}
	
	protected FragmentContainerWrapper getCurrentTargetContainerWrapper() {
		return (FragmentContainerWrapper)getActivity().getWrapperById(config.targetWrapperIds.get(currentTabIndex));
	}


}
