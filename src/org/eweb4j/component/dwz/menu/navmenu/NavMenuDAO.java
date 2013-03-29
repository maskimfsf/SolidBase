package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Collection;

import org.eweb4j.component.dwz.menu.MenuException;

/**
 * 导航菜单数据库访问接口
 * 
 * @author weiwei
 * 
 */
public interface NavMenuDAO {
	
	public NavMenu getOneByName(String name) throws MenuException;

	public NavMenu getOtherByName(Long id, String name) throws MenuException;

	public long countAll() throws MenuException;

	public Collection<NavMenu> getPage(int p, int n) throws MenuException;

	public NavMenu getOne(Long id) throws MenuException;

	public void update(NavMenu navMenu) throws MenuException;

	public void create(NavMenu navMenu) throws MenuException;

	public Collection<NavMenu> getAll() throws MenuException;

	public void deleteOne(Long id) throws MenuException;

	public Collection<NavMenu> searchAndPaging(String keyword, int p, int n) throws MenuException;

	public long countSearch(String keyword) throws MenuException;

	public long insert(String[] fields, Object[] values) throws MenuException;

	public Collection<NavMenu> selectAllOrderBy(String orderField, int orderType) throws MenuException;

	public void batchDelete(NavMenu... navMenus) throws MenuException;
}
