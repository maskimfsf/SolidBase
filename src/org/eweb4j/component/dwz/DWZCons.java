package org.eweb4j.component.dwz;

import java.util.Map;

import org.eweb4j.cache.Props;

public class DWZCons {
	private final static String propId = "DWZConstant";
	private final static Map<String, String> map = Props.getMap(propId);

	public static String ERROR_ATTR_NAME() {
		return map.get("ERROR_ATTR_NAME");
	}

	public static String ERROR_PAGE() {
		return map.get("ERROR_PAGE");
	}

	public static String IOC_DWZ_BEAN_ID() {
		return map.get("IOC_DWZ_BEAN_ID");
	}

	public static String DIV_PAGE_BTN_MAX_SHOW() {
		return map.get("DIV_PAGE_BTN_MAX_SHOW");
	}

}
