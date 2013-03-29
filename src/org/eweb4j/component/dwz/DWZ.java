package org.eweb4j.component.dwz;

import java.util.List;

import org.eweb4j.component.dwz.view.UlTag;
import org.eweb4j.mvc.view.CallBackJson;


public interface DWZ {
	
	/**
	 * 提供树形菜单叶子节点的访问权限集合
	 * @param treemenus
	 */
	public void setTreeMenuPermissions(List<Long> treemenus);
	
	/**
	 * 提供给运行期的程序设置不同的树形菜单风格
	 * @param styleClass
	 */
	public void setRootUlStyleClass(String styleClass);
	
	/**
	 * 给定一个导航菜单名字，取得其对应的树形菜单 例如： 导航菜单：文章管理，用户管理
	 * 
	 * 点击“文章管理”，树形菜单就变成： 文章管理 |——显示所有文章 |——新增文章 点击“用户管理”，树形菜单就变成： 用户管理 |——显示所有用户
	 * |——新增用户
	 * 
	 * @param navMenu
	 *            导航菜单
	 * @param noAuth 是否不鉴权
	 * @return
	 */
	public String getAccordion(String navMenuName, boolean noAuth) throws Exception;

	/**
	 * 给定一个导航菜单的数据库记录ID，取得其对应的树形菜单 例如： 导航菜单：文章管理，用户管理
	 * 
	 * 点击“文章管理”，树形菜单就变成： 文章管理 |——显示所有文章 |——新增文章 点击“用户管理”，树形菜单就变成： 用户管理 |——显示所有用户
	 * |——新增用户
	 * 
	 * @param navMenuId
	 *            导航菜单数据库保存记录的ID
	 * @param noAuth 是否不鉴权
	 * @return
	 */
	public String getAccordion(Long navMenuId, boolean noAuth) throws Exception;

	/**
	 * 创建并返回一个dwz的操作成功json信息。
	 * 
	 * @param message
	 *            消息文字
	 * @param navTabId
	 *            标签页ID
	 * @param forwardUrl
	 *            跳转路径
	 * @param callType
	 *            回调类型，@see CallBackType
	 * @param title
	 * @return
	 */
	public CallBackJson getSuccessJson(String message, String navTabId,
			String forwardUrl, String callType, String title);

	/**
	 * 创建并返回一个dwz的操作失败json信息
	 * 
	 * @param message
	 * @return
	 */
	public CallBackJson getFailedJson(String message);

	/**
	 * 给定根节点TreeMenuId,创建一棵DWZ树形菜单HTML代码
	 * 
	 * @param rootNodeId
	 *            根节点
	 * @param noAuth 是否不需要鉴权
	 * @return
	 */
	public String createTreeMenu(Long treeMenuId, boolean noAuth) throws Exception;
	
	/**
	 * 给定导航菜单名字，创建一颗DWZ树形菜单HTML代码
	 * @param navMenuName
	 * @param noAuth 是否不需要鉴权
	 * @return
	 * @throws Exception
	 */
	public String createTreeMenu(String navMenuName, boolean noAuth) throws Exception;
	
	/**
	 * 创建所有包括导航菜单的树形菜单HTML代码
	 * @return
	 * @throws Exception
	 */
	public String createAllTreeMenu() throws Exception;

	/**
	 * 给定根节点TreeMenuId,创建一棵DWZ树形菜单(JAVA对象)
	 * 
	 * @param rootNodeId
	 *            根节点
	 * @param noAuth 是否不需要鉴权
	 * @return
	 */
	public UlTag getUlTag(Long treeMenuId, boolean noAuth) throws Exception;
}
