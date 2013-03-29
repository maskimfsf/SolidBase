package org.eweb4j.component.dwz.menu.treemenu;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.validator.annotation.Int;

/**
 * 抽象类BaseAction
 * 
 * @author weiwei
 * 
 */
public abstract class TreeMenuBaseAction {
	protected TreeMenuService service = IOC.getBean(TreeMenuCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	protected TreeMenu treeMenu;

	protected Long[] ids;
	protected Long id = 0L;
	protected String keyword = "";
	protected Long treeMenuId = 0L;
	@Int(mess="导航菜单ID应该为数字格式")
	protected Long navMenuId = 0L;
	protected int pageNum = 1;
	protected int numPerPage = 20;

	public void setTreeMenu(TreeMenu treeMenu) {
		this.treeMenu = treeMenu;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public void setTreeMenuId(Long treeMenuId) {
		this.treeMenuId = treeMenuId;
	}

	public void setNavMenuId(Long navMenuId) {
		this.navMenuId = navMenuId;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
