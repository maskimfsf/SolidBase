package org.eweb4j.solidbase.mapper.web;

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
import org.eweb4j.solidbase.files.model.FilesException;
import org.eweb4j.solidbase.files.model.FilesService;
import org.eweb4j.solidbase.files.model.FilesServiceImpl;
import org.eweb4j.solidbase.mapper.model.Mapper;
import org.eweb4j.solidbase.mapper.model.MapperException;
import org.eweb4j.solidbase.mapper.model.MapperService;
import org.eweb4j.solidbase.mapper.model.MapperServiceImpl;
import org.eweb4j.util.CommonUtil;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-1 上午09:45:54
 */
@Path("/mapper")
public class MapperControl {

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private FilesService filesService = new FilesServiceImpl();
	private MapperService mapperService = new MapperServiceImpl();
	private int pageNum = 1;
	private int numPerPage = 20;
	private Mapper mapper = null;
	private Long id = null;
	private Long[] ids = null;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public void setMapper(Mapper mapper){
		this.mapper = mapper;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setIds(Long[] ids){
		this.ids = ids;
	}
	
	@Path("/tree")
	public String doSwitchEnvToMapper(Map<String, Object> model) {
		Collection<Mapper> mappers = 
			Db.ar(Mapper.class).dao()
				.fetch("areas")
				.selectAll()
				.query();
		
		model.put("mappers", mappers);
		
		return "jsp:mapper/view/tree.jsp";
	}
	
	@Path("/all.json")
	public String getAllJson(){
		
		try {
			Collection<Mapper> mappers = mapperService.getAllMappers();
			return CommonUtil.toJson(mappers);
		} catch (MapperException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@Path("/canvas")
	public String image(@QueryParam("savePath") String savePath){
		MVC.ctx().getModel().put("savePath", savePath);
		return "jsp:mapper/view/canvas.jsp";
	}
	
	@Path("/{id}/draw")
	public String doDraw(@PathParam("id")Long id){
		try {
			Mapper mapper = mapperService.get(id);
			MVC.ctx().getModel().put("mapper", mapper);
			MVC.ctx().getModel().put("colors", Area.allColors());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsp:mapper/view/draw.jsp";
	}
	
	@Path("/{id}/edit_draw")
	public String editDraw(@PathParam("id")Long id){
		MVC.ctx().getModel().put("id", id);
		return "jsp:mapper/view/edit_draw.jsp";
	}
	
	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {
		try {
			PageMod<Mapper> pageMod = mapperService.getPage(pageNum, numPerPage);

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, pageMod.getAllCount() - 1, 8);
			SearchForm searchForm = new SearchForm("mapper/list", "");
			ListPage listPage = new ListPage("mapper", searchForm, pageMod.getPojos(), dpc);
			listPage = DataAssemUtil.assemHead(listPage, pageMod.getPojos(), Props.getMap("MapperConstant"));

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:mapper/view/list.jsp";
	}
	
	@Path("/new")
	@GET
	@POST
	public String doGet(Map<String, Object> model) {
		try {
			model.put("images", filesService.getAllImages());
		} catch (FilesException e) {
		}
		return "jsp:mapper/view/add.jsp";
	}
	
	@Path("/")
	@POST
	public String doPost() {
		try {
			mapperService.create(mapper);
			return dwz.getSuccessJson("create ok", "xssytxys", MVCConfigConstant.BASE_URL+"mapper/list", "dialog", "图像映射管理").toString();
		} catch (MapperException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}/edit")
	@GET
	@POST
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("images", filesService.getAllImages());
			model.put("pojo", mapperService.get(id));
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "jsp:mapper/view/edit.jsp";
	}
	
	@Path("/{id}")
	@PUT
	public String doPut() {
		try {
			mapper.setId(id);
			mapperService.update(mapper);
			return dwz.getSuccessJson("update ok", "xssytxys", MVCConfigConstant.BASE_URL+"mapper/list", "dialog", "图像映射管理").toString();
		} catch (MapperException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/{id}.json")
	public String get(@PathParam("id")Long id){
		Mapper mapper;
		try {
			mapper = mapperService.get(id);
			return CommonUtil.toJson(mapper);
		} catch (MapperException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Path("/{id}")
	@DELETE
	public String doDelete() {
		try {
			mapperService.remove(id);
			return dwz.getSuccessJson("delete ok", "xssytxys", MVCConfigConstant.BASE_URL+"mapper/list", "dialog", "图像映射管理").toString();
		} catch (MapperException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
	@Path("/batchRemove")
	@DELETE
	public String doBatchDelete() {
		try {
			mapperService.batchRemove(ids);
			return dwz.getSuccessJson("delete ok", "xssytxys", MVCConfigConstant.BASE_URL+"mapper/list", "dialog", "图像映射管理").toString();
		} catch (MapperException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	
}
