package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.view.IView;
import ui.view.control.ButtonControl;
import ui.view.control.IControl;
import ui.view.control.Region;
import ui.view.control.ScreenHost;
import ui.view.control.TextControl;
import config.ControlConfig;
import config.ScreenConfig;

public class ControlFactory {

	public ControlFactory(){
		
	}
	
	public ArrayList<IControl> createControls(BaseActivity context, IView parent, ScreenConfig screenConfig){
		
		ArrayList<Integer> controlIds = screenConfig.getControlIds();
		
		HashMap<Integer, ControlConfig> controlConfigs = context.getConfigStore().getControlConfigs(controlIds);
		
		ArrayList<IControl> controls = new ArrayList<IControl>();
		
		for(int id : controlIds){
			ControlConfig controlConfig = controlConfigs.get(id);
			IControl c = createControl(context, parent, controlConfig);
			initControl(c, controlConfig);
			controls.add(c);
		}

        return controls;
	}
	
	public ArrayList<IControl> createControls(BaseActivity context, IView parent, ControlConfig controlConfig) {
		ArrayList<Integer> controlIds = controlConfig.getControlIds();
		
		HashMap<Integer, ControlConfig> controlConfigs = context.getConfigStore().getControlConfigs(controlIds);
		
		ArrayList<IControl> controls = new ArrayList<IControl>();
		
		for(int id : controlIds){
			ControlConfig innerControlConfig = controlConfigs.get(id);
			IControl c = createControl(context, parent, innerControlConfig);
			initControl(c, innerControlConfig);
			controls.add(c);
		}

        return controls;
	}

	private IControl createControl(BaseActivity context, IView parent, ControlConfig controlConfig) {
		switch (controlConfig.getType()) {
			case BUTTON: return new ButtonControl(context, parent, controlConfig);			
			case TEXT: return new TextControl(context, parent, controlConfig);	
			case SCREENHOST: 
				ScreenHost s = new ScreenHost(context, parent, controlConfig);
				s.init();
				return s;
			case REGION: 
				Region r = new Region(context, parent, controlConfig);
				r.init();
				return r;

			default:return null;
		}
	}
	
	private void initControl(IControl c, ControlConfig controlConfig) {
		c.setId(controlConfig.getId());
		c.setBackgroundColor(controlConfig.getBackgroundColor());
		c.setText(controlConfig.getTitle());
		c.setGravity(controlConfig.getInnerAlignment().value());
		c.setPadding(controlConfig.getPaddingLeft(), controlConfig.getPaddingTop(), controlConfig.getPaddingRight(), controlConfig.getPaddingBottom());
	}

}
