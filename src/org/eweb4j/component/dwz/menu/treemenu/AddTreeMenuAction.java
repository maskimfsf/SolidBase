package org.eweb4j.component.dwz.menu.treemenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.action.annotation.ShowValMess;


@Path("${TreeMenuConstant.MODEL_NAME}")
public class AddTreeMenuAction extends TreeMenuBaseAction {
	@GET
	@POST
	@Path("/new")
	public String doNew(Map<String, Object> model) {
		model.put("model", TreeMenuCons.MODEL_NAME());

		return TreeMenuCons.NEW_ACTION_RESULT();
	}

	@POST
	@Path("/")
	@ShowValMess("dwzJson")
	public String doAdd(@QueryParam("navmenus.dwz_navMenu2.navMenuId") Long navMenuId, @QueryParam("treemenus.dwz_treeMenu2.treeMenuId") Long pid) {
		try {
			service.addWithCascade(treeMenu, navMenuId, pid);

			return TreeMenuCons.DWZ_SUCCESS_JSON_CLOSE_CURRENT();
		} catch (MenuException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
