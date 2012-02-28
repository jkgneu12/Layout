package ui.view.wrapper;

import ui.activity.BaseActivity;
import ui.factory.ControlFactory;
import ui.wrapper.fragment.BaseFragment;
import android.R;
import android.app.FragmentTransaction;

public class ScreenContainer extends Container {

	protected int screenId;
	protected BaseFragment fragment;
	
	public ScreenContainer(BaseActivity context, ViewWrapper parent, int id) {
		super(context, parent, id);
		context.addScreenContainer(this);
	}
	
	@Override
	public void createLayoutAndAddControls() {
		view = activity.getConfigStore().getScreenConfig(targetScreenId).getLayoutType().getLayout(activity);
		super.createLayoutAndAddControls();
	}
	
	public void initFragment() {
		fragment = new BaseFragment(activity, this);
		FragmentTransaction trans = activity.getFragmentManager().beginTransaction();
		trans.add(R.id.content, fragment);
		trans.commit();
	}
	
	public void createControls() {
		controls = new ControlFactory().createControlsForScreen(activity, this, targetScreenId);
	}
	
	@Override
	public void setText(String title) {
		// TODO Auto-generated method stub
		
	}

	public BaseFragment getFragment() {
		return fragment;
	}
	
	
}
