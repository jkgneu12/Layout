package config;

import java.util.ArrayList;

import android.graphics.Color;
import enums.EAlignment;
import enums.ELayoutType;
import enums.EWrapperType;

public class Config {
	
	public static final int INVALID = -1000;
	
	public String title;
	
	public int id = INVALID;
	
	public EWrapperType type;
	
	public int width = INVALID;
	public int height = INVALID;
	
	public int paddingLeft = 0;
	public int paddingTop = 0;
	public int paddingRight = 0;
	public int paddingBottom = 0;
	
	public int marginLeft = 0;
	public int marginTop = 0;
	public int marginRight = 0;
	public int marginBottom = 0;
	
	public EAlignment screenAlignment = EAlignment.LEFT;
	public EAlignment innerAlignment = EAlignment.LEFT;

	public int backgroundColor = Color.TRANSPARENT;
	public int textColor = Color.BLACK;
	
	public ELayoutType layoutType;

	public ArrayList<Integer> childWrapperIds;
	public ArrayList<Integer> targetWrapperIds;
	
	public int navigationId = INVALID;

	
	
	
	public Config(){}
	
}
