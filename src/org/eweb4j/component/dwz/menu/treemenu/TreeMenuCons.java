package org.eweb4j.component.dwz.menu.treemenu;

import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;
import org.eweb4j.util.RegexList;

public class TreeMenuCons {
	private final static String propId = "TreeMenuConstant";
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
		path = MVCConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", "操作成功", rel, path, "reloadTab", title)
				.toString();
	}

	public static String DWZ_SUCCESS_JSON_CLOSE_CURRENT() {
		String rel = map.get("SHOW_LIST_REL");
		String path = map.get("SHOW_LIST_PATH");
		path = MVCConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", "操作成功", rel, path, "closeCurrent", title)
				.toString();
	}

	public static String ROOT_UL_STYLE_CLASS() {
		String defaultVal = "tree treeFolder";
		String val = map.get("ROOT_UL_STYLE_CLASS");
		if (val == null)
			val = defaultVal;

		return val;
	}

	public static long TOP_TREE_MENU_ID() {
		String id = map.get("TOP_TREE_MENU_ID");

		if (id.matches(RegexList.integer_regexp))
			return Long.parseLong(id);

		return -1;
	}

	public static String TOP_TREE_MENU_NAME() {
		return map.get("TOP_TREE_MENU_NAME");
	}

	public static String NEW_ACTION_RESULT() {
		return map.get("NEW_ACTION_RESULT");
	}

	public static String EDIT_ACTION_RESULT() {
		return map.get("EDIT_ACTION_RESULT");
	}

	public static String LOOKUP_ACTION_RESULT() {
		return map.get("LOOKUP_ACTION_RESULT");
	}

	public static String PAGING_ACTION_RESULT() {
		return map.get("PAGING_ACTION_RESULT");
	}

	public static String MENU_NOT_SELECTED_MESS() {
		return "请选择菜单";
	}
}
