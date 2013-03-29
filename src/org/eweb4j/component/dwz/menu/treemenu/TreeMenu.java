package org.eweb4j.component.dwz.menu.treemenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eweb4j.component.dwz.menu.navmenu.NavMenu;


/**
 * DWZ中左边树形结构菜单模型
 * 
 * @author weiwei
 * 
 */
@Entity
@Table(name = "t_tree_menu")
public class TreeMenu implements Serializable {
	private static final long serialVersionUID = 5264149396283615258L;
	@Id
	@Column(name = "id")
	private Long treeMenuId = 0L;
	private String name;// 菜单名
	private String target = "navTab";// navTab或dialog
	private String rel;// navTabId
	@Column(name = "reload_flag")
	private String reloadFlag = "1";// 是否自动刷新

	private String external = "false";// 是否是外部页

	private int width; // 对话框宽度
	private int height; // 对话框高度

	private String href;// 跳转路径
	private int rank;// 排序
	@OneToOne
	@JoinColumn(name="pid")
	private TreeMenu parent;// 父亲菜单
	
	@OneToOne
	@JoinColumn(name="nav_menu_id")
	private NavMenu navMenu;// 所在的导航菜单
	
	@OneToMany(mappedBy = "pid")
	private List<TreeMenu> children = new ArrayList<TreeMenu>();// 孩子菜单们

	public Long getTreeMenuId() {
		return treeMenuId;
	}

	public void setTreeMenuId(Long treeMenuId) {
		this.treeMenuId = treeMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMenu getParent() {
		return parent;
	}

	public void setParent(TreeMenu parent) {
		this.parent = parent;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getReloadFlag() {
		return this.reloadFlag;
	}

	public void setReloadFlag(String reloadFlag) {
		this.reloadFlag = reloadFlag;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public NavMenu getNavMenu() {
		return navMenu;
	}

	public void setNavMenu(NavMenu navMenu) {
		this.navMenu = navMenu;
	}

	public List<TreeMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TreeMenu> children) {
		this.children = children;
	}

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	public String toString() {
		Long pid = parent == null ? TreeMenuCons.TOP_TREE_MENU_ID() : parent.treeMenuId;
		return "{\"treeMenuId\":\"" + treeMenuId + "\",\"navMenuId\":\""
				+ navMenu.getNavMenuId() + "\",\"pid\":\"" + pid
				+ "\",\"name\":\"" + name + "\",\"href\":\"" + href + "\"}";
	}

}
