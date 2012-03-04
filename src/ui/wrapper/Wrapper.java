package ui.wrapper;

import ui.activity.BaseActivity;
import android.view.View;
import android.view.View.MeasureSpec;
import config.Config;

public abstract class Wrapper {
	
	public static final int INVALID = -1000;
	
	protected BaseActivity activity;
	protected View view;
	protected ContainerWrapper parentWrapper;
	protected Config config;
	
	
	/*Configurable*/
	/*protected String title;
	
	protected int id = INVALID;
	
	protected EWrapperType type;
	
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
	private int textColor = Color.BLACK;
	
	protected ELayoutType layoutType = ELayoutType.FLOW;
	
	protected ArrayList<Integer> targetWrapperIds;
	
	protected int navigationId = INVALID;*/
	
	
	public Wrapper(BaseActivity context, ContainerWrapper parentWrapper, Config config){
		this.activity = context;
		this.parentWrapper = parentWrapper;
		this.config = config;
		
		activity.addWrapper(config.id, this);
	}
	
	public void createAndLayoutAndAddWrappers(){
		view.setBackgroundColor(config.backgroundColor);
		view.setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom);
		setText(config.title);
	}
	
	public abstract void finializeWrappers();
	
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

	public ContainerWrapper getParent() {
		return parentWrapper;
	}

	public void setParent(ContainerWrapper parent) {
		this.parentWrapper = parent;
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}

	public Config getConfig() {
		return config;
	}

	
	/*Configurable*/
	/*
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

	public EWrapperType getType() {
		return type;
	}

	public void setType(EWrapperType type) {
		this.type = type;
	}

	public void setWidth(int width) {
		this.width = width;
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
	
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public int getTextColor() {
		return textColor;
	}
	
	public ELayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(ELayoutType layoutType) {
		this.layoutType = layoutType;
	}
	
	public void setTargetWrapperIds(ArrayList<Integer> targetWrapperIds) {
		this.targetWrapperIds = targetWrapperIds;
	}
	
	public void setNavigationId(int navigationId) {
		this.navigationId = navigationId;
	}

	public int getNavigationId() {
		return navigationId;
	}
	*/
	
	
	
	
	public int getConfiguredWidth(){
		if(config.width >= 0 || config.width == Config.INVALID)
			return config.width;
		return (int) (activity.getScreenWidth() * ((double)-config.width / 100.0));
	}
	
	public int getConfiguredHeight(){
		if(config.height >= 0 || config.height == Config.INVALID)
			return config.height;
		return (int) (activity.getScreenHeight() * ((double)-config.height / 100.0));
	}

	public int getMeasuredWidth() {
		if(getConfiguredWidth() == Config.INVALID){
			getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			return getView().getMeasuredWidth();
		}
		return getConfiguredWidth();	
	}
	
	public int getMeasuredHeight() {
		if(getConfiguredHeight() == Config.INVALID){
			getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			return getView().getMeasuredHeight();
		}
		return getConfiguredHeight();
	}
	
	public int getMeasuredWidthPlusMargins(){
		 return getMeasuredWidth() + config.marginLeft + config.marginRight;
	}
	
	public int getMeasuredHeightPlusMargins(){
		 return getMeasuredHeight() + config.marginTop + config.marginBottom;
	}
	
	public int getMaxWidth() {
		if(getConfiguredWidth() != Config.INVALID)
			return getConfiguredWidth();
		if(parentWrapper != null){
			if(parentWrapper instanceof Wrapper){
				int configWidth = parentWrapper.getConfiguredWidth();
			
				if(configWidth != Config.INVALID)
					return configWidth;
				else 
					return parentWrapper.getMaxWidth();
			}
		}
		return activity.getScreenWidth();
	}
	
	public int getMaxHeight() {
		if(getConfiguredHeight() != Config.INVALID)
			return getConfiguredHeight();
		if(parentWrapper != null){
			if(parentWrapper instanceof Wrapper){
				int configHeight = parentWrapper.getConfiguredHeight();
			
				if(configHeight != Config.INVALID)
					return configHeight;
				else 
					return parentWrapper.getMaxHeight();
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
