package ui.wrapper;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.factory.MeasureFactory;
import ui.factory.WrapperFactory;
import ui.layout.Layout;
import config.WrapperConfig;

/**
 * Holds a layout
 * @author jimgeorge
 *
 */
public abstract class ContainerWrapper extends Wrapper<Layout> {

	protected ArrayList<Wrapper<?>> childWrappers;
	protected ArrayList<FragmentContainerWrapper> childFragmentWrappers;
	
	public ContainerWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
		childFragmentWrappers = new ArrayList<FragmentContainerWrapper>();
	}
	
	@Override
	public Wrapper<Layout> createWrapper(){
		view = config.layoutType.getLayout(activity, this);
		createChildWrappers();
		return super.createWrapper();
	}
	
	public void createChildWrappers() {
		childWrappers = new WrapperFactory().createChildWrappersForId(activity, this, config.id);
		for(Wrapper<?> wrapper : childWrappers){
			if(wrapper instanceof FragmentContainerWrapper)
				childFragmentWrappers.add((FragmentContainerWrapper) wrapper);
		}
	}
	
	@Override
	public void layoutWrapper() {
		layoutChildWrappers();
	}
	
	public void layoutChildWrappers(){
		for(Wrapper<?> c : childWrappers)
			c.layoutWrapper();
		view.layoutChildWrappers();
	}

	@Override
	public void addChildViews() {
		for(Wrapper<?> c : childWrappers){
			c.addChildViews();
			c.addViewToLayout(view);
		}
	}
	
	public void updateData(HashMap<String, Object> data) {
		for(Wrapper<?> wrapper : childWrappers){
			wrapper.updateData(data.get("" + wrapper.getConfig().id));
		}
		layoutChildWrappers();
		finishLayoutWrapper();
	}
	
	@Override
	public void finializeWrapper(){
		finalizeChildWrappers();
	}

	protected void finalizeChildWrappers() {
		for(Wrapper<?> childWrapper : childWrappers){
			childWrapper.finializeWrapper();
		}
	}
	

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
	
	public ArrayList<Wrapper<?>> getChildWrappers() {
		return childWrappers;
	}
	
	@Override
	public void setText(String text) {}

	public void resetLayout() {
		setLeftMargin(MeasureFactory.getPaddingLeft(this) + MeasureFactory.getMarginLeft(this));
		setTopMargin(MeasureFactory.getPaddingTop(this) + MeasureFactory.getMarginTop(this));
		view.resetLayout();
	}

	public void relayout(boolean reset) {
		super.relayout(reset);
		for(Wrapper<?> childWrapper : childWrappers)
			childWrapper.relayout(reset);
		if(reset)
			resetLayout();
		layoutChildWrappers();
		finishLayoutWrapper();
		
	}
}
