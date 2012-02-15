package ui.view;
import ui.activity.BaseActivity;
import ui.factory.ControlFactory;


public class BaseView extends ScreenContainer {
	

	public BaseView(BaseActivity context, int screenId, int width, int height) {
		super(context, null, screenId, width, height);
		this.screenConfig = activity.getConfigStore().getScreenConfig(screenId);
	}

	@Override
	public void createControls() {
		controls = new ControlFactory().createControls(activity, this, screenConfig);
	}

}
