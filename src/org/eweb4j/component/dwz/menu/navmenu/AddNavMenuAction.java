package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.action.annotation.ShowValMess;

@Path("${NavMenuConstant.MODEL_NAME}")
public class AddNavMenuAction extends NavMenuBaseAction {

	@GET
	@POST
	@Path("/new")
	public String doNew(Map<String, Object> model) {
		model.put("model", NavMenuCons.MODEL_NAME());
		model.put("switchAction", NavMenuCons.DEFAULT_NAV_MENU_HREF());
		return NavMenuCons.NEW_ACTION_RESULT();
	}

	@POST
	@Path("/")
	@ShowValMess("dwzJson")
	public String doAdd() {
		try {
			service.add(navMenu);
			return NavMenuCons.DWZ_SUCCESS_JSON_RELOAD_NAVTAB();
		} catch (MenuException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
