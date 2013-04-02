package org.eweb4j.solidbase.area.model;

import org.eweb4j.mvc.view.PageMod;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午10:49:06
 */
public interface AreaService {
	void create(Area area) throws AreaException;

	PageMod<Area> getPage(int p, int n) throws AreaException;

	void remove(Long id) throws AreaException;

	void batchRemove(Long[] ids) throws AreaException;

	void update(Area area) throws AreaException;

	Area get(Long id) throws AreaException;
}
