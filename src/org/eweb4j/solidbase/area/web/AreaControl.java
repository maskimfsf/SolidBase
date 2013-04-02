package org.eweb4j.solidbase.area.web;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eweb4j.cache.Props;
import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.orm.Db;
import org.eweb4j.solidbase.area.model.Area;
import org.eweb4j.solidbase.area.model.AreaException;
import org.eweb4j.solidbase.area.model.AreaService;
import org.eweb4j.solidbase.area.model.AreaServiceImpl;
import org.eweb4j.solidbase.mapper.model.MapperService;
import org.eweb4j.solidbase.mapper.model.MapperServiceImpl;
import org.eweb4j.util.CommonUtil;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-1 上午09:45:54
 */
@Path("/area")
public class AreaControl {

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private AreaService areaService = new AreaServiceImpl();
	private MapperService mapperService = new MapperServiceImpl();
	private int pageNum = 1;
	private int numPerPage = 20;
	private Area area = null;
	private Long id = null;
	private Long[] ids = null;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public void setArea(Area area){
		this.area = area;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setIds(Long[] ids){
		this.ids = ids;
	}
	
	@Path("/{mapId}/all.json")
	public String getAllByMapId(@PathParam("mapId")Long mapId){
		Collection<Area> areas = Db.ar(Area.class).find("byMapper", mapId).fetch();
		return CommonUtil.toJson(areas);
	}
	
	@Path("/image")
	public String image(@QueryParam("savePath") String savePath){
		MVC.ctx().getModel().put("savePath", savePath);
		return "jsp:mapper/view/area/image.jsp";
	}
	
	@Path("/{id}/draw")
	public String doDraw(@PathParam("id")Long id){
		try {
			Area area = areaService.get(id);
			MVC.ctx().getModel().put("area", area);
			MVC.ctx().getModel().put("colors", Area.allColors());
		} catch (AreaException e) {
			e.printStackTrace();
		}
		
		return "jsp:mapper/view/area/draw.jsp";
	}
	
	@Path("/{id}/edit_draw")
	public String editDraw(@PathParam("id")Long id){
		MVC.ctx().getModel().put("id", id);
		return "jsp:mapper/view/area/edit_draw.jsp";
	}
	
	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {
		try {
			PageMod<Area> pageMod = areaService.getPage(pageNum, numPerPage);

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, pageMod.getAllCount() - 1, 8);
			SearchForm searchForm = new SearchForm("area/list", "");
			ListPage listPage = new ListPage("area", searchForm, pageMod.getPojos(), dpc);
			listPage = DataAssemUtil.assemHead(listPage, pageMod.getPojos(), Props.getMap("AreaConstant"));

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:mapper/view/area/list.jsp";
	}
	
	@Path("/new")
	@GET
	@POST
	public String doGet(Map<String, Object> model) {
		try {
			model.put("mappers", mapperService.getAllMappers());
			model.put("colors", Area.allColors());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp:mapper/view/area/add.jsp";
	}
	
	@Path("/")
	@POST
	public String doPost() {
		try {
			areaService.create(area);
			return dwz.getSuccessJson("create ok", "xssytxrd", MVCConfigConstant.BASE_URL+"mapper/list", "dialog", "热点管理").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}/edit")
	@GET
	@POST
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("mappers", mapperService.getAllMappers());
			model.put("pojo", areaService.get(id));
			model.put("colors", Area.allColors());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:mapper/view/area/edit.jsp";
	}
	
	@Path("/{id}")
	@PUT
	public String doPut() {
		try {
			area.setId(id);
			areaService.update(area);
			return dwz.getSuccessJson("update ok", "xssytxrd", MVCConfigConstant.BASE_URL+"area/list", "dialog", "热点管理").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}")
	@DELETE
	public String doDelete() {
		try {
			areaService.remove(id);
			return dwz.getSuccessJson("delete ok", "xssytxrd", MVCConfigConstant.BASE_URL+"area/list", "dialog", "热点管理").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/batchRemove")
	@DELETE
	public String doBatchDelete() {
		try {
			areaService.batchRemove(ids);
			return dwz.getSuccessJson("delete ok", "xssytxrd", MVCConfigConstant.BASE_URL+"area/list", "dialog", "热点管理").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
}
