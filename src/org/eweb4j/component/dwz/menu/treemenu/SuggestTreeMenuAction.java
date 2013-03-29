package org.eweb4j.component.dwz.menu.treemenu;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.validator.annotation.Validate;


@Path("${TreeMenuConstant.MODEL_NAME}")
public class SuggestTreeMenuAction extends TreeMenuBaseAction {
	@GET
	@POST
	@Path("/{navMenuId}/suggest.json")
	@Validate("navMenuId")
	public String doSuggest() {
		try {
			return service.getParentsFormatJson(navMenuId, treeMenuId);
		} catch (Exception e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
