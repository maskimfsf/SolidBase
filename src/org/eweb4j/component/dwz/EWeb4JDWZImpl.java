package org.eweb4j.component.dwz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.navmenu.NavMenuService;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenuCons;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenuService;
import org.eweb4j.component.dwz.view.ATag;
import org.eweb4j.component.dwz.view.Accordion;
import org.eweb4j.component.dwz.view.LiTag;
import org.eweb4j.component.dwz.view.UlTag;
import org.eweb4j.mvc.view.CallBackJson;

public class EWeb4JDWZImpl implements DWZ {

	private String rootUlStyleClass = TreeMenuCons.ROOT_UL_STYLE_CLASS();
	private TreeMenuService treeMenuService = null;
	private NavMenuService navMenuService = null;
	private List<Long> permissions = null;

	public void setRootUlStyleClass(String styleClass) {
		this.rootUlStyleClass = styleClass;
	}
	
	public void setTreeMenuPermissions(List<Long> treemenus) {
		this.permissions = treemenus;
	}

	// IOC注入
	public void setTreeMenuService(TreeMenuService treeMenuService) {
		if (this.treeMenuService == null)
			this.treeMenuService = treeMenuService;
	}

	// IOC注入
	public void setNavMenuService(NavMenuService navMenuService) {
		if (this.navMenuService == null)
			this.navMenuService = navMenuService;
	}

	public CallBackJson getSuccessJson(String message, String navTabId,
			String forwardUrl, String callType, String title) {
		return new CallBackJson("200", message, navTabId, forwardUrl, callType,
				title);
	}

	public CallBackJson getFailedJson(String message) {
		return new CallBackJson("300", message, "", "", "reload", "");
	}

	/**
	 * 通过给定的导航菜单名字查询数据库,得到导航菜单的ID,然后创建滑动菜单树
	 */
	public String getAccordion(String navMenuName, boolean noAuth)
			throws Exception {

		NavMenu navMenu = this.navMenuService.getOneByName(navMenuName);
		if (navMenu == null)
			navMenu = this.navMenuService.createDefaultNavMenu(navMenuName);

		return getAccordion(navMenu.getNavMenuId(), noAuth);
	}

	public String createAllTreeMenu() throws Exception {

		Collection<NavMenu> navMenus = navMenuService.findAllOrderBy("rank", 1);
		if (navMenus == null || navMenus.size() == 0)
			return "";

		List<LiTag> topLiList = new ArrayList<LiTag>();
		LiTag topLi = new LiTag();
		ATag topA = new ATag();
		topA.setValue("全部菜单");
		topLi.setA(topA);

		List<LiTag> navLiList = new ArrayList<LiTag>();

		UlTag navUl = new UlTag();
		navUl.setClazz("");
		navUl.setLi(navLiList);
		topLi.setUl(navUl);

		topLiList.add(topLi);

		for (NavMenu navMenu : navMenus) {
			ATag navA = new ATag();
			LiTag navLi = new LiTag();
			navA.setValue(navMenu.getName());
			navLi.setA(navA);

			if (navMenu.getHref().endsWith(".jsp")
					|| navMenu.getHref().endsWith(".html")) {
				navA.setTname("navMenuIds");
				navA.setTvalue(navMenu.getNavMenuId());
				navLiList.add(navLi);
				continue;
			}

			List<TreeMenu> parent = treeMenuService.getTopParent(navMenu
					.getNavMenuId());

			if (parent == null || parent.size() == 1)
				continue;

			List<LiTag> treeLiList = new ArrayList<LiTag>();

			UlTag treeUl = new UlTag();
			treeUl.setClazz("");
			treeUl.setLi(treeLiList);

			navLi.setUl(treeUl);

			for (TreeMenu tm : parent) {
				if (tm.getTreeMenuId() == TreeMenuCons.TOP_TREE_MENU_ID())
					continue;

				ATag a = new ATag();
				a.setValue(tm.getName());

				treeLiList.add(new LiTag(a, new UlTag("", createTree(
						tm.getTreeMenuId(), true, true))));
			}

			/* 没有任何子菜单的导航菜单不应该被显示出来 */
			if (treeLiList.size() > 0) {
				navLiList.add(navLi);
			}

		}

		UlTag top = new UlTag(rootUlStyleClass, topLiList);

		return top.toString();
	}

	public String createTreeMenu(String navMenuName, boolean noAuth)
			throws Exception {
		NavMenu navMenu = this.navMenuService.getOneByName(navMenuName);
		if (navMenu == null)
			navMenu = this.navMenuService.createDefaultNavMenu(navMenuName);

		List<LiTag> topLiList = new ArrayList<LiTag>();
		LiTag topLi = new LiTag();
		ATag topA = new ATag();
		topA.setValue("全部菜单");
		topLi.setA(topA);

		List<LiTag> liList = new ArrayList<LiTag>();

		UlTag topUl = new UlTag();
		topUl.setClazz("");
		topUl.setLi(liList);
		topLi.setUl(topUl);

		topLiList.add(topLi);

		List<TreeMenu> parent = treeMenuService.getTopParent(navMenu
				.getNavMenuId());
		for (TreeMenu tm : parent) {
			if (tm.getTreeMenuId() == TreeMenuCons.TOP_TREE_MENU_ID())
				continue;

			ATag a = new ATag();
			a.setValue(tm.getName());
			UlTag chidren = new UlTag("", createTree(tm.getTreeMenuId(), true));
			liList.add(new LiTag(a, chidren));
		}

		UlTag top = new UlTag(rootUlStyleClass, topLiList);

		return top.toString();
	}

