package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Collection;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAO;
import org.eweb4j.orm.dao.DAOException;

public class NavMenuDAOImpl implements NavMenuDAO {
	
	private String dsName;
	
	private final static Class<NavMenu> clazz = NavMenu.class;
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public void batchDelete(NavMenu... menus) throws MenuException{
		try{
			Db.batchDelete(dsName, menus);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}
	
	public NavMenu getOneByName(String name) throws MenuException {
		try {
			return Db.ar(clazz,dsName).find("byName", name).first();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public NavMenu getOtherByName(Long id, String name) throws MenuException {
		try {
			return Db.ar(clazz, dsName).find("id <> ? and name = ?", id, name).first();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public long countAll() throws MenuException {
		try {
			return Db.ar(clazz, dsName).count();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public Collection<NavMenu> getPage(int p, int n) throws MenuException {
		try {
			return Db.ar(clazz, dsName).find().fetch(p, n);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public NavMenu getOne(Long id) throws MenuException {
		try {
			return Db.ar(clazz, dsName).findById(id);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public void update(NavMenu navMenu) throws MenuException {
		try {
			Db.ar(navMenu,dsName).save();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public void create(NavMenu navMenu) throws MenuException {
		try {
			Db.ar(navMenu,dsName).create();
			
			String href = navMenu.getHref();
			if (!href.endsWith(".html") && !href.endsWith(".jsp"))
				navMenu.setHref(NavMenuCons.DEFAULT_NAV_MENU_HREF().replace( "{id}", navMenu.getNavMenuId() + ""));
			
			Db.ar(navMenu,dsName).save("href");
			
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public Collection<NavMenu> getAll() throws MenuException {
		try {
			return Db.ar(clazz,dsName).findAll();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public void deleteOne(Long id) throws MenuException {
		try {
			Db.ar(clazz,dsName).delete("byNavMenuId", id);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public Collection<NavMenu> searchAndPaging(String keyword, int p, int n) throws MenuException {
		try {
			return Db.ar(clazz, dsName).find("byNameLike", "%"+keyword+"%").fetch(p, n);
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public long countSearch(String keyword) throws MenuException {
		try {
			return Db.ar(clazz,dsName).count("name like ?", "%"+keyword+"%");
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public long insert(String[] fields, Object[] values) throws MenuException {
		try {
			return Db.ar(clazz, dsName)
					.dao()
						.insert(fields).values(values)
					.execute()
					.longValue();
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

	public Collection<NavMenu> selectAllOrderBy(String orderField, int orderType) throws MenuException {
		try {
			DAO dao = Db.ar(clazz,dsName).dao().selectAll().orderBy(orderField);
			
			dao = orderType == -1 ? dao.desc() : dao.asc();
			
			return dao.query();
			
		} catch (DAOException e) {
			throw new MenuException(e.getMessage());
		}
	}

}
