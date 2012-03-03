package config;

import java.util.ArrayList;

import android.graphics.Color;
import enums.EAlignment;
import enums.ELayoutType;
import enums.EWrapperType;

public class Config {
	
	public static final int INVALID = -1000;
	
	private String title;
	
	private int id = INVALID;
	
	private EWrapperType type;
	
	private int width = INVALID;
	private int height = INVALID;
	
	private int paddingLeft = 0;
	private int paddingTop = 0;
	private int paddingRight = 0;
	private int paddingBottom = 0;
	
	private int marginLeft = 0;
	private int marginTop = 0;
	private int marginRight = 0;
	private int marginBottom = 0;
	
	private EAlignment screenAlignment = EAlignment.LEFT;
	private EAlignment innerAlignment = EAlignment.LEFT;

	private int backgroundColor = Color.TRANSPARENT;
	
	private String layoutType;

	private ArrayList<Integer> childWrapperIds;
	private ArrayList<Integer> targetWrapperIds;
	
	
	public Config(){}
	
	
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

	public EWrapperType getType() {
		return type;
	}

	public void setType(String type) {
		this.type = EWrapperType.stringToWrapperType(type);
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

	public EAlignment getScreenAlignment() {
		return screenAlignment;
	}

	public void setScreenAlignment(String alignment) {
		this.screenAlignment = EAlignment.stringToAlignment(alignment);
	}
	
	public EAlignment getInnerAlignment() {
		return innerAlignment;
	}

	public void setInnerAlignment(String innerAlignment) {
		this.innerAlignment = EAlignment.stringToAlignment(innerAlignment);
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
	}
	
	public ELayoutType getLayoutType() {
		return ELayoutType.stringToLayoutType(layoutType);
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}

	public ArrayList<Integer> getChildWrapperIds() {
		return childWrapperIds;
	}

	public void setChildWrapperIds(ArrayList<Integer> childWrapperIds) {
		this.childWrapperIds = childWrapperIds;
	}

	public ArrayList<Integer> getTargetWrapperIds() {
		return targetWrapperIds;
	}

	public void setTargetWrapperIds(ArrayList<Integer> targetWrapperIds) {
		this.targetWrapperIds = targetWrapperIds;
	}
	
}