	private Long findPerm(Long id) {
		if (permissions == null)
			return -1L;

		for (Long treemenuId : permissions) {
			if (treemenuId.equals(id)) {
				return treemenuId;
			}
		}

		return -1L;
	}

	private TreeMenu findTreeMenu(List<TreeMenu> list) throws MenuException {
		if (permissions == null || list == null)
			return null;

		for (TreeMenu tm : list) {
			List<TreeMenu> children = treeMenuService.getChildren(tm
					.getTreeMenuId());

			if (children != null && children.size() > 0)
				return findTreeMenu(children);

			for (Long id : this.permissions) {
				if (tm.getTreeMenuId().equals(id)) {
					return tm;
				}
			}
		}

		return null;
	}

	/**
	 * 创建
	 */
	public String getAccordion(Long navMenuId, final boolean noAuth) throws Exception {

		boolean flag = false;

		if (noAuth)
			flag = true;

		else if (this.permissions != null && this.permissions.size() > 0) {

			for (Long treemenuId : permissions) {
				NavMenu navmenu = this.treeMenuService
						.findNavMenuByTreeMenuId(treemenuId);
				if (navmenu == null
						|| !navMenuId.equals(navmenu.getNavMenuId()))
					continue;

				flag = true;// 表示当前导航菜单可以显示
				break;
			}
		}

		if (!flag)
			return "";

		List<TreeMenu> topParent = this.treeMenuService.getTopParent(navMenuId);

		if (topParent == null)
			return "";

		StringBuilder sb = new StringBuilder();

		for (Iterator<TreeMenu> it = topParent.iterator(); it.hasNext();) {
			TreeMenu m = it.next();
			if (m.getTreeMenuId() == TreeMenuCons.TOP_TREE_MENU_ID())
				continue;

			/* 如果不需要权限判断 */
			if (noAuth) {
				sb.append(new Accordion(m.getName(), getUlTag(m.getTreeMenuId(), noAuth)).toString());
				continue;
			}


			Long treeMenuId = m.getTreeMenuId();
			List<TreeMenu> children = treeMenuService.getChildren(treeMenuId);
			/* 如果是叶子节点则直接判断权限 */
			if (children == null && !findPerm(treeMenuId).equals(-1L))
				continue;

			// 如果是父节点，则判断孩子中是否找到与权限匹配的，如果有，它自身就通过,否则跳过
			if (findTreeMenu(children) == null)
				continue;

			sb.append(new Accordion(m.getName(), getUlTag(m.getTreeMenuId(), noAuth)).toString());
		}

		return sb.toString();
	}

	public String createTreeMenu(Long treeMenuId, boolean noAuth)
			throws Exception {
		return getUlTag(treeMenuId, noAuth).toString();
	}

	public UlTag getUlTag(Long treeMenuId, boolean noAuth) throws Exception {
		return new UlTag(rootUlStyleClass, createTree(treeMenuId, noAuth));
	}

	private List<LiTag> createTree(Long pid, boolean noAuth) throws Exception {
		return this.createTree(pid, false, noAuth);
	}

	private List<LiTag> createTree(final Long pid, final boolean isCheckbox,
			final boolean noAuth) throws Exception {
		List<LiTag> liList = new ArrayList<LiTag>();
		List<TreeMenu> children = this.treeMenuService.getChildren(pid);
		if (children == null)
			return liList;

		for (Iterator<TreeMenu> it = children.iterator(); it.hasNext();) {
			TreeMenu m = it.next();

			if (!noAuth && this.findPerm(m.getTreeMenuId()) == -1L)
				continue;

			if (isCheckbox) {
				ATag a = new ATag();
				if (treeMenuService.getChildren(m.getTreeMenuId()) == null) {
					a.setTname("treeMenuIds");
					a.setTvalue(m.getTreeMenuId());
				}

				a.setValue(m.getName());
				liList.add(new LiTag(a, new UlTag("", createTree(
						m.getTreeMenuId(), isCheckbox, noAuth))));
			} else {
				liList.add(new LiTag(new ATag(null, null, false, m.getTarget(),
						m.getWidth() + "", m.getHeight() + "", m.getRel(), 
						m.getExternal(), m.getReloadFlag(), m.getHref(),
						m.getName()), new UlTag("", createTree(
						m.getTreeMenuId(), isCheckbox, noAuth))));
			}

		}

		return liList;
	}

}
