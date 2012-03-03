package ui.fragment;

import ui.activity.BaseActivity;
import ui.wrapper.ContainerWrapper;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected ContainerWrapper baseContainerWrapper;
	protected BaseActivity activity;
	
	public BaseFragment(BaseActivity activity, ContainerWrapper baseContainerWrapper) {
		super();
		this.activity = activity;
		this.baseContainerWrapper = baseContainerWrapper;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return getView();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	@Override
	public View getView() {
		return baseContainerWrapper.getLayout();
	}

	public ContainerWrapper getBaseContainerFragment() {
		return baseContainerWrapper;
	}

	
	public BaseActivity getBaseActivity(){
		return (BaseActivity)getActivity();
	}
}
