package ui.view.wrapper;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.layout.Layout;
import ui.wrapper.fragment.BaseFragment;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public abstract class Container extends ViewWrapper {

	protected ArrayList<ViewWrapper> controls;
	protected ArrayList<Integer> controlIds;
	private BaseFragment baseFragment;
	private int calculatedWidth = INVALID;
	private int calculatedHeight = INVALID;
	
	public Container(BaseActivity context, ViewWrapper parent, int id){
		super(context, parent, id);
	}
	
	public void createLayoutAndAddControls(){
		createControls();
		layoutControls();
		addControls();
		super.createLayoutAndAddControls();
	}
	
	public void applyOffsetX(int parentOffsetX){
		int newTranslationX = (int)(getLayout().getTranslationX() + parentOffsetX);
		getLayout().setTranslationX(newTranslationX);
		
		for(ViewWrapper control : controls){
			control.applyOffsetX(newTranslationX);
		}
	}
	
	public void applyOffsetY(int parentOffsetY){
		int newTranslationY = (int)(parentOffsetY + getLayout().getTranslationY());
		getLayout().setTranslationY(newTranslationY);
		
		for(ViewWrapper control : controls){
			control.applyOffsetY(newTranslationY);
		}
	}

	public abstract void createControls();
	
	public void layoutControls() {
		getLayout().layoutControls(this, getMaxWidth(), getMaxHeight());
	}

	public void addControls() {
		for(ViewWrapper c : controls){
			//ScreenContainers are Fragments and will be added to the id.content view
			if(!(c instanceof ScreenContainer))
				getLayout().addView(c.getView());
		}
	}
	
	public void updateData() {
		for(ViewWrapper control : controls){
			control.updateData();
		}
		layoutControls();
	}
	
	public int getXOffset(){
		int offset = paddingLeft + marginLeft;
		if(parent != null)
			return offset + parent.getXOffset();
		return offset;
	}
	
	public int getYOffset(){
		int offset = paddingTop + marginTop;
		if(parent != null)
			return offset + parent.getYOffset();
		return offset;
	}
	
	public Layout getLayout() {
		return (Layout)view;
	}
	
	public ArrayList<ViewWrapper> getControls() {
		return controls;
	}

	public void setCalculatedWidth(int width) {
		this.calculatedWidth = width;
	}

	public void setCalculatedHeight(int height) {
		this.calculatedHeight = height;
	}
	
	@Override
	public int getMeasuredWidth() {
		if(width != INVALID)
			return addPaddingToWidth(width);
		if(calculatedWidth != INVALID)
			return calculatedWidth;
		return super.getMeasuredWidth();
	}
	
	@Override
	public int getMeasuredHeight() {
		if(height != INVALID)
			return addPaddingToHeight(height);
		if(calculatedHeight != INVALID)
			return calculatedHeight;
		
		return super.getMeasuredHeight();
	}
	
	public int addPaddingToWidth(int width) {
		if(paddingLeft != ViewWrapper.INVALID)
			width += paddingLeft;
		if(paddingRight != ViewWrapper.INVALID)
			width += paddingRight;
		return width;
	}
	
	public int addPaddingToHeight(int height) {
		if(paddingTop != ViewWrapper.INVALID)
			height += paddingTop;
		if(paddingBottom != ViewWrapper.INVALID)
			height += paddingBottom;
		return height;
	}
	
	public int subtractMarginAndPaddingFromWidth(int width) {
		width = subtractPaddingFromWidth(width);
		return subtractMarginFromWidth(width);
	}
	
	public int subtractMarginAndPaddingFromHeight(int height) {
		width = subtractPaddingFromHeight(height);
		return subtractMarginFromHeight(height);
	}

	public int subtractPaddingFromWidth(int width) {
		if(paddingLeft != ViewWrapper.INVALID)
			width -= paddingLeft;
		if(paddingRight != ViewWrapper.INVALID)
			width -= paddingRight;
		return width;
	}
	
	public int subtractPaddingFromHeight(int height) {
		if(paddingTop != ViewWrapper.INVALID)
			height -= paddingTop;
		if(paddingBottom != ViewWrapper.INVALID)
			height -= paddingBottom;
		return height;
	}
	
	public int subtractMarginFromWidth(int width) {
		if(marginLeft != ViewWrapper.INVALID)
			width -= marginLeft;
		if(marginRight != ViewWrapper.INVALID)
			width -= marginRight;
		return width;
	}
	
	public int subtractMarginFromHeight(int height) {
		if(marginTop != ViewWrapper.INVALID)
			height -= marginTop;
		if(marginBottom != ViewWrapper.INVALID)
			height -= marginBottom;
		return height;
	}
}
