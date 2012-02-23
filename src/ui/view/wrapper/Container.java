package ui.view.wrapper;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.layout.Layout;
import ui.layout.calc.LayoutCalc;
import config.ControlConfig;
import enums.ELayoutType;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public abstract class Container extends ViewWrapper {

	protected ArrayList<ViewWrapper> controls;
	protected ArrayList<Integer> controlIds;
	
	public Container(BaseActivity context, ViewWrapper parent, int id){
		super(context, parent, id);
	}
	
	public void init(){
		view = getLayoutType().getLayout(activity);
		createControls();
		layoutControls();
		addControls();
		super.init();
	}	
	

	public abstract void createControls();
	
	public void layoutControls() {
		int w = width;
		int h = height;
		if(w == ControlConfig.INVALID) w = LayoutCalc.getMaxWidth(this, activity);
		if(h == ControlConfig.INVALID) h = LayoutCalc.getMaxHeight(this, activity);
		getLayout().layoutControls(controls, w, h);
	}

	public void addControls() {
		for(ViewWrapper c : controls){
			//activity.getBaseView().getLayout().addView(c.getView());
			getLayout().addView(c.getView());
		}
	}
	
	public void updateData() {
		for(ViewWrapper control : controls){
			control.updateData();
		}
		layoutControls();
	}
	
	
	public Layout getLayout() {
		return (Layout)view;
	}
	
	public ArrayList<ViewWrapper> getControls() {
		return controls;
	}
	
}
