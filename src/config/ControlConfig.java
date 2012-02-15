package config;

import java.util.ArrayList;

import android.graphics.Color;
import android.view.Gravity;

public class ControlConfig {
	
	public static final int INVALID = -1000;
	
	private String title;
	
	private int id = INVALID;
	
	private ControlType type;
	
	private int width = INVALID;
	private int height = INVALID;
	
	private int paddingLeft = INVALID;
	private int paddingTop = INVALID;
	private int paddingRight = INVALID;
	private int paddingBottom = INVALID;
	
	private int marginLeft = INVALID;
	private int marginTop = INVALID;
	private int marginRight = INVALID;
	private int marginBottom = INVALID;
	
	private Alignment screenAlignment = Alignment.LEFT;
	private Alignment innerAlignment = Alignment.LEFT;

	private int backgroundColor = Color.TRANSPARENT;
	
	private String layoutType;
	
	private int targetScreenId = 1;

	private ArrayList<Integer> controlIds;
	
	
	public ControlConfig(){
		
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ControlType getType() {
		return type;
	}

	public void setType(ControlType type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public int getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	public int getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	public int getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}
	
	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}

	public int getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}

	public Alignment getScreenAlignment() {
		return screenAlignment;
	}

	public void setScreenAlignment(Alignment alignment) {
		this.screenAlignment = alignment;
	}
	
	public Alignment getInnerAlignment() {
		return innerAlignment;
	}

	public void setInnerAlignment(Alignment innerAlignment) {
		this.innerAlignment = innerAlignment;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
	}
	
	public String getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}
	
	public int getTargetScreenId() {
		return targetScreenId;
	}

	public void setTargetScreenId(int targetScreenId) {
		this.targetScreenId = targetScreenId;
	}

	public ArrayList<Integer> getControlIds() {
		return controlIds;
	}

	public void setControlIds(ArrayList<Integer> controlIds) {
		this.controlIds = controlIds;
	}


	public enum ControlType {
		BUTTON,
		TEXT,
		SCREENHOST,
		REGION
	}
	
	public enum Alignment {
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
	}


	
}
