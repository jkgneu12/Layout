package config;

import java.util.ArrayList;

import enums.ELayoutType;

public class ScreenConfig {
	
	private ArrayList<Integer> controlIds;
	private String layoutType;
	private int id;
	
	public ScreenConfig() {
	}


	
	
	public ArrayList<Integer> getControlIds() {
		return controlIds;
	}

	public void setControlIds(ArrayList<Integer> controlIds) {
		this.controlIds = controlIds;
	}
	
	public ELayoutType getLayoutType() {
		return ELayoutType.stringToLayoutType(layoutType);
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}

	public int getId(){
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
