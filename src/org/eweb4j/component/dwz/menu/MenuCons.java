package org.eweb4j.component.dwz.menu;

import java.util.Map;

import org.eweb4j.cache.Props;


public class MenuCons {
	private final static String propId = "MenuConstant";
	private final static Map<String, String> map = Props.getMap(propId);
	
	public static String MENU_NOT_FOUND_MESS(){
		return map.get("MENU_NOT_FOUND_MESS");
	}
	
	public static String DUP_MENU_NAME_MESS(){
		return map.get("DUP_MENU_NAME_MESS");
	}
	
	public static String PARENT_MENU_NOT_FOUND_MESS(){
		return map.get("PARENT_MENU_NOT_FOUND_MESS");
	}
	
	public static String NAV_MENU_NOT_FOUND_MESS(){
		return map.get("NAV_MENU_NOT_FOUND_MESS");
	}
	
	public static String INVALID_PARENT_MESS(){
		return map.get("INVALID_PARENT_MESS");
	}
}
