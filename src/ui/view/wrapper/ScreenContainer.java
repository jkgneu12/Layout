package ui.view.wrapper;

import ui.activity.BaseActivity;
import ui.factory.ControlFactory;

public class ScreenContainer extends Container {

	protected int screenId;
	
	public ScreenContainer(BaseActivity context, ViewWrapper parent, int id) {
		super(context, parent, id);
	}
	
	public void createControls() {
		controls = new ControlFactory().createControlsForScreen(activity, this, targetScreenId);
	}
	
	@Override
	public void setText(String title) {
		// TODO Auto-generated method stub
		
	}
	
}
