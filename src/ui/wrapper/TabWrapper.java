package ui.wrapper;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.factory.WrapperFactory;
import ui.view.TabButtonView;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * A TabWrapper(s) Layout holds TabButtonWrapper(s).
 * They also hold references to the ContainerWrapper(s) that map to each tab
 *
 */
public class TabWrapper extends ContainerWrapper implements OnClickListener {
	
	private ContainerWrapper currentContainerWrapper;
	private int currentTabIndex;
	private ArrayList<TabButtonWrapper> tabs;
	
	public TabWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		super(activity, parent, id);
		tabs = new ArrayList<TabButtonWrapper>();
	}
	
	@Override
	public void createWrappers() {
		super.createWrappers();
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
	public void finializeWrappers(){
		currentTabIndex = 0;
		currentContainerWrapper = getCurrentTargetContainerWrapper();
		getCurrentTabWrapper().setContainerWrapper(currentContainerWrapper);
		super.finializeWrappers();
	}

	@Override
	public void updateData() {}

	@Override
	public void setText(String text) {}

	public void onClick(View v) {
		setActiveTab((TabButtonWrapper)((TabButtonView)v).getWrapper());
	}

	public void setActiveTab(TabButtonWrapper tab) {
		int index = tab.getTabIndex();
		ContainerWrapper newWrapper = tab.getContainerWrapper();
		
		if(newWrapper == null)
			newWrapper = initContainerWrapper(index);
		else if(newWrapper == currentContainerWrapper)
			return;
		
		activity.replaceFragment(newWrapper, currentContainerWrapper);
		
		parentWrapper.replaceChildWrapper(currentContainerWrapper, newWrapper);
		currentContainerWrapper = newWrapper; 
		
		activity.relayout(false);
	}

	private ContainerWrapper initContainerWrapper(int index) {
		TabButtonWrapper tab = getTabWrapper(index);
		
		ContainerWrapper wrapper = (ContainerWrapper) new WrapperFactory().createAndInitWrapper(activity, parentWrapper, targetWrapperIds.get(index));
		
		tab.setContainerWrapper(wrapper);
		
		return wrapper;
	}
	
	protected TabButtonWrapper getCurrentTabWrapper(){
		return getTabWrapper(currentTabIndex);
	}
	
	protected TabButtonWrapper getTabWrapper(int index) {
		return tabs.get(index);
	}
	
	protected ContainerWrapper getCurrentTargetContainerWrapper() {
		return (ContainerWrapper)getActivity().getWrapperById(targetWrapperIds.get(currentTabIndex));
	}


}
