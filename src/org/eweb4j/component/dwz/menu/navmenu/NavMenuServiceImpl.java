package org.eweb4j.component.dwz.menu.navmenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.component.dwz.menu.MenuCons;
import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.SearchForm;

public class NavMenuServiceImpl implements NavMenuService {
	private SearchForm searchForm = new SearchForm(NavMenuCons.MODEL_NAME() + "/search", "");
	private Collection<NavMenu> pojos = null;
	private NavMenuDAO navMenuDAO = null;

	// IOC注入
	public void setNavMenuDAO(NavMenuDAO navMenuDAO) {
		if (this.navMenuDAO == null)
			this.navMenuDAO = navMenuDAO;
	}

	public ListPage getPage(int p, int n) throws MenuException {
		pojos = navMenuDAO.getPage(p, n);
		int maxBtn = Integer.parseInt(DWZCons.DIV_PAGE_BTN_MAX_SHOW());
		return new ListPage(NavMenuCons.MODEL_NAME(), searchForm, pojos, new DivPageComp(p, n, navMenuDAO.countAll(), maxBtn));
	}

	public EditPage<NavMenu> getEditPage(Long id) throws MenuException {
		NavMenu navMenu = null;
		navMenu = navMenuDAO.getOne(id);
		if (navMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		return new EditPage<NavMenu>(NavMenuCons.MODEL_NAME(),
				NavMenuCons.MODEL_NAME() + "/" + navMenu.getNavMenuId(),
				navMenu);
	}

	public NavMenu getOne(Long id) throws MenuException {
		NavMenu navMenu = null;
		navMenu = navMenuDAO.getOne(id);
		if (navMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		return navMenu;
	}

	public void update(NavMenu navMenu) throws MenuException {
		Long id = navMenu.getNavMenuId();
		if (null == id || id <= 0)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		NavMenu db_NavMenu = navMenuDAO.getOne(id);
		if (db_NavMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		NavMenu other_NavMenu = navMenuDAO
				.getOtherByName(id, navMenu.getName());
		if (null != other_NavMenu)
			throw new MenuException(MenuCons.DUP_MENU_NAME_MESS());

		navMenuDAO.update(navMenu);
	}

	public void add(NavMenu navMenu) throws MenuException {
		NavMenu tmp_NavMenu = null;
		tmp_NavMenu = navMenuDAO.getOneByName(navMenu.getName());
		if (tmp_NavMenu != null)
			throw new MenuException(MenuCons.DUP_MENU_NAME_MESS());

		navMenuDAO.create(navMenu);
	}

	public String getAllFormatJson() throws MenuException {
		Collection<NavMenu> list = navMenuDAO.getAll();
		return list == null ? "[]" : list.toString();
	}

	public void batchRemove(Long[] ids) throws MenuException {
		List<NavMenu> navMenus = new ArrayList<NavMenu>(ids.length);
		for (Long id : ids) {
			NavMenu nm = new NavMenu();
			nm.setNavMenuId(id);
			navMenus.add(nm);
		}
		navMenuDAO.batchDelete(navMenus.toArray(new NavMenu[]{}));
	}

	public void removeOne(Long id) throws MenuException {
		if (id == null || id.equals(0))
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		NavMenu navMenu = navMenuDAO.getOne(id);
		if (navMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		navMenuDAO.deleteOne(id);
	}

	public ListPage getSearchResult(String keyword, int p, int n)
			throws MenuException {
		if (keyword == null || keyword.length() == 0) {
			searchForm.setKeyword("");
			return getPage(p, n);
		}

		Collection<NavMenu> navMenuList = navMenuDAO.searchAndPaging(keyword, p, n);
		searchForm.setKeyword(keyword);
		long count = navMenuDAO.countSearch(keyword);
		int maxBtn = Integer.parseInt(DWZCons.DIV_PAGE_BTN_MAX_SHOW());
		return new ListPage(NavMenuCons.MODEL_NAME(), searchForm, navMenuList, new DivPageComp(p, n, count, maxBtn));
	}

	public NavMenu getOneByName(String name) throws MenuException {
		NavMenu navMenu = this.navMenuDAO.getOneByName(name);
		if (navMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		return navMenu;
	}

	public NavMenu createDefaultNavMenu(String navMenuName) throws MenuException {
		if (navMenuName == null || navMenuName.trim().length() == 0)
			navMenuName = NavMenuCons.DEFAULT_NAV_MENU_NAME();

		NavMenu navMenu = new NavMenu();
		navMenu.setHref(NavMenuCons.DEFAULT_NAV_MENU_HREF());
		navMenu.setName(NavMenuCons.DEFAULT_NAV_MENU_NAME());

		String[] fields = { "href", "name" };
		Object[] values = { navMenu.getHref(),
				navMenu.getName() };

		navMenu.setNavMenuId(this.navMenuDAO.insert(fields, values));

		return navMenu;
	}

	public Collection<NavMenu> findAllOrderBy(String orderField, int orderType) throws MenuException {
		return navMenuDAO.selectAllOrderBy(orderField, orderType);
	}
}
