package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.action.annotation.Result;

@Path("${NavMenuConstant.MODEL_NAME}")
public class PagingNavMenuAction extends NavMenuBaseAction {

	@GET
	@POST
	@Path("/list")
	@Result("${NavMenuConstant.PAGING_ACTION_RESULT}")
	public String doPaging(Map<String, Object> model) {
		try {
			model.put("listPage", service.getPage(pageNum, numPerPage));
			model.put("random", Math.random());
		} catch (MenuException e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "success";
	}

	@GET
	@POST
	@Path("/lookup")
	@Result("${NavMenuConstant.LOOKUP_ACTION_RESULT}")
	public String doLookup(Map<String, Object> model) {
		return this.doPaging(model);
	}

}
