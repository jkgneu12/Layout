package config;

import java.util.ArrayList;

public class ScreenConfig {
	
	private ArrayList<Integer> controlIds;
	private int id;
	
	public ScreenConfig() {
	}


	
	
	public ArrayList<Integer> getControlIds() {
		return controlIds;
	}

	public void setControlIds(ArrayList<Integer> controlIds) {
		this.controlIds = controlIds;
	}
	

	public int getId(){
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
