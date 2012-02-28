package ui.view.wrapper;

import android.graphics.Color;
import ui.activity.BaseActivity;

public class BaseContainer extends ScreenContainer {

	public BaseContainer(BaseActivity context, ViewWrapper parent, int id) {
		super(context, parent, id);
		this.targetScreenId = 1000;
		this.width = context.getScreenWidth();
	    this.height = context.getScreenHeight();
	    this.backgroundColor = Color.GRAY;
	}
	
	public void finishLayoutControls(){
		applyOffsetX(0);
		applyOffsetY(0);
	}
	
	public void updateData() {
		super.updateData();
		finishLayoutControls();
	}

}
