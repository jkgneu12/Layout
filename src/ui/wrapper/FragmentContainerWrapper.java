package ui.wrapper;

import ui.activity.BaseActivity;
import ui.fragment.BaseFragment;
import android.app.FragmentTransaction;
import config.ViewConfig;

public class FragmentContainerWrapper extends ContainerWrapper {

	private BaseFragment fragment;
	
	public FragmentContainerWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		this(activity, parent, config, true);
	}
	
	public FragmentContainerWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config, boolean addFragment) {
		super(activity, parent, config);
		if(addFragment)
			activity.addContainerWrapper(this);
	}
	
	public void finishLayoutWrappers(){
		applyOffsetX(0);
		applyOffsetY(0);
	}
	
	public void applyOffsetX(int parentOffsetX){
		if(getLayout() != null){
			int newTranslationX = getOffsetX(parentOffsetX);
			getLayout().setTranslationX(newTranslationX);
			
			for(Wrapper wrapper : getChildWrappers()){
				wrapper.applyOffsetX(newTranslationX);
			}
		}
	}
	
	public void applyOffsetY(int parentOffsetY){
		if(getLayout() != null){
			int newTranslationY = getOffsetY(parentOffsetY);
			getLayout().setTranslationY(newTranslationY);
			
			for(Wrapper wrapper : getChildWrappers()){
				wrapper.applyOffsetY(newTranslationY);
			}
		}
	}
	
	protected int getOffsetX(int parentOffsetX){
		return (int)(getLayout().getTranslationX() + parentOffsetX);
	}
	
	protected int getOffsetY(int parentOffsetY) {
		return (int)(parentOffsetY + getLayout().getTranslationY());
	}

	public void initFragments() {
		initFragment();
		for(FragmentContainerWrapper wrapper : childFragmentWrappers)
			wrapper.initFragments();
	}
	
	public synchronized boolean initFragment() {
		if(fragment == null)
			fragment = createFragment();
		FragmentTransaction trans = activity.getFragmentManager().beginTransaction();
		if(activity.getFragmentManager().findFragmentById(fragment.getId()) != null)
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
			FragmentTransaction trans = activity.getFragmentManager().beginTransaction();
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
