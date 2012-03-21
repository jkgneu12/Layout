package ui.factory;

import ui.activity.BaseActivity;
import ui.wrapper.Wrapper;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import enums.EWrapperType;

public class MeasureFactory {
	
	public static final int INVALID = -1000;

	
	public static int resolveWidth(int base, BaseActivity activity){
		if(base >= 0 || base == INVALID)
			return base;
		return (int) (activity.getScreenWidth() * ((double)-base / 100.0));
	}
	
	public static int resolveHeight(int base, BaseActivity activity){
		if(base >= 0 || base == INVALID)
			return base;
		return (int) (activity.getScreenHeight() * ((double)-base / 100.0));
	}
	
	
	public static int getWidth(Wrapper wrapper){
		return resolveWidth(wrapper.getConfig().width, wrapper.getActivity());
	}
	
	public static int getHeight(Wrapper wrapper){
		return resolveHeight(wrapper.getConfig().height, wrapper.getActivity());
	}
	
	
	public static int getMarginLeft(Wrapper wrapper){
		return resolveWidth(wrapper.getConfig().marginLeft, wrapper.getActivity());
	}
	
	public static int getMarginRight(Wrapper wrapper){
		return resolveWidth(wrapper.getConfig().marginRight, wrapper.getActivity());
	}
	
	public static int getMarginTop(Wrapper wrapper){
		return resolveHeight(wrapper.getConfig().marginTop, wrapper.getActivity());
	}
	
	public static int getMarginBottom(Wrapper wrapper){
		return resolveHeight(wrapper.getConfig().marginBottom, wrapper.getActivity());
	}
	
	
	public static int getPaddingLeft(Wrapper wrapper){
		return resolveWidth(wrapper.getStyleConfig().paddingLeft, wrapper.getActivity());
	}
	
	public static int getPaddingRight(Wrapper wrapper){
		return resolveWidth(wrapper.getStyleConfig().paddingRight, wrapper.getActivity());
	}
	
	public static int getPaddingTop(Wrapper wrapper){
		return resolveHeight(wrapper.getStyleConfig().paddingTop, wrapper.getActivity());
	}
	
	public static int getPaddingBottom(Wrapper wrapper){
		return resolveHeight(wrapper.getStyleConfig().paddingBottom, wrapper.getActivity());
	}
	
	
	public static int addPaddingToWidth(Wrapper wrapper) {
		int width = getWidth(wrapper);
		if(getPaddingLeft(wrapper) != Wrapper.INVALID)
			width += getPaddingLeft(wrapper);
		if(getPaddingRight(wrapper) != Wrapper.INVALID)
			width += getPaddingRight(wrapper);
		return width;
	}
	
	public static int addPaddingToHeight(Wrapper wrapper) {
		int height = getHeight(wrapper);
		if(getPaddingTop(wrapper) != Wrapper.INVALID)
			height += getPaddingTop(wrapper);
		if(getPaddingBottom(wrapper) != Wrapper.INVALID)
			height += getPaddingBottom(wrapper);
		return height;
	}

	public static int subtractPaddingFromWidth(Wrapper wrapper) {
		int width = getWidth(wrapper);
		if(getPaddingLeft(wrapper) != Wrapper.INVALID)
			width -= getPaddingLeft(wrapper);
		if(getPaddingRight(wrapper) != Wrapper.INVALID)
			width -= getPaddingRight(wrapper);
		return width;
	}
	
	public static int subtractPaddingFromHeight(Wrapper wrapper) {
		int height = getHeight(wrapper);
		if(getPaddingTop(wrapper) != Wrapper.INVALID)
			height -= getPaddingTop(wrapper);
		if(getPaddingBottom(wrapper) != Wrapper.INVALID)
			height -= getPaddingBottom(wrapper);
		return height;
	}
	
	public static int subtractMarginFromWidth(Wrapper wrapper) {
		int width = getWidth(wrapper);
		if(getMarginLeft(wrapper) != Wrapper.INVALID)
			width -= getMarginLeft(wrapper);
		if(getMarginRight(wrapper) != Wrapper.INVALID)
			width -= getMarginRight(wrapper);
		return width;
	}
	
