package config;

import ui.factory.WrapperFactory;
import android.graphics.Color;

public class StyleConfig extends Config {
	
	public int id = WrapperFactory.INVALID;
	
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
