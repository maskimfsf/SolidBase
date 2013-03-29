package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;

public class NavMenuCons {
	private final static String propId = "NavMenuConstant";
	private final static Map<String, String> map = Props.getMap(propId);

	public static String IOC_SERVICE_BEAN_ID() {
		return map.get("IOC_SERVICE_BEAN_ID");
	}

	public static String MODEL_NAME() {
		return map.get("MODEL_NAME");
	}

	public static String DWZ_SUCCESS_JSON_RELOAD_NAVTAB() {
		String rel = map.get("SHOW_LIST_REL");
		String path = map.get("SHOW_LIST_PATH");
		String title = map.get("SHOW_LIST_TITLE");
		path = MVCConfigConstant.BASE_URL + path;
		return new CallBackJson("200", "操作成功", rel, path, "reloadTab", title).toString();
	}

	public static String DWZ_SUCCESS_JSON_CLOSE_CURRENT() {
		String rel = map.get("SHOW_LIST_REL");
		String path = map.get("SHOW_LIST_PATH");
		path = MVCConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", "操作成功", rel, path, "closeCurrent", title).toString();
	}

	public static String DEFAULT_NAV_MENU_NAME() {
		return map.get("DEFAULT_NAV_MENU_NAME");
	}

	public static String DEFAULT_NAV_MENU_HREF() {
		return map.get("DEFAULT_NAV_MENU_HREF");
	}

	public static String NEW_ACTION_RESULT() {
		return map.get("NEW_ACTION_RESULT");
	}

	public static String EDIT_ACTION_RESULT() {
		return map.get("EDIT_ACTION_RESULT");
	}

	public static String PAGING_ACTION_RESULT() {
		return map.get("PAGING_ACTION_RESULT");
	}

	public static String LOOKUP_ACTION_RESULT() {
		return map.get("LOOKUP_ACTION_RESULT");
	}

	public static String MENU_NOT_SELECTED_MESS() {
		return "请选择菜单";
	}
}
