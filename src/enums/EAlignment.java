package enums;

import android.view.Gravity;

public enum EAlignment {
	LEFT, CENTER, RIGHT;

	public int value() {
		switch (this) {
		case LEFT:
			return Gravity.LEFT;
		case CENTER:
			return Gravity.CENTER;
		case RIGHT:
			return Gravity.RIGHT;
		default:
			return Gravity.LEFT;
		}
	}
	
	public static EAlignment stringToAlignment(String value) {
		if(value != null){
			if(value.equals("left")) return LEFT;
			if(value.equals("center")) return CENTER;
			if(value.equals("right")) return RIGHT;
		}
		return null;
	}
}
