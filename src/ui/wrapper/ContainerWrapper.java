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
	
	public void createLayoutAndAddWrappers(){
		view = config.layoutType.getLayout(activity, this);
		createWrappers();
		layoutWrappers();
		addWrappers();
		super.createLayoutAndAddWrappers();
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
	
	public void initFragment(boolean force) {
		if(!fragmentHasBeenAdded || force){
			if(fragment == null)
				fragment = new BaseFragment(this);
			FragmentTransaction trans = activity.getFragmentManager().beginTransaction();
			trans.add(android.R.id.content, fragment);
			trans.commit();
			fragmentHasBeenAdded  = true;
		}
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
		int offset = config.paddingLeft + config.marginLeft;
		if(parentWrapper != null)
			return offset + parentWrapper.getXOffset();
		return offset;
	}
	
	public int getYOffset(){
		int offset = config.paddingTop + config.marginTop;
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
		if(config.paddingLeft != Wrapper.INVALID)
			width += config.paddingLeft;
		if(config.paddingRight != Wrapper.INVALID)
			width += config.paddingRight;
		return width;
	}
	
	public int addPaddingToHeight(int height) {
		if(config.paddingTop != Wrapper.INVALID)
			height += config.paddingTop;
		if(config.paddingBottom != Wrapper.INVALID)
			height += config.paddingBottom;
		return height;
	}

	public int subtractPaddingFromWidth(int width) {
		if(config.paddingLeft != Wrapper.INVALID)
			width -= config.paddingLeft;
		if(config.paddingRight != Wrapper.INVALID)
			width -= config.paddingRight;
		return width;
	}
	
	public int subtractPaddingFromHeight(int height) {
		if(config.paddingTop != Wrapper.INVALID)
			height -= config.paddingTop;
		if(config.paddingBottom != Wrapper.INVALID)
			height -= config.paddingBottom;
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
		view.setTranslationX(config.paddingLeft + config.marginLeft);
		view.setTranslationY(config.paddingTop + config.marginTop);
		getLayout().resetLayout();
	}
}
