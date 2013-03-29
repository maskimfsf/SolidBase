package org.eweb4j.component.dwz.menu.treemenu;

import java.util.List;

import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.ListPage;

public interface TreeMenuService {

	public NavMenu findNavMenuByTreeMenuId(Long treemenuId) throws Exception;

	/**
	 * 分页，级联NavMenu
	 * 
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public ListPage getPageWithCascade(int pageNum, int numPerPage)
			throws MenuException;

	/**
	 * 分页，查询关键字
	 * 
	 * @param keyword
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public ListPage getSearchResult(String keyword, int pageNum, int numPerPage)
			throws MenuException;

	/**
	 * 获取能够作为父菜单的列表。 条件：分页，查询关键字，所属某个导航菜单，不能把自己作为父菜单
	 * 
	 * @param navMenuId
	 *            所属导航菜单ID
	 * @param treeMenuId
	 *            自己(树形菜单)ID
	 * @param keyword
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public ListPage getParentsSearchResult(Long navMenuId, Long treeMenuId,
			String keyword, int pageNum, int numPerPage) throws MenuException;

	/**
	 * 获取能够作为父菜单的列表。 条件：分页，所属某个导航菜单，不能把自己作为父菜单
	 * 
	 * @param navMenuId
	 *            所属导航菜单ID
	 * @param treeMenuId
	 *            自己(树形菜单)ID
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public ListPage getParentsPage(Long navMenuId, Long treeMenuId,
			int pageNum, int numPerPage) throws MenuException;

	/**
	 * 获取能够作为父菜单的列表(Json格式字符串)。 条件：所属某个导航菜单，不能把自己作为父菜单
	 * 
	 * @param navMenuId
	 *            所属导航菜单ID
	 * @param treeMenuId
	 *            自己(树形菜单)ID
	 * @return
	 */
	public String getParentsFormatJson(Long navMenuId, Long treeMenuId)
			throws MenuException;

	/**
	 * 批量删除给定的树形菜单
	 * 
	 * @param ids
	 */
	public void batchRemove(Long[] ids) throws MenuException;

	/**
	 * 删除给定的树形菜单
	 * 
	 * @param id
	 */
	public void removeOne(Long id) throws MenuException;

	/**
	 * 新增树形菜单. 规则： 自己不能作为父菜单、 导航菜单在数据库中要存在、 如果父菜单ID > 0，则父菜单在数据库中要存在、
	 * 
	 * @param treeMenu
	 * @param navMenuId
	 *            所属导航菜单ID
	 * @param pid
	 *            父菜单ID ;
	 */
	public void addWithCascade(TreeMenu treeMenu, Long navMenuId, Long pid)
			throws MenuException;

	/**
	 * 返回编辑页. 要求级联查询出与之有关的对象数据。
	 * 
	 * @param id
	 * @return
	 */
	public EditPage<TreeMenu> getEditPage(Long id) throws MenuException;

	/**
	 * 更新树形菜单. 规则： 自己不能作为父菜单、 导航菜单在数据库中要存在、 如果父菜单ID > 0，则父菜单在数据库中要存在、
	 * 
	 * @param treeMenu
	 * @param navMenuId
	 *            所属导航菜单ID
	 * @param pid
	 *            父菜单ID
	 */
	public void updateWithCascade(TreeMenu treeMenu, Long navMenuId, Long pid)
			throws MenuException;

	/**
	 * 获取某导航菜单下的所有顶级树形菜单
	 * 
	 * @param navMenuId
	 * @return
	 */
	public List<TreeMenu> getTopParent(Long navMenuId) throws MenuException;

	/**
	 * 获取给定父菜单的所有子菜单
	 * 
	 * @param pid
	 * @return
	 */
	public List<TreeMenu> getChildren(Long pid) throws MenuException;
}
