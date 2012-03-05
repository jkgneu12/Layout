package ui.wrapper;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.factory.MeasureFactory;
import ui.factory.WrapperFactory;
import ui.layout.Layout;
import config.ViewConfig;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public abstract class ContainerWrapper extends Wrapper {

	protected ArrayList<Wrapper> childWrappers;
	protected ArrayList<FragmentContainerWrapper> childFragmentWrappers;
	
	public ContainerWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		super(activity, parent, config);
		childFragmentWrappers = new ArrayList<FragmentContainerWrapper>();
	}
	
	public void createAndLayoutAndAddWrappers(){
		view = config.layoutType.getLayout(activity, this).self();
		createWrappers();
		layoutWrappers();
		addWrappers();
		super.createAndLayoutAndAddWrappers();
	}
	
	public void createWrappers() {
		childWrappers = new WrapperFactory().createChildWrappersForId(activity, this, config.id);
		for(Wrapper wrapper : childWrappers){
			if(wrapper instanceof FragmentContainerWrapper)
				childFragmentWrappers.add((FragmentContainerWrapper) wrapper);
		}
	}
	
	public void layoutWrappers() {
		getLayout().layoutWrappers();
	}

	public void addWrappers() {
		for(Wrapper c : getChildWrappers()){
			//ContainerWrapper hold Fragments and will be added to the id.content view
			if(!(c instanceof FragmentContainerWrapper))
				getLayout().addView(c.getView());
		}
	}
	
	public void updateData(HashMap<String, Object> data) {
		for(Wrapper wrapper : getChildWrappers()){
			wrapper.updateData(data.get("" + wrapper.getConfig().id));
		}
		layoutWrappers();
		finishLayoutWrappers();
	}
	
	public void finializeWrappers(){
		for(Wrapper childWrapper : childWrappers){
			childWrapper.finializeWrappers();
		}
	}
	
	public void finishLayoutWrappers(){}

	public int getXOffset(){
		int offset = MeasureFactory.getPaddingLeft(this) + MeasureFactory.getMarginLeft(this);
		if(parentWrapper != null)
			return offset + parentWrapper.getXOffset();
		return offset;
	}
	
	public int getYOffset(){
		int offset = MeasureFactory.getPaddingTop(this) + MeasureFactory.getMarginTop(this);
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
	
	@Override
	public void setText(String text) {}

	public void resetLayout() {
		view.setTranslationX(MeasureFactory.getPaddingLeft(this) + MeasureFactory.getMarginLeft(this));
		view.setTranslationY(MeasureFactory.getPaddingTop(this) + MeasureFactory.getMarginTop(this));
		getLayout().resetLayout();
	}

	public void relayout(boolean reset) {
		super.relayout(reset);
		for(Wrapper childWrapper : childWrappers)
			childWrapper.relayout(reset);
		if(reset)
			resetLayout();
		layoutWrappers();
		finishLayoutWrappers();
		
	}
}
