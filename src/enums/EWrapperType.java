package enums;

import ui.activity.BaseActivity;
import ui.wrapper.ButtonWrapper;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.FragmentContainerWrapper;
import ui.wrapper.ListRowWrapper;
import ui.wrapper.ListTargetWrapper;
import ui.wrapper.ListWrapper;
import ui.wrapper.TabButtonWrapper;
import ui.wrapper.TabWrapper;
import ui.wrapper.TextWrapper;
import ui.wrapper.Wrapper;
import config.WrapperConfig;

public enum EWrapperType {
	BUTTON {
		@Override
		public ButtonWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new ButtonWrapper(activity, parent, config);
		}
	},
	TEXT {
		@Override
		public TextWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new TextWrapper(activity, parent, config);
		}
	},
	CONTAINER {
		@Override
		public FragmentContainerWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new FragmentContainerWrapper(activity, parent, config);
		}
	}, 
	LIST {
		@Override
		public ListWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new ListWrapper(activity, parent, config);
		}
	},
	LISTROW {
		@Override
		public ListRowWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new ListRowWrapper(activity, parent, config);
		}
	},
	LISTTARGET {
		@Override
		public ListTargetWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new ListTargetWrapper(activity, parent, config);
		}
	},
	TAB {
		@Override
		public TabWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new TabWrapper(activity, parent, config);
		}
	},
	TABBUTTON {
		@Override
		public TabButtonWrapper initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
			return new TabButtonWrapper(activity, parent, config);
		}
	};

	public abstract Wrapper<?> initializeWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config);
	
	public static EWrapperType stringToWrapperType(String type) {
		if(type != null){
			if(type.equals("button")) return BUTTON;
			if(type.equals("text")) return TEXT;
			if(type.equals("container")) return CONTAINER;
			if(type.equals("list")) return LIST;
			if(type.equals("listrow")) return LISTROW;
			if(type.equals("listtarget")) return LISTTARGET;
			if(type.equals("tab")) return TAB;
			if(type.equals("tabbutton")) return TABBUTTON;
		}
		
		return null;
	}
}
