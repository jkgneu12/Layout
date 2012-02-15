package ui.view;

import ui.activity.BaseActivity;
import config.ScreenConfig;

public abstract class ScreenContainer extends Container {

	protected ScreenConfig screenConfig;
	
	protected int screenId;
	
	public ScreenContainer(BaseActivity context, IView parent, int screenId, int width, int height) {
		super(context, parent, width, height);
		
		this.screenId = screenId;
		
	}
	
	public int getScreenId(){
		return screenId;
	}
	
}
