package ui.wrapper;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.factory.MeasureFactory;
import ui.layout.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import config.StyleConfig;
import config.WrapperConfig;

public abstract class Wrapper implements OnClickListener {
	
	public static final int INVALID = -1000;
	
	protected BaseActivity activity;
	protected View view;
	protected ContainerWrapper parentWrapper;
	protected WrapperConfig config;
	protected StyleConfig styleConfig;
	
	private int calculatedWidth = INVALID;
	private int calculatedHeight = INVALID;
	
	
	public Wrapper(BaseActivity context, ContainerWrapper parentWrapper, WrapperConfig config){
		this.activity = context;
		this.parentWrapper = parentWrapper;
		this.config = config;
		this.styleConfig = activity.getConfigStore().getStyleConfig(config.styleId);
		
		activity.addWrapper(config.id, this);
	}
	
	public Wrapper createWrapper() {
		view.setBackgroundColor(styleConfig.backgroundColor);
		view.setPadding(MeasureFactory.getPaddingLeft(this), MeasureFactory.getPaddingTop(this), MeasureFactory.getPaddingRight(this), MeasureFactory.getPaddingBottom(this));
		setText(config.title);
		return this;
	}
	
	public abstract void layoutWrapper();
	
	public void addChildViews(){}
	
	public void addViewToLayout(Layout layout) {
		layout.addView(getView());
	}
	
	public void relayout(boolean reset){
		setCalculatedHeight(INVALID);
		setCalculatedWidth(INVALID);
	}
	
	public abstract void finishLayoutWrapper();
	
	public abstract void finializeWrapper();
	
	public void updateData(ArrayList<HashMap<String, Object>> data){}
	public void updateData(HashMap<String, Object> data){}
	public void updateData(Object data){}

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
	
	public void setConfig(WrapperConfig config) {
		this.config = config;
	}

	public WrapperConfig getConfig() {
		return config;
	}
	
	public StyleConfig getStyleConfig() {
		return styleConfig;
	}
	
	
	public int getXOffset(){
		return 0;
	}
	
	public int getYOffset(){
		return 0;
	}
	
	public void applyOffsetX(int parentOffset){}
	public void applyOffsetY(int parentOffset){}

	public void onClick(View v) {
		activity.updateData(new HashMap<String,Object>());
	}

	public void setCalculatedWidth(int calculatedWidth) {
		this.calculatedWidth = calculatedWidth;
	}

	public int getCalculatedWidth() {
		return calculatedWidth;
	}

	public void setCalculatedHeight(int calculatedHeight) {
		this.calculatedHeight = calculatedHeight;
	}

	public int getCalculatedHeight() {
		return calculatedHeight;
	}
	
	public LayoutParams getLayoutParams(){
		android.view.ViewGroup.LayoutParams lp = getView().getLayoutParams();
		if(lp instanceof LayoutParams)
			return (LayoutParams)lp;
		return null;
	}
	
	public int getLeftMargin() {
		return getLayoutParams().leftMargin;
	}
	
	public int getTopMargin() {
		return getLayoutParams().topMargin;
	}
	
	public void setLeftMargin(int leftMargin) {
		getLayoutParams().leftMargin = leftMargin;
	}
	
	public void setTopMargin(int topMargin) {
		getLayoutParams().topMargin = topMargin;
	}
	
	public void increaseLeftMargin(int dLeftMargin) {
		getLayoutParams().leftMargin += dLeftMargin;
	}
	
	public void increaseTopMargin(int dTopMargin) {
		getLayoutParams().topMargin += dTopMargin;
	}
}
