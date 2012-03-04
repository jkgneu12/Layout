package config;

import android.graphics.Color;

public class StyleConfig {

	public static final int INVALID = -1000;
	
	public int id = INVALID;
	
	public int paddingLeft = 0;
	public int paddingTop = 0;
	public int paddingRight = 0;
	public int paddingBottom = 0;
	
	public int backgroundColor = Color.TRANSPARENT;
	

	public int pressedTextColor = Color.BLACK;
	public int selectedTextColor = Color.BLACK;
	public int focusedTextColor = Color.BLACK;
	public int defaultTextColor = Color.BLACK;
	
	public StyleConfig(){}
}
