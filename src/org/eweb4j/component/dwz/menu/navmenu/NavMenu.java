package org.eweb4j.component.dwz.menu.navmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Length;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.util.JsonConverter;

/**
 * DWZ中导航菜单模型
 * 
 * @author weiwei
 * 
 */

@Entity
@Table(name = "t_nav_menu")
public class NavMenu implements Serializable {
	private static final long serialVersionUID = -4323821316335244056L;

	@Id
	@Column(name = "id")
	private Long navMenuId;
	
	@Required
	@Length(min=2, max=10)
	private String name;
	
	@Length(min=1, max=255)
	private String href;
	
	@Int
	private int rank;
	
	@OneToMany(mappedBy = "nav_menu_id")
	private List<TreeMenu> treeMenus = new ArrayList<TreeMenu>();

	
	public Long getNavMenuId() {
		return navMenuId;
	}

	
	public List<TreeMenu> getTreeMenus() {
		return treeMenus;
	}

	public String getName() {
		return name;
	}

	public String getHref() {
		if (href != null) {
			if (href.contains("{id}")) {
				href = href.replace("{id}", String.valueOf(navMenuId));
			}
		}
		return href;
	}

	public int getRank() {
		return rank;
	}

	public void setNavMenuId(Long navMenuId) {
		this.navMenuId = navMenuId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTreeMenus(List<TreeMenu> treeMenus) {
		this.treeMenus = treeMenus;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String toString() {
		return JsonConverter.convert(this);
	}
}
