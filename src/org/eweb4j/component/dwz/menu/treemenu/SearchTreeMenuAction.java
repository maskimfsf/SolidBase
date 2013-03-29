package org.eweb4j.component.dwz.menu.treemenu;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("${TreeMenuConstant.MODEL_NAME}")
public class SearchTreeMenuAction extends TreeMenuBaseAction {

	@GET
	@POST
	@Path("/search")
	public String doSearch(Map<String, Object> model) {
		try {
			model.put("listPage", service.getSearchResult(keyword, pageNum, numPerPage));
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return TreeMenuCons.PAGING_ACTION_RESULT();
	}
}
