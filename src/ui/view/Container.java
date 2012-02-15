package ui.view;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.layout.Layout;
import ui.layout.calc.LayoutCalc;
import ui.view.control.IControl;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import config.ControlConfig;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public abstract class Container implements IView {

	protected BaseActivity activity;
	
	protected Layout layout;
	
	protected ArrayList<IControl> controls;
	
	protected int width, height;

	private IView parent;
	
	public Container(BaseActivity context, IView parent, int width, int height){
		activity = context;
		this.parent = parent;
		
		this.width = width;
		this.height = height;
	}
	
	public void init(){
		layout = Layout.create(getLayoutType(), activity);//new FlowLayout(activity);
		createControls();
		layoutControls();
		addControls();
	}	
	

	public abstract void createControls();
	
	public void layoutControls() {
		int w = width;
		int h = height;
		if(w == ControlConfig.INVALID) w = LayoutCalc.getMaxWidth(this, activity);
		if(h == ControlConfig.INVALID) h = LayoutCalc.getMaxHeight(this, activity);
		layout.layoutControls(controls, w, h);
	}

	public void addControls() {
		for(IControl c : controls){
			//activity.getBaseView().getLayout().addView(c.getView());
			layout.addView(c.getView());
		}
	}
	
	public void updateData() {
		for(IControl control : controls){
			control.updateData();
		}
		layoutControls();
	}
	
	public void invalidate(){
		layout.invalidate();
	}
	
	public String getLayoutType() {
		return "flow";
	}
	
	
	public BaseActivity getActivity() {
		return activity;
	}
	
	
	public View getView() {
		return layout;
	}
	
	public Layout getLayout() {
		return layout;
	}
	
	public ArrayList<IControl> getControls() {
		return controls;
	}
	
	public void setX(int x) {
		layout.setX(x);
	}

	public void setY(int y) {
		layout.setY(y);
	}
	
	public void setTranslationX(int x){
		layout.setTranslationX(x);
	}

	public void setTanslationY(int y){
		layout.setTranslationY(y);
	}

	public void setLayoutParams(LayoutParams params) {
		layout.setLayoutParams(params);
	}

	public int getId() {
		return layout.getId();
	}
	
	public void setId(int id){
		layout.setId(id);
	}
	
	public void setBackgroundColor(int backgroundColor){
		layout.setBackgroundColor(backgroundColor);
	}
	
	public void setGravity(int value){
		//layout calc should handle this
	}
	
	public void setPadding(int left, int top, int right, int bottom) {
		layout.setPadding(left, top, right, bottom);
	}

	
	public int getConfigWidth() {
		return width;
	}

	public int getConfigHeight() {
		return height;
	}
	
	public IView getViewParent() {
		return parent;
	}
	
}
