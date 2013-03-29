package org.eweb4j.component.dwz.menu.treemenu;

import java.util.List;

import org.eweb4j.component.dwz.menu.MenuException;

public interface TreeMenuDAO {
	public long countAll() throws MenuException;

	public List<TreeMenu> getPageWithCascade(int pageNum, int numPerPage)
			throws MenuException;

	public long countSearch(String keyword) throws MenuException;

	public List<TreeMenu> getSearchResult(String keyword, int pageNum,
			int numPerPage) throws MenuException;

	public TreeMenu getOne(Long id) throws MenuException;

	public void update(TreeMenu treeMenu) throws MenuException;

	public List<TreeMenu> getParent(Long navMenuId, Long treeMenuId)
			throws MenuException;

	public List<TreeMenu> getParentSearchResult(int pageNum, int numPerPage,
			String keyword, Long navMenuId, Long treeMenuId)
			throws MenuException;

	public long countParentSearchResult(String keyword, Long navMenuId,
			Long treeMenuId) throws MenuException;

	public void create(TreeMenu menu) throws MenuException;

	public void deleteOne(Long id) throws MenuException;

	public TreeMenu getOneByName(String name) throws MenuException;

	public TreeMenu getOtherByName(Long id, String name)
			throws MenuException;

	public List<TreeMenu> getTopParentOrderByRankASC(Long navMenuId)
			throws MenuException;

	public List<TreeMenu> getChildrenOrderByRankASC(Long pid)
			throws MenuException;

	public long insert(String[] fields, Object[] values)
			throws MenuException;

	public void update(String[] fields, Object[] values)
			throws MenuException;
	
	public void cascadeSelectNavMenu(TreeMenu treeMenu) throws MenuException;

}
