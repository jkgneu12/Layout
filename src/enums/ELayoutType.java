package enums;

import ui.activity.BaseActivity;
import ui.layout.FlowLayout;
import ui.layout.HorizontalLayout;
import ui.layout.Layout;

public enum ELayoutType {
	FLOW (){
		@Override
		public Layout getLayout(BaseActivity activity){
			return new FlowLayout(activity);
			
		}
	},
	HORIZONTAL {
		@Override
		public Layout getLayout(BaseActivity activity) {
			return new HorizontalLayout(activity);
		}
	},
	VERTICAL {
		@Override
		public Layout getLayout(BaseActivity activity) {
			return null;
		}
	};
	
	public abstract Layout getLayout(BaseActivity activity);
	
	public static ELayoutType stringToLayoutType(String s){
		if(s.equals("flow"))
			return FLOW;
		if(s.equals("horizontal"))
			return HORIZONTAL;
		if(s.equals("vertical"))
			return VERTICAL;
		return FLOW;
	}
	
}
