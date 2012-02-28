package ui.wrapper.fragment;

import ui.activity.BaseActivity;
import ui.view.wrapper.ScreenContainer;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected ScreenContainer baseView;
	protected BaseActivity activity;
	
	public BaseFragment(BaseActivity activity, ScreenContainer baseView) {
		super();
		this.activity = activity;
		this.baseView = baseView;
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
		return baseView.getLayout();
	}

	public ScreenContainer getBaseView() {
		return baseView;
	}

	
	public BaseActivity getBaseActivity(){
		return (BaseActivity)getActivity();
	}
}
