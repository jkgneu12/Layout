package ui.wrapper;

import ui.activity.BaseActivity;
import android.view.ViewGroup.LayoutParams;
import config.ViewConfig;

public class ListRowWrapper extends ContainerWrapper {

	public ListRowWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		super(activity, parent, config);
	}

	public void createAndLayoutAndAddWrappers() {
		super.createAndLayoutAndAddWrappers();
		LayoutParams oldParams = view.getLayoutParams();
		view.setLayoutParams(new android.widget.AbsListView.LayoutParams(oldParams.width, oldParams.height));
	}
	
	@Override
	public void finializeWrappers() {}

	@Override
	public void finishLayoutWrappers() {}
}
