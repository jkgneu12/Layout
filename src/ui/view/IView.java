package ui.view;

import android.view.View;

public interface IView {
	
	public View getView();
	
	public String getLayoutType();

	public IView getViewParent();
}
