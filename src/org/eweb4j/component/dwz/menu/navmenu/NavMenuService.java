package org.eweb4j.component.dwz.menu.navmenu;

import java.util.Collection;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.ListPage;

/**
 * 管理导航菜单服务, 向下依赖DAO, 向上Action提供服务接口, 数据库事务边界, 异常处理
 * 
 * @author weiwei
 * 
 */
public interface NavMenuService {

	/**
	 * 新增导航菜单信息
	 * 
	 * @param navMenu
	 *            数据库访问异常
	 */
	public void add(NavMenu navMenu) throws MenuException;

	/**
	 * 获取给定数据库记录ID的导航菜单信息
	 * 
	 * @param id
	 * @return 数据库访问异常
	 */
	public NavMenu getOne(Long id) throws MenuException;

	/**
	 * 获取编辑给定数据库记录ID的导航菜单页面信息
	 * 
	 * @param id
	 * @return 数据库访问异常
	 */
	public EditPage<NavMenu> getEditPage(Long id) throws MenuException;

	/**
	 * 更新指定导航菜单信息
	 * 
	 * @param navMenu
	 */
	public void update(NavMenu navMenu) throws MenuException;

	/**
	 * 获取所有导航菜单信息，Json格式字符串
	 * 
	 * @return String
	 */
	public String getAllFormatJson() throws MenuException;

	/**
	 * 批量删除导航菜单信息，给定数据库记录ID
	 * 
	 * @param ids
	 */
	public void batchRemove(Long[] ids) throws MenuException;

	/**
	 * 删除给定数据库记录ID的导航菜单信息
	 * 
	 * @param id
	 */
	public void removeOne(Long id) throws MenuException;

	/**
	 * 分页
	 * 
	 * @param p
	 *            页码
	 * @param n
	 *            每页显示数
	 * @return
	 */
	public ListPage getPage(int p, int n) throws MenuException;

	/**
	 * /** 分页查询
	 * 
	 * @param keyword
	 *            关键字
	 * @param p
	 *            页码
	 * @param n
	 *            每页显示数
	 * @return
	 */
	public ListPage getSearchResult(String keyword, int p, int n)
			throws MenuException;

	/**
	 * 获取给定名字的导航菜单信息
	 * 
	 * @param name
	 * @return
	 */
	public NavMenu getOneByName(String name) throws MenuException;

	/**
	 * 
	 * @param navMenuName
	 * @throws MenuException
	 */
	public NavMenu createDefaultNavMenu(String navMenuName)
			throws MenuException;

	/**
	 * 排序，查找所有导航菜单
	 * 
	 * @param orderFiled
	 *            排序字段
	 * @param orderType
	 *            排序类型 1 升序 -1降序
	 * @return
	 */
	public Collection<NavMenu> findAllOrderBy(String orderFiled, int orderType) throws MenuException;
}
