package enums;

import ui.activity.BaseActivity;
import ui.layout.FlowLayout;
import ui.layout.HorizontalLayout;
import ui.layout.Layout;
import ui.layout.VerticalLayout;
import ui.wrapper.ContainerWrapper;

public enum ELayoutType {
	FLOW (){
		@Override
		public Layout getLayout(BaseActivity activity, ContainerWrapper wrapper){
			return new FlowLayout(activity, wrapper);
			
		}
	},
	HORIZONTAL {
		@Override
		public Layout getLayout(BaseActivity activity, ContainerWrapper wrapper) {
			return new HorizontalLayout(activity, wrapper);
		}
	},
	VERTICAL {
		@Override
		public Layout getLayout(BaseActivity activity, ContainerWrapper wrapper) {
			return new VerticalLayout(activity, wrapper);
		}
	};
	
	public abstract Layout getLayout(BaseActivity activity, ContainerWrapper wrapper);
	
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
