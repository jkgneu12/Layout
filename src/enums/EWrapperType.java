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
import config.ViewConfig;

public enum EWrapperType {
	BUTTON {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new ButtonWrapper(activity, parent, config);
		}
	},
	TEXT {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new TextWrapper(activity, parent, config);
		}
	},
	CONTAINER {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new FragmentContainerWrapper(activity, parent, config);
		}
	}, 
	LIST {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new ListWrapper(activity, parent, config);
		}
	},
	LISTROW {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new ListRowWrapper(activity, parent, config);
		}
	},
	LISTTARGET {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new ListTargetWrapper(activity, parent, config);
		}
	},
	TAB {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new TabWrapper(activity, parent, config);
		}
	},
	TABBUTTON {
		@Override
		public Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
			return new TabButtonWrapper(activity, parent, config);
		}
	};

	public abstract Wrapper getWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config);
	
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