	public static int subtractMarginFromHeight(Wrapper wrapper) {
		int height = getHeight(wrapper);
		if(getMarginTop(wrapper) != Wrapper.INVALID)
			height -= getMarginTop(wrapper);
		if(getMarginBottom(wrapper) != Wrapper.INVALID)
			height -= getMarginBottom(wrapper);
		return height;
	}
	
	public static int getMeasuredWidth(Wrapper wrapper) {
		if(getWidth(wrapper) != INVALID)
			return getWidth(wrapper);	
		
		if(wrapper.getCalculatedWidth() != INVALID)
			return wrapper.getCalculatedWidth();
		
		
		int height = MeasureSpec.UNSPECIFIED;
		if(getHeight(wrapper) != INVALID)
			height = MeasureSpec.makeMeasureSpec(getHeight(wrapper), MeasureSpec.EXACTLY);
		if(!wrapper.getConfig().type.equals(EWrapperType.LIST) && wrapper.getView() != null){
			wrapper.getView().measure(MeasureSpec.UNSPECIFIED, height);
			return wrapper.getView().getMeasuredWidth();
		}
		return LayoutParams.WRAP_CONTENT;
	}

	
	
	public static int getMeasuredHeight(Wrapper wrapper) {
		if(getHeight(wrapper) != INVALID)
			getHeight(wrapper);
		
		if(wrapper.getCalculatedHeight() != INVALID)
			return wrapper.getCalculatedHeight();
		
		int width = MeasureSpec.UNSPECIFIED;
		if(getWidth(wrapper) != INVALID)
			width = MeasureSpec.makeMeasureSpec(getWidth(wrapper), MeasureSpec.EXACTLY);
		if(!wrapper.getConfig().type.equals(EWrapperType.LIST) && wrapper.getView() != null){
			wrapper.getView().measure(width, MeasureSpec.UNSPECIFIED);
			return wrapper.getView().getMeasuredHeight();
			
		}
		return LayoutParams.WRAP_CONTENT;
	}

	public static int getMeasuredWidthPlusMargins(Wrapper wrapper){
		 return getMeasuredWidth(wrapper) + getMarginLeft(wrapper) + getMarginRight(wrapper);
	}
	
	public static int getMeasuredHeightPlusMargins(Wrapper wrapper){
		 return getMeasuredHeight(wrapper) + getMarginTop(wrapper) + getMarginBottom(wrapper);
	}
	
	public static int getMeasuredWidthMinusPadding(Wrapper wrapper) {
		return getMeasuredWidth(wrapper) - (getPaddingLeft(wrapper) + getPaddingRight(wrapper));
	}
	
	public static int getMeasuredHeightMinusPadding(Wrapper wrapper) {
		return getMeasuredHeight(wrapper) - (getPaddingTop(wrapper) + getPaddingBottom(wrapper));
	}
	
	public static int getMeasuredWidthPlusMarginsAndPadding(Wrapper wrapper){
		 return getMeasuredWidthPlusMargins(wrapper) + getPaddingLeft(wrapper) + getPaddingRight(wrapper);
	}
	
	public static int getMeasuredHeightPlusMarginsAndPadding(Wrapper wrapper){
		 return getMeasuredHeightPlusMargins(wrapper) + getPaddingTop(wrapper) + getPaddingBottom(wrapper);
	}
	
	public static int getMaxWidth(Wrapper wrapper) {
		if(getWidth(wrapper) != INVALID)
			return getWidth(wrapper);
		if(wrapper.getParent() != null){
			int configWidth = getWidth(wrapper.getParent());
		
			if(configWidth != INVALID)
				return configWidth;
			else 
				return getMaxWidth(wrapper.getParent());
		}
		return wrapper.getActivity().getScreenWidth();
	}
	
	public static int getMaxHeight(Wrapper wrapper) {
		if(getHeight(wrapper) != INVALID)
			return getHeight(wrapper);
		if(wrapper.getParent() != null){
			int configHeight = getHeight(wrapper.getParent());
		
			if(configHeight != INVALID)
				return configHeight;
			else 
				return getMaxHeight(wrapper.getParent());
		}
		return wrapper.getActivity().getScreenHeight();
	}

}
