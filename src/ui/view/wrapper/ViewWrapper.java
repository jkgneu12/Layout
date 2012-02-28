package ui.view.wrapper;

import ui.activity.BaseActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import config.ControlConfig;
import enums.EAlignment;
import enums.EControlType;
import enums.ELayoutType;

public abstract class ViewWrapper {
	
	public static final int INVALID = -1000;
	
	protected BaseActivity activity;
	protected View view;
	protected ViewWrapper parent;
	
	
	/*Configurable*/
	protected String title;
	
	protected int id = INVALID;
	
	protected EControlType type;
	
	protected int width = INVALID;
	protected int height = INVALID;
	
	protected int paddingLeft = 0;
	protected int paddingTop = 0;
	protected int paddingRight = 0;
	protected int paddingBottom = 0;
	
	protected int marginLeft = 0;
	protected int marginTop = 0;
	protected int marginRight = 0;
	protected int marginBottom = 0;
	
	protected EAlignment screenAlignment = EAlignment.LEFT;
	protected EAlignment innerAlignment = EAlignment.LEFT;

	protected int backgroundColor = Color.TRANSPARENT;
	
	protected ELayoutType layoutType = ELayoutType.FLOW;
	
	protected int targetScreenId = 1;
	
	
	public ViewWrapper(Context context, ViewWrapper parent, int id){
		this.activity = (BaseActivity)context;
		this.parent = parent;
		this.id = id;
	}
	
	public void createLayoutAndAddControls(){
		view.setBackgroundColor(backgroundColor);
	}
	
	public abstract void updateData();

	public abstract void setText(String text);
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public BaseActivity getActivity() {
		return activity;
	}

	public void setActivity(BaseActivity activity) {
		this.activity = activity;
	}

	public ViewWrapper getParent() {
		return parent;
	}

	public void setParent(ViewWrapper parent) {
		this.parent = parent;
	}

	
	/*Configurable*/
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EControlType getType() {
		return type;
	}

	public void setType(EControlType type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public int getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	public int getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	public int getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}

	public int getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}

	public EAlignment getScreenAlignment() {
		return screenAlignment;
	}

	public void setScreenAlignment(EAlignment screenAlignment) {
		this.screenAlignment = screenAlignment;
	}

	public EAlignment getInnerAlignment() {
		return innerAlignment;
	}

	public void setInnerAlignment(EAlignment innerAlignment) {
		this.innerAlignment = innerAlignment;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public ELayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(ELayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public int getTargetScreenId() {
		return targetScreenId;
	}

	public void setTargetScreenId(int targetScreenId) {
		this.targetScreenId = targetScreenId;
	}

	public int getMeasuredWidth() {
		if(width == ControlConfig.INVALID){
			getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			return getView().getMeasuredWidth();
		}
		return width;	
	}
	
	public int getMeasuredHeight() {
		if(height == ControlConfig.INVALID){
			getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			return getView().getMeasuredHeight();
		}
		return height;
	}
	
	public int getMeasuredWidthPlusMargins(){
		 return getMeasuredWidth() + marginLeft + marginRight;
	}
	
	public int getMeasuredHeightPlusMargins(){
		 return getMeasuredHeight() + marginTop + marginBottom;
	}
	
	public int getMaxWidth() {
		if(width != ControlConfig.INVALID)
			return width;
		if(parent != null){
			if(parent instanceof ViewWrapper){
				int configWidth = parent.getWidth();
			
				if(configWidth != ControlConfig.INVALID)
					return configWidth;
				else 
					return parent.getMaxWidth();
			}
		}
		return activity.getScreenWidth();
	}
	
	public int getMaxHeight() {
		if(height != ControlConfig.INVALID)
			return height;
		if(parent != null){
			if(parent instanceof ViewWrapper){
				int configHeight = parent.getHeight();
			
				if(configHeight != ControlConfig.INVALID)
					return configHeight;
				else 
					return parent.getMaxHeight();
			}
		}
		return activity.getScreenHeight();
	}
	
	public int getXOffset(){
		return 0;
	}
	
	public int getYOffset(){
		return 0;
	}
	
	public void applyOffsetX(int parentOffset){}
	public void applyOffsetY(int parentOffset){}
	
}
