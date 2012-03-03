package enums;

public enum EWrapperType {
	BUTTON,
	TEXT,
	CONTAINER,
	TAB,
	TABBUTTON;

	public static EWrapperType stringToWrapperType(String type) {
		if(type != null){
			if(type.equals("button")) return BUTTON;
			if(type.equals("text")) return TEXT;
			if(type.equals("container")) return CONTAINER;
			if(type.equals("tab")) return TAB;
			if(type.equals("tabbutton")) return TABBUTTON;
		}
		
		return null;
	}
}
