package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.CustomTextView;
import android.content.res.ColorStateList;
import config.WrapperConfig;

public class TextWrapper extends Wrapper<CustomTextView> {

	public TextWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}
	
	@Override
	public Wrapper<CustomTextView> createWrapper() {
		view = new CustomTextView(activity, this);
		view.setTextColor(new ColorStateList(
				new int[][] {
						new int[] { android.R.attr.state_pressed},
						new int[] { android.R.attr.state_selected},
						
						new int[] {android.R.attr.state_focused},
						new int[] {}
				}, 
				new int[] {
						styleConfig.pressedTextColor,
						styleConfig.selectedTextColor,
						styleConfig.focusedTextColor,
						styleConfig.defaultTextColor
				})
		);
		return super.createWrapper();
	}
	
	@Override
	public void layoutWrapper() {}
	
	@Override
	public void finishLayoutWrapper() {}
	
	@Override
	public void finializeWrapper(){}

	@Override
	public void updateData(Object data) {
		setText((String)data);
	}

	@Override
	public void setText(String text) {
		view.setText(text);
		
	}

}
