package enums;

import ui.activity.BaseActivity;
import ui.layout.FlowLayout;
import ui.layout.HorizontalLayout;
import ui.layout.Layout;
import ui.wrapper.Wrapper;

public enum ELayoutType {
	FLOW (){
		@Override
		public Layout getLayout(BaseActivity activity, Wrapper wrapper){
			return new FlowLayout(activity, wrapper);
			
		}
	},
	HORIZONTAL {
		@Override
		public Layout getLayout(BaseActivity activity, Wrapper wrapper) {
			return new HorizontalLayout(activity, wrapper);
		}
	},
	VERTICAL {
		@Override
		public Layout getLayout(BaseActivity activity, Wrapper wrapper) {
			return null;
		}
	};
	
	public abstract Layout getLayout(BaseActivity activity, Wrapper wrapper);
	
	public static ELayoutType stringToLayoutType(String s){
		if(s != null){
			if(s.equals("flow"))
				return FLOW;
			if(s.equals("horizontal"))
				return HORIZONTAL;
			if(s.equals("vertical"))
				return VERTICAL;
			return FLOW;
		}
		return null;
	}
	
}
