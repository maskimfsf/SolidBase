package org.eweb4j.component.dwz.menu.treemenu;

import java.util.ArrayList;
import java.util.List;

import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.component.dwz.menu.MenuCons;
import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.navmenu.NavMenuDAO;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;

public class TreeMenuServiceImpl implements TreeMenuService {

	private SearchForm searchForm = new SearchForm(TreeMenuCons.MODEL_NAME() + "/search", "");

	private NavMenuDAO navMenuDAO = null;
	private TreeMenuDAO treeMenuDAO = null;

	// IOC注入
	public void setNavMenuDAO(NavMenuDAO navMenuDAO) {
		if (this.navMenuDAO == null)
			this.navMenuDAO = navMenuDAO;
	}

	// IOC注入
	public void setTreeMenuDAO(TreeMenuDAO treeMenuDAO) {
		if (this.treeMenuDAO == null)
			this.treeMenuDAO = treeMenuDAO;
	}

	public ListPage getPageWithCascade(int pageNum, int numPerPage)throws MenuException {
		List<TreeMenu> list = treeMenuDAO.getPageWithCascade(pageNum, numPerPage);
		int maxBtn = Integer.parseInt(DWZCons.DIV_PAGE_BTN_MAX_SHOW());
		DivPageComp dpc = new DivPageComp(pageNum, numPerPage, this.treeMenuDAO.countAll(), maxBtn);

		return new ListPage(TreeMenuCons.MODEL_NAME(), searchForm, list, dpc);
	}

	public ListPage getSearchResult(String keyword, int pageNum, int numPerPage)
			throws MenuException {

		if (keyword == null || keyword.length() == 0) {
			searchForm.setKeyword("");
			return getPageWithCascade(pageNum, numPerPage);
		}

		List<TreeMenu> list = treeMenuDAO.getSearchResult(keyword, pageNum, numPerPage);
		int maxBtn = Integer.parseInt(DWZCons.DIV_PAGE_BTN_MAX_SHOW());
		DivPageComp dpc = new DivPageComp(pageNum, numPerPage, this.treeMenuDAO.countSearch(keyword), maxBtn);

		searchForm.setKeyword(keyword);

		return new ListPage(TreeMenuCons.MODEL_NAME(), searchForm, list, dpc);
	}

	public ListPage getParentsPage(Long navMenuId, Long treeMenuId,
			int pageNum, int numPerPage) throws MenuException {
		return getParentsSearchResult(navMenuId, treeMenuId, "", pageNum,
				numPerPage);
	}

	public ListPage getParentsSearchResult(Long navMenuId, Long treeMenuId,
			String keyword, int pageNum, int numPerPage) throws MenuException {
		if (navMenuId == null || navMenuId <= 0)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		NavMenu navMenu = this.navMenuDAO.getOne(navMenuId);
		if (navMenu == null)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		if (keyword == null)
			keyword = "";

		List<TreeMenu> pojos = this.treeMenuDAO.getParentSearchResult(pageNum,
				numPerPage, keyword, navMenuId, treeMenuId);
		pojos = handleTopTreeMenu(pojos);

		long allCount = this.treeMenuDAO.countParentSearchResult(keyword,
				navMenuId, treeMenuId);
		int maxBtn = Integer.parseInt(DWZCons.DIV_PAGE_BTN_MAX_SHOW());
		DivPageComp dpc = new DivPageComp(pageNum, numPerPage, allCount, maxBtn);
		SearchForm searchForm = new SearchForm(TreeMenuCons.MODEL_NAME() + "/" + navMenuId + "/lookupSearch", "");
		searchForm.setKeyword(keyword);

		return new ListPage(TreeMenuCons.MODEL_NAME(), searchForm, pojos, dpc);
	}

	private List<TreeMenu> handleTopTreeMenu(List<TreeMenu> pojos)
			throws MenuException {
		final List<TreeMenu> result = new ArrayList<TreeMenu>();
		if (pojos == null)
			pojos = new ArrayList<TreeMenu>();

		TreeMenu top = this.treeMenuDAO.getOne(TreeMenuCons.TOP_TREE_MENU_ID());
		if (top != null) {
			pojos.add(top);
			result.addAll(pojos);
			return result;
		}

		Transaction.execute(new Trans() {
			@Override
			public void run(Object... args) throws MenuException {
				TreeMenu top = new TreeMenu();
				top.setTreeMenuId(TreeMenuCons.TOP_TREE_MENU_ID());
				top.setName(TreeMenuCons.TOP_TREE_MENU_NAME());
				String[] fields = { "treeMenuId", "name" };
				Object[] values = { top.getTreeMenuId(), top.getName() };
				TreeMenu db_top = treeMenuDAO.getOneByName(top.getName());
				if (db_top != null) {
					top.setName(top.getName() + "_");
					values[1] = top.getName();
				}

				long id = treeMenuDAO.insert(fields, values);
				if (id != TreeMenuCons.TOP_TREE_MENU_ID()) {

					treeMenuDAO.update(new String[] { "treeMenuId" },
							new Object[] { TreeMenuCons.TOP_TREE_MENU_ID() });
				}

				result.add(top);
			}
		}, "");

		result.addAll(pojos);
		return result;
	}

	public String getParentsFormatJson(Long navMenuId, Long treeMenuId)
			throws MenuException {
		if (navMenuId == null || navMenuId <= 0)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		NavMenu navMenu = this.navMenuDAO.getOne(navMenuId);
		if (navMenu == null)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		List<TreeMenu> pojos = this.treeMenuDAO
				.getParent(navMenuId, treeMenuId);
		pojos = this.handleTopTreeMenu(pojos);

		return pojos.toString();
	}

