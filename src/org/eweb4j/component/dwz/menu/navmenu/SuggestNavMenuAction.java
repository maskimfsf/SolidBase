package org.eweb4j.component.dwz.menu.navmenu;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("${NavMenuConstant.MODEL_NAME}")
public class SuggestNavMenuAction extends NavMenuBaseAction {

	@GET
	@POST
	@Path("/suggest.json")
	public String doSuggest() {
		try {
			return service.getAllFormatJson();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

}
