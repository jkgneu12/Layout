package config;

import java.util.ArrayList;

import ui.factory.MeasureFactory;
import ui.factory.StyleFactory;
import ui.factory.WrapperFactory;
import enums.EAlignment;
import enums.ELayoutType;
import enums.EWrapperType;

public class WrapperConfig extends Config {
	
	
	public String title;
	
	public int styleId = StyleFactory.INVALID;
	
	public EWrapperType type;
	
	public int width = MeasureFactory.INVALID;
	public int height = MeasureFactory.INVALID;
	
	public int marginLeft = 0;
	public int marginTop = 0;
	public int marginRight = 0;
	public int marginBottom = 0;
	
	public EAlignment screenAlignment = EAlignment.LEFT;
	public EAlignment innerAlignment = EAlignment.LEFT;
	
	public ELayoutType layoutType;

	public ArrayList<Integer> childWrapperIds;
	public ArrayList<Integer> targetWrapperIds;
	
	public int navigationId = WrapperFactory.INVALID;

	public int rowId = WrapperFactory.INVALID;

	
	
	
	public WrapperConfig(){}

	
}