	public void batchRemove(Long[] ids) throws MenuException {
		for (Long id : ids) {
			removeOne(id);
		}
	}

	public void removeOne(Long id) throws MenuException {
		if (id == null || id < 0)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		TreeMenu treeMenu = treeMenuDAO.getOne(id);
		if (treeMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		treeMenuDAO.deleteOne(id);
	}

	public void addWithCascade(TreeMenu treeMenu, Long navMenuId, Long pid)
			throws MenuException {

		TreeMenu tmp_TreeMenu = this.treeMenuDAO.getOneByName(treeMenu
				.getName());
		if (tmp_TreeMenu != null)
			throw new MenuException(MenuCons.DUP_MENU_NAME_MESS());

		if (navMenuId == null || navMenuId <= 0)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		NavMenu navMenu = this.navMenuDAO.getOne(navMenuId);
		if (navMenu == null)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		if (pid > 0) {
			TreeMenu parent = this.treeMenuDAO.getOne(pid);
			if (parent == null)
				throw new MenuException(MenuCons.PARENT_MENU_NOT_FOUND_MESS());
			if (pid.equals(treeMenu.getTreeMenuId()))
				throw new MenuException(MenuCons.INVALID_PARENT_MESS());
			treeMenu.setParent(parent);
		} else {
			treeMenu.setParent(new TreeMenu());
		}

		treeMenu.setNavMenu(navMenu);

		String reloadFlag = treeMenu.getReloadFlag();
		treeMenu.setReloadFlag("on".equals(reloadFlag) ? "1" : "0");
		String external = treeMenu.getExternal();
		treeMenu.setExternal("on".equals(external) ? "true" : "false");

		this.treeMenuDAO.create(treeMenu);
	}

	public EditPage<TreeMenu> getEditPage(Long id) throws MenuException {

		TreeMenu treeMenu = this.treeMenuDAO.getOne(id);
		if (treeMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		String reloadFlag = treeMenu.getReloadFlag();
		treeMenu.setReloadFlag("1".equals(reloadFlag) ? "checked" : "");
		String external = treeMenu.getExternal();
		treeMenu.setExternal("true".equals(external) ? "checked" : "");

		EditPage<TreeMenu> editPage = new EditPage<TreeMenu>(
				TreeMenuCons.MODEL_NAME(),
				TreeMenuCons.MODEL_NAME() + "/" + id, treeMenu);

		return editPage;
	}

	public void updateWithCascade(TreeMenu treeMenu, Long navMenuId, Long pid)
			throws MenuException {
		Long id = treeMenu.getTreeMenuId();
		if (id == null || id <= 0)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		TreeMenu db_TreeMenu = this.treeMenuDAO.getOne(id);
		if (db_TreeMenu == null)
			throw new MenuException(MenuCons.MENU_NOT_FOUND_MESS());

		TreeMenu other_TreeMenu = this.treeMenuDAO.getOtherByName(id,
				treeMenu.getName());
		if (other_TreeMenu != null)
			throw new MenuException(MenuCons.DUP_MENU_NAME_MESS());

		if (navMenuId == null || navMenuId <= 0)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		NavMenu navMenu = this.navMenuDAO.getOne(navMenuId);
		if (navMenu == null)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		if (pid > 0) {
			TreeMenu parent = this.treeMenuDAO.getOne(pid);
			if (parent == null)
				throw new MenuException(MenuCons.PARENT_MENU_NOT_FOUND_MESS());

			if (pid.equals(treeMenu.getTreeMenuId()))
				throw new MenuException(MenuCons.INVALID_PARENT_MESS());

			treeMenu.setParent(parent);
		} else
			treeMenu.setParent(new TreeMenu());

		treeMenu.setNavMenu(navMenu);

		String reloadFlag = treeMenu.getReloadFlag();
		treeMenu.setReloadFlag("on".equals(reloadFlag) ? "1" : "0");
		String external = treeMenu.getExternal();
		treeMenu.setExternal("on".equals(external) ? "true" : "false");

		this.treeMenuDAO.update(treeMenu);
	}

	public List<TreeMenu> getTopParent(Long navMenuId) throws MenuException {
		if (navMenuId == null || navMenuId <= 0)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		NavMenu navMenu = this.navMenuDAO.getOne(navMenuId);
		if (navMenu == null)
			throw new MenuException(MenuCons.NAV_MENU_NOT_FOUND_MESS());

		List<TreeMenu> pojos = this.treeMenuDAO
				.getTopParentOrderByRankASC(navMenuId);
		pojos = this.handleTopTreeMenu(pojos);
		return pojos;
	}

	public List<TreeMenu> getChildren(Long pid) throws MenuException {
		if (pid == null || pid <= 0)
			throw new MenuException(MenuCons.PARENT_MENU_NOT_FOUND_MESS());

		TreeMenu parent = this.treeMenuDAO.getOne(pid);
		if (parent == null)
			throw new MenuException(MenuCons.PARENT_MENU_NOT_FOUND_MESS());

		return this.treeMenuDAO.getChildrenOrderByRankASC(pid);
	}

	public NavMenu findNavMenuByTreeMenuId(Long treemenuId) throws Exception {
		TreeMenu treeMenu = new TreeMenu();
		treeMenu.setTreeMenuId(treemenuId);
		this.treeMenuDAO.cascadeSelectNavMenu(treeMenu);

		return treeMenu.getNavMenu();
	}
}
