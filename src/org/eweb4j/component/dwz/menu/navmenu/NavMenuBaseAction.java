package org.eweb4j.component.dwz.menu.navmenu;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;

/**
 * 抽象类 BaseAction
 * 
 * @author weiwei
 * 
 */
public abstract class NavMenuBaseAction {

	protected NavMenuService service = IOC.getBean(NavMenuCons.IOC_SERVICE_BEAN_ID());

	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	protected NavMenu navMenu;

	protected Long id;
	protected Long[] ids;
	protected int pageNum = 1;
	protected int numPerPage = 20;
	protected String keyword;

	public void setNavMenu(NavMenu navMenu) {
		this.navMenu = navMenu;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
