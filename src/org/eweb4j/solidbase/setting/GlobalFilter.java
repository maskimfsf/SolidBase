package org.eweb4j.solidbase.setting;

import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.action.annotation.Singleton;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.interceptor.Interceptor;
import org.eweb4j.mvc.interceptor.Uri;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午11:39:59
 */
@Interceptor(
	priority = -1, 
	uri = { @Uri(type = "*") }
)
@Singleton
public class GlobalFilter {

	public String doIntercept(){
		Setting setting = Setting.inst.find().first();
		String fileBaseDir = setting.getFileBaseDir();
		String base = fileBaseDir.replace("${RootPath}/", MVCConfigConstant.BASE_URL);
		
		MVC.ctx().getServletContext().setAttribute("ImageBaseURL", base);
		
		return null;
	}
	
}
