package ui.wrapper;

import ui.activity.BaseActivity;
import android.graphics.Color;

/**
 * 
 *
 */
public class BaseContainerWrapper extends ContainerWrapper {

	public BaseContainerWrapper(BaseActivity context, ContainerWrapper parent, int id) {
		super(context, parent, id);
		this.width = context.getScreenWidth();
	    this.height = context.getScreenHeight();
	    this.backgroundColor = Color.GRAY;
	}
	
	public void updateData() {
		super.updateData();
		finishLayoutWrappers();
	}

}
