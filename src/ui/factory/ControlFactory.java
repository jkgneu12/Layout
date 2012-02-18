package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.view.wrapper.ButtonWrapper;
import ui.view.wrapper.Region;
import ui.view.wrapper.ScreenContainer;
import ui.view.wrapper.TextWrapper;
import ui.view.wrapper.ViewWrapper;
import config.ControlConfig;
import config.ScreenConfig;

public class ControlFactory {

	public ControlFactory(){
		
	}
	
	public ArrayList<ViewWrapper> createControlsForScreen(BaseActivity context, ViewWrapper parent, int screenId){
		
		ScreenConfig screenConfig = context.getConfigStore().getScreenConfig(screenId);
		
		ArrayList<Integer> controlIds = screenConfig.getControlIds();
		
		HashMap<Integer, ControlConfig> controlConfigs = context.getConfigStore().getControlConfigs(controlIds);
		
		ArrayList<ViewWrapper> controls = new ArrayList<ViewWrapper>();
		
		for(int id : controlIds){
			ControlConfig controlConfig = controlConfigs.get(id);
			ViewWrapper c = createControl(context, parent, controlConfig);
			initControl(c, controlConfig);
			controls.add(c);
		}

        return controls;
	}
	
	public ArrayList<ViewWrapper> createControlsForControl(BaseActivity context, ViewWrapper parent, int controlId) {
		ControlConfig controlConfig = context.getConfigStore().getControlConfig(controlId);
		
		ArrayList<Integer> controlIds = controlConfig.getControlIds();
		
		HashMap<Integer, ControlConfig> controlConfigs = context.getConfigStore().getControlConfigs(controlIds);
		
		ArrayList<ViewWrapper> controls = new ArrayList<ViewWrapper>();
		
		for(int id : controlIds){
			ControlConfig innerControlConfig = controlConfigs.get(id);
			ViewWrapper c = createControl(context, parent, innerControlConfig);
			initControl(c, innerControlConfig);
			controls.add(c);
		}

        return controls;
	}

	private ViewWrapper createControl(BaseActivity context, ViewWrapper parent, ControlConfig controlConfig) {
		int id = controlConfig.getId();
		switch (controlConfig.getType()) {
			case BUTTON: return new ButtonWrapper(context, parent, id);			
			case TEXT: return new TextWrapper(context, parent, id);	
			case SCREENHOST: 
				ScreenContainer s = new ScreenContainer(context, parent, id);
				s.init();
				return s;
			case REGION: 
				Region r = new Region(context, parent, id);
				r.init();
				return r;

			default:return null;
		}
	}
	
	private void initControl(ViewWrapper c, ControlConfig controlConfig) {
		c.setTitle(controlConfig.getTitle());
		c.setId(controlConfig.getId());
		c.setType(controlConfig.getType());
		c.setWidth(controlConfig.getWidth());
		c.setHeight(controlConfig.getHeight());
		c.setPaddingLeft(controlConfig.getPaddingLeft());
		c.setPaddingTop(controlConfig.getPaddingTop());
		c.setPaddingRight(controlConfig.getPaddingRight());
		c.setPaddingBottom(controlConfig.getPaddingBottom());
		c.setMarginLeft(controlConfig.getMarginLeft());
		c.setMarginTop(controlConfig.getMarginTop());
		c.setMarginRight(controlConfig.getMarginRight());
		c.setMarginBottom(controlConfig.getMarginBottom());
		c.setScreenAlignment(controlConfig.getScreenAlignment());
		c.setInnerAlignment(controlConfig.getInnerAlignment());
		c.setBackgroundColor(controlConfig.getBackgroundColor());
		c.setLayoutType(controlConfig.getLayoutType());
		c.setTargetScreenId(controlConfig.getTargetScreenId());
		
		c.init();
	}

}
