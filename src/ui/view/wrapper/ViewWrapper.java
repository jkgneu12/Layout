package ui.view.wrapper;

import ui.activity.BaseActivity;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout.Alignment;
import android.view.View;
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
	
	protected int paddingLeft = INVALID;
	protected int paddingTop = INVALID;
	protected int paddingRight = INVALID;
	protected int paddingBottom = INVALID;
	
	protected int marginLeft = INVALID;
	protected int marginTop = INVALID;
	protected int marginRight = INVALID;
	protected int marginBottom = INVALID;
	
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
	
	public void init(){
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
	
}
