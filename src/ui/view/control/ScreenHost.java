package ui.view.control;

import ui.activity.BaseActivity;
import ui.factory.ControlFactory;
import ui.view.IView;
import ui.view.ScreenContainer;
import android.view.View;
import config.ControlConfig;

public class ScreenHost extends ScreenContainer implements IControl {

	private ControlConfig controlConfig;
	
	public ScreenHost(BaseActivity context, IView parent, ControlConfig config) {
		super(context, parent, config.getId(), config.getWidth(), config.getHeight());
		this.controlConfig = config;
		
		this.screenConfig = activity.getConfigStore().getScreenConfig(controlConfig.getTargetScreenId());
	}
	
	public void init(){
		super.init();
		layout.setBackgroundColor(controlConfig.getBackgroundColor());
	}	
	
	public View getView() {
		if(layout == null)
			init();
		return layout;
	}

	public void createControls() {
		controls = new ControlFactory().createControls(activity, this, screenConfig);
	}

	public ControlConfig getControlConfig() {
		return controlConfig;
	}
	
	public String getLayoutType() {
		return controlConfig.getLayoutType();
	}
	
	public void setText(String title) {
		//setTitle??
	}

}
