package org.eweb4j.component.dwz.menu.treemenu;

import java.util.List;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.orm.LikeType;
import org.eweb4j.orm.OrderType;
import org.eweb4j.orm.dao.DAO;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SearchDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;

public class TreeMenuDAOImpl implements TreeMenuDAO {

	private SelectDAO selectDAO = null;
	private UpdateDAO updateDAO = null;
	private DeleteDAO deleteDAO = null;
	private DivPageDAO divPageDAO = null;
	private SearchDAO searchDAO = null;
	private InsertDAO insertDAO = null;
	private CascadeDAO cascadeDAO = null;
	private DAO dao = null;

	public void setDsName(String dsName) {
		this.selectDAO = DAOFactory.getSelectDAO(dsName);
		this.updateDAO = DAOFactory.getUpdateDAO(dsName);
		this.deleteDAO = DAOFactory.getDeleteDAO(dsName);
		this.divPageDAO = DAOFactory.getDivPageDAO(dsName);
		this.searchDAO = DAOFactory.getSearchDAO(dsName);
		this.insertDAO = DAOFactory.getInsertDAO(dsName);
		this.cascadeDAO = DAOFactory.getCascadeDAO(dsName);
		this.dao = DAOFactory.getDAO(TreeMenu.class, dsName);
	}

	public List<TreeMenu> getPageWithCascade(int pageNum, int numPerPage)
			throws MenuException {
		List<TreeMenu> list = null;
		try {
			list = this.divPageDAO.divPage(TreeMenu.class, pageNum, numPerPage);
			if (list != null)
				this.cascadeDAO.select(list.toArray(new TreeMenu[] {}),
						"parent", "navMenu");
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return list;
	}

	public long countAll() throws MenuException {
		long count = 0;
		try {
			count = this.selectDAO.selectCount(TreeMenu.class);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return count;
	}

	public long countSearch(String keyword) throws MenuException {
		long count = 0;
		try {
			String condition = "name like '%" + keyword + "%'";
			count = this.selectDAO.selectCount(TreeMenu.class, condition);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return count;
	}

	public List<TreeMenu> getSearchResult(String keyword, int pageNum,
			int numPerPage) throws MenuException {
		List<TreeMenu> list = null;
		try {
			String[] fields = { "name" };
			String[] values = { keyword };
			int likeType = LikeType.ALL_LIKE;
			boolean isLike = true;
			boolean isNot = false;
			boolean isOR = true;
			String orderField = "id";
			int oType = OrderType.DESC_ORDER;

			list = this.searchDAO.searchByDivPage(TreeMenu.class, fields,
					values, likeType, isLike, isNot, isOR, orderField, oType,
					pageNum, numPerPage);
			if (list != null)
				this.cascadeDAO.select(list.toArray(new TreeMenu[] {}),
						"parent", "navMenu");
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return list;
	}

	public TreeMenu getOne(Long id) throws MenuException {
		TreeMenu treeMenu = null;
		try {
			treeMenu = this.selectDAO.selectOneById(TreeMenu.class, id);
			if (treeMenu != null)
				this.cascadeDAO.select(treeMenu, "navMenu", "parent");
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return treeMenu;
	}

	public void update(TreeMenu treeMenu) throws MenuException {
		try {
			this.updateDAO.update(treeMenu);
		} catch (DAOException e) {

			throw new MenuException(e.getMessage());
		}
	}

	public void create(TreeMenu treeMenu) throws MenuException {
		try {
			this.insertDAO.insert(treeMenu);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public List<TreeMenu> getParent(Long navMenuId, Long treeMenuId)
			throws MenuException {
		List<TreeMenu> pojos = null;
		try {
			String condition = "id <> ? and nav_menu_id = ? ";
			pojos = this.selectDAO.selectWhere(TreeMenu.class, condition,
					treeMenuId, navMenuId);
			if (pojos != null)
				this.cascadeDAO.select(pojos.toArray(new TreeMenu[] {}),
						"parent", "navMenu");

		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return pojos;
	}

	public List<TreeMenu> getParentSearchResult(int pageNum, int numPerPage,
			String keyword, Long navMenuId, Long treeMenuId)
			throws MenuException {
		List<TreeMenu> pojos = null;
		try {
			String condition = "id <> ? and nav_menu_id = ? and name like ? ";
			Object[] args = new Object[] { treeMenuId, navMenuId,
					"%" + keyword + "%" };
			pojos = this.divPageDAO.divPageByWhere(TreeMenu.class, pageNum,
					numPerPage, condition, args);
			if (pojos != null)
				this.cascadeDAO.select(pojos.toArray(new TreeMenu[] {}),
						"parent", "navMenu");

		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return pojos;
	}

	public long countParentSearchResult(String keyword, Long navMenuId,
			Long treeMenuId) throws MenuException {
		long count = 0;
		try {
			String condition = "id <> '" + treeMenuId + "' and nav_menu_id = '"
					+ navMenuId + "' and name like '%" + keyword + "%'";
			count = this.selectDAO.selectCount(TreeMenu.class, condition);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
		return count;
	}

	public void deleteOne(Long id) throws MenuException {
		try {
			this.deleteDAO.deleteById(TreeMenu.class, id);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public TreeMenu getOneByName(String name) throws MenuException {
		TreeMenu treeMenu = null;
		try {
			treeMenu = this.selectDAO.selectOne(TreeMenu.class,
					new String[] { "name" }, new String[] { name });
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return treeMenu;
	}

	public TreeMenu getOtherByName(Long id, String name) throws MenuException {
		try {
			String condition = "id <> ? and name = ?";
			List<TreeMenu> treeMenus = this.selectDAO.selectWhere(
					TreeMenu.class, condition, id, name);
			return treeMenus == null ? null : treeMenus.get(0);
		} catch (Exception e) {
			throw new MenuException(e.getMessage());
		}
	}

	public List<TreeMenu> getTopParentOrderByRankASC(Long navMenuId)
			throws MenuException {
		List<TreeMenu> pojos = null;
		try {
			String topId = TreeMenuCons.TOP_TREE_MENU_ID() + "";
			String[] fields = new String[] { "pid", "nav_menu_id" };
			String[] values = new String[] { topId, String.valueOf(navMenuId) };

			pojos = this.searchDAO.searchByExact(TreeMenu.class, fields,
					values, "rank", OrderType.ASC_ORDER, false);

		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return pojos;
	}

	public List<TreeMenu> getChildrenOrderByRankASC(Long pid)
			throws MenuException {
		List<TreeMenu> pojos = null;
		try {
			String[] fields = new String[] { "pid" };
			String[] values = new String[] { String.valueOf(pid) };

			pojos = this.searchDAO.searchByExact(TreeMenu.class, fields,
					values, "rank", OrderType.ASC_ORDER, false);

		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}

		return pojos;
	}

	public long insert(String[] fields, Object[] values) throws MenuException {
		this.dao.clear();
		return this.dao.insert(fields).values(values).execute().longValue();
	}

	public void update(String[] fields, Object[] values) throws MenuException {
		this.dao.clear();
		this.dao.update().set(fields, values).execute();
	}

	public void cascadeSelectNavMenu(TreeMenu treeMenu) throws MenuException {
		this.cascadeDAO.select(treeMenu, "navMenu");
	}
}
