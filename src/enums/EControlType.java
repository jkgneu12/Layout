package enums;

public enum EControlType {
	BUTTON,
	TEXT,
	SCREENHOST,
	REGION;

	public static EControlType stringToControlType(String type) {
		if(type != null){
			if(type.equals("button")) return BUTTON;
			if(type.equals("text")) return TEXT;
			if(type.equals("sh")) return SCREENHOST;
			if(type.equals("region")) return REGION;
		}
		
		return null;
	}
}
