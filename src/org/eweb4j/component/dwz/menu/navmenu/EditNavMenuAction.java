package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.action.annotation.ShowValMess;

@Path("${NavMenuConstant.MODEL_NAME}")
public class EditNavMenuAction extends NavMenuBaseAction {

	@GET
	@POST
	@Path("/{id}/edit")
	@ShowValMess("dwzJson")
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("switchAction", NavMenuCons.DEFAULT_NAV_MENU_HREF());
			model.put("editPage", service.getEditPage(id));
		} catch (MenuException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return NavMenuCons.EDIT_ACTION_RESULT();
	}

	@PUT
	@Path("/{navMenuId}")
	@ShowValMess("dwzJson")
	public String doUpdate(@QueryParam("navMenuId") long navMenuId) {
		try {
			navMenu.setNavMenuId(navMenuId);
			service.update(navMenu);

			return NavMenuCons.DWZ_SUCCESS_JSON_RELOAD_NAVTAB();
		} catch (MenuException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
