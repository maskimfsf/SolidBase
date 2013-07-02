package org.eweb4j.solidbase.notice.web;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.MVC;
import org.eweb4j.orm.Db;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.notice.model.Notice;
import org.eweb4j.solidbase.notice.model.NoticeReader;
import org.eweb4j.util.CommonUtil;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-13 上午11:03:26
 */
@Path("/notice")
public class AddNoticeAction {
	
	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID()); 
	private Notice notice;
	private NoticeReader reader;

	@Path("/new")
	public String newEdit(){
		MVC.ctx().getModel().put("model", "notice");
		return "jsp:notice/view/add.jsp";
	}
	
	@Path("/")
	@POST
	public String doPost() {
		try {
			
			notice.setPubTime(new Date());
			Db.ar(notice).create();
			reader.setNotice(notice);
			
			reader.setReadTag(0);
			System.out.println("->"+CommonUtil.toJson(reader));
			Db.ar(reader).create();
			
			return DepartmentCons.DWZ_SUCCESS_JSON("添加信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	
	public void setReader(NoticeReader reader){
		this.reader = reader;
	}
}
