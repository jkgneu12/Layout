package ui.fragment;

import ui.activity.BaseActivity;
import ui.wrapper.ContainerWrapper;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected ContainerWrapper baseContainerWrapper;
	protected BaseActivity activity;
	
	public BaseFragment(){
		super();
	}
	
	public BaseFragment(ContainerWrapper baseContainerWrapper) {
		super();
		this.baseContainerWrapper = baseContainerWrapper;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (BaseActivity)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return getView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	
	@Override
	public View getView() {
		return baseContainerWrapper.getView();
	}

	public ContainerWrapper getBaseContainerFragment() {
		return baseContainerWrapper;
	}

	
	public BaseActivity getBaseActivity(){
		return (BaseActivity)getActivity();
	}
}
