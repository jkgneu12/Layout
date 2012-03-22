package ui.wrapper;

import ui.activity.BaseActivity;
import ui.fragment.BaseFragment;
import ui.layout.Layout;
import android.support.v4.app.FragmentTransaction;
import config.WrapperConfig;

public class FragmentContainerWrapper extends ContainerWrapper {

	private BaseFragment fragment;
	
	public FragmentContainerWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		this(activity, parent, config, true);
	}
	
	public FragmentContainerWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config, boolean addFragment) {
		super(activity, parent, config);
		if(addFragment)
			activity.addContainerWrapper(this);
	}
	
	/**
	 * FragmentContainerWrapper adds its view to R.id.content
	 */
	public void addViewToLayout(Layout layout) {}
	
	@Override
	public void finishLayoutWrapper(){
		applyOffsetX(0);
		applyOffsetY(0);
	}
	
	public void applyOffsetX(int parentOffsetX){
		if(view != null){
			increaseLeftMargin(parentOffsetX);
			applyChildWrappersOffsetX();
		}
	}

	protected void applyChildWrappersOffsetX() {
		for(Wrapper<?> wrapper : childWrappers){
			wrapper.applyOffsetX(getLeftMargin());
		}
	}
	
	public void applyOffsetY(int parentOffsetY){
		if(view != null){
			increaseTopMargin(parentOffsetY);
			applyChildWrappersOffsetY();
		}
	}

	protected void applyChildWrappersOffsetY() {
		for(Wrapper<?> wrapper : childWrappers){
			wrapper.applyOffsetY(getTopMargin());
		}
	}

	public void initFragments() {
		initFragment();
		for(FragmentContainerWrapper wrapper : childFragmentWrappers)
			wrapper.initFragments();
	}
	
	public synchronized boolean initFragment() {
		if(fragment == null)
			fragment = createFragment();
		FragmentTransaction trans = activity.getSupportFragmentManager().beginTransaction();
		if(activity.getSupportFragmentManager().findFragmentById(fragment.getId()) != null)
			trans.show(fragment);
		else
			trans.add(android.R.id.content, fragment);
		trans.commit();
		return true;
	}
	
	public void hideAllFragments(){
		for(FragmentContainerWrapper wrapper : childFragmentWrappers){
			wrapper.hideFragment();
			wrapper.hideAllFragments();
		}
	}
	
	public synchronized void hideFragment() {
		if(fragment != null){
			FragmentTransaction trans = activity.getSupportFragmentManager().beginTransaction();
			trans.hide(fragment);
			trans.commit();
		}
	}
	
	public BaseFragment createFragment(){
		return new BaseFragment(this);
	}

	public BaseFragment getFragment() {
		return fragment;
	}
	
	
	public void replaceChildWrapper(FragmentContainerWrapper oldWrapper, FragmentContainerWrapper newWrapper) {
		int index = childWrappers.indexOf(oldWrapper);
		childWrappers.remove(index);
		childWrappers.add(index, newWrapper);
		oldWrapper.hideAllFragments();
		newWrapper.initFragments();
	}
	
}
