package org.eweb4j.component.dwz.menu.treemenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.menu.MenuException;

@Path("${TreeMenuConstant.MODEL_NAME}")
public class LookupTreeMenuParentsAction extends TreeMenuBaseAction {

	@GET
	@POST
	@Path("/{navMenuId}/lookup")
	public String doLookup(Map<String, Object> model) {
		try {
			model.put("listPage", service.getParentsSearchResult(navMenuId, treeMenuId, keyword, pageNum, numPerPage));
			model.put("random", Math.random());
		} catch (MenuException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return TreeMenuCons.LOOKUP_ACTION_RESULT();
	}

	@GET
	@POST
	@Path("/{navMenuId}/lookupSearch")
	public String doLookupSearch(Map<String, Object> model) {
		return this.doLookup(model);
	}
}
