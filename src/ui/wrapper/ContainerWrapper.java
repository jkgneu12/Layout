package ui.wrapper;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.factory.WrapperFactory;
import ui.fragment.BaseFragment;
import ui.layout.Layout;
import android.app.FragmentTransaction;
import config.Config;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public class ContainerWrapper extends Wrapper {

	protected ArrayList<Wrapper> childWrappers;
	
	protected BaseFragment fragment;
	private boolean fragmentHasBeenAdded = false;
	
	private int calculatedWidth = INVALID;
	private int calculatedHeight = INVALID;
	
	public ContainerWrapper(BaseActivity activity, ContainerWrapper parent, Config config) {
		this(activity, parent, config, true);
	}
	
	public ContainerWrapper(BaseActivity activity, ContainerWrapper parent, Config config, boolean addFragment) {
		super(activity, parent, config);
		if(addFragment)
			activity.addContainerWrapper(this);
	}
	
	public void createAndLayoutAndAddWrappers(){
		view = config.layoutType.getLayout(activity, this);
		createWrappers();
		layoutWrappers();
		addWrappers();
		super.createAndLayoutAndAddWrappers();
	}
	
	public void createWrappers() {
		childWrappers = new WrapperFactory().createWrapperForId(activity, this, config.id);
	}
	
	public void layoutWrappers() {
		getLayout().layoutWrappers(this, getMaxWidth(), getMaxHeight());
	}

	public void addWrappers() {
		for(Wrapper c : getChildWrappers()){
			//ContainerWrapper hold Fragments and will be added to the id.content view
			if(!(c instanceof ContainerWrapper))
				getLayout().addView(c.getView());
		}
	}
	
	public void finializeWrappers(){
		for(Wrapper childWrapper : childWrappers){
			childWrapper.finializeWrappers();
		}
	}
	
	public void finishLayoutWrappers(){
		applyOffsetX(0);
		applyOffsetY(0);
	}
	
	public void applyOffsetX(int parentOffsetX){
		int newTranslationX = getOffsetX(parentOffsetX);
		getLayout().setTranslationX(newTranslationX);
		
		for(Wrapper wrapper : getChildWrappers()){
			wrapper.applyOffsetX(newTranslationX);
		}
	}
	
	public void applyOffsetY(int parentOffsetY){
		int newTranslationY = getOffsetY(parentOffsetY);
		getLayout().setTranslationY(newTranslationY);
		
		for(Wrapper wrapper : getChildWrappers()){
			wrapper.applyOffsetY(newTranslationY);
		}
	}
	
	protected int getOffsetX(int parentOffsetX){
		return (int)(getLayout().getTranslationX() + parentOffsetX);
	}
	
	protected int getOffsetY(int parentOffsetY) {
		return (int)(parentOffsetY + getLayout().getTranslationY());
	}
	
	public synchronized boolean initFragment() {
		if(fragment == null)
			fragment = new BaseFragment(this);
		if(fragment.getView().getParent() != null)
			return false;
		FragmentTransaction trans = activity.getFragmentManager().beginTransaction();
		//trans.setCustomAnimations(R.animator.slide_in, R.animator.slide_out);
		trans.add(android.R.id.content, fragment);
		trans.commit();
		fragmentHasBeenAdded  = true;
		return true;
	}

	public BaseFragment getFragment() {
		return fragment;
	}
	
	
	public void replaceChildWrapper(Wrapper oldWrapper, Wrapper newWrapper) {
		int index = childWrappers.indexOf(oldWrapper);
		childWrappers.remove(index);
		childWrappers.add(index, newWrapper);
	}
	
	public void updateData() {
		for(Wrapper wrapper : getChildWrappers()){
			wrapper.updateData();
		}
		layoutWrappers();
		finishLayoutWrappers();
	}
	
	public int getXOffset(){
		int offset = styleConfig.paddingLeft + config.marginLeft;
		if(parentWrapper != null)
			return offset + parentWrapper.getXOffset();
		return offset;
	}
	
	public int getYOffset(){
		int offset = styleConfig.paddingTop + config.marginTop;
		if(parentWrapper != null)
			return offset + parentWrapper.getYOffset();
		return offset;
	}
	
	public Layout getLayout() {
		return (Layout)view;
	}
	
	public ArrayList<Wrapper> getChildWrappers() {
		return childWrappers;
	}

	public void setCalculatedWidth(int width) {
		this.calculatedWidth = width;
	}

	public void setCalculatedHeight(int height) {
		this.calculatedHeight = height;
	}
	
	@Override
	public int getMeasuredWidth() {
		if(getConfiguredWidth() != INVALID)
			return addPaddingToWidth(getConfiguredWidth());
		if(calculatedWidth != INVALID)
			return calculatedWidth;
		return super.getMeasuredWidth();
	}
	
	@Override
	public int getMeasuredHeight() {
		if(getConfiguredHeight() != INVALID)
			return addPaddingToHeight(getConfiguredHeight());
		if(calculatedHeight != INVALID)
			return calculatedHeight;
		
		return super.getMeasuredHeight();
	}
	
	public int addPaddingToWidth(int width) {
		if(styleConfig.paddingLeft != Wrapper.INVALID)
			width += styleConfig.paddingLeft;
		if(styleConfig.paddingRight != Wrapper.INVALID)
			width += styleConfig.paddingRight;
		return width;
	}
	
	public int addPaddingToHeight(int height) {
		if(styleConfig.paddingTop != Wrapper.INVALID)
			height += styleConfig.paddingTop;
		if(styleConfig.paddingBottom != Wrapper.INVALID)
			height += styleConfig.paddingBottom;
		return height;
	}

	public int subtractPaddingFromWidth(int width) {
		if(styleConfig.paddingLeft != Wrapper.INVALID)
			width -= styleConfig.paddingLeft;
		if(styleConfig.paddingRight != Wrapper.INVALID)
			width -= styleConfig.paddingRight;
		return width;
	}
	
	public int subtractPaddingFromHeight(int height) {
		if(styleConfig.paddingTop != Wrapper.INVALID)
			height -= styleConfig.paddingTop;
		if(styleConfig.paddingBottom != Wrapper.INVALID)
			height -= styleConfig.paddingBottom;
		return height;
	}
	
	public int subtractMarginFromWidth(int width) {
		if(config.marginLeft != Wrapper.INVALID)
			width -= config.marginLeft;
		if(config.marginRight != Wrapper.INVALID)
			width -= config.marginRight;
		return width;
	}
	
	public int subtractMarginFromHeight(int height) {
		if(config.marginTop != Wrapper.INVALID)
			height -= config.marginTop;
		if(config.marginBottom != Wrapper.INVALID)
			height -= config.marginBottom;
		return height;
	}

	@Override
	public void setText(String text) {}

	public void resetLayout() {
		view.setTranslationX(styleConfig.paddingLeft + config.marginLeft);
		view.setTranslationY(styleConfig.paddingTop + config.marginTop);
		getLayout().resetLayout();
	}

	public void relayout(boolean reset) {
		for(Wrapper childWrapper : childWrappers)
			childWrapper.relayout(reset);
		if(reset)
			resetLayout();
		layoutWrappers();
		finishLayoutWrappers();
		
	}
}
