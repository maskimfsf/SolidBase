package org.eweb4j.solidbase.area.model;

import java.util.ArrayList;
import java.util.List;

import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.Db;
import org.eweb4j.orm.Page;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午10:50:08
 */
public class AreaServiceImpl implements AreaService{

	public void create(Area pojo) throws AreaException {
		if (pojo == null)
			throw new AreaException("area can not be null");
		Area db_pojo = Db.ar(Area.class).find("byName", pojo.getName()).first();
		if (db_pojo != null)
			throw new AreaException("area.name duplicate");
		
		if (pojo.getCoords() != null)
			pojo.setCoords(pojo.getCoords().trim());
		
		Db.ar(pojo).create();
	}

	public PageMod<Area> getPage(int p, int n) throws AreaException {
		if (p <= 0)
			p = 1;
		if (n <= 0)
			n = 20;
		
		Page<Area> page = 
			Db.ar(Area.class)
				.dao()
				.fetch("mapper")
				.selectAll()
				.getPage(p, n);
		
		return new PageMod<Area>(page.getList(), page.getTotalRowCount());
	}

	@Override
	public void remove(Long id) throws AreaException {
		batchRemove(new Long[]{id});
	}

	@Override
	public void batchRemove(Long[] ids) throws AreaException {
		if (ids == null || ids.length == 0) 
			throw new AreaException("ids can not be empty");
		
		List<Area> pojos = new ArrayList<Area>(ids.length);
		for (Long id : ids){
			Area pojo = new Area();
			pojo.setId(id);
			pojos.add(pojo);
		}
		
		Db.batchDelete(pojos.toArray());
	}

	@Override
	public void update(Area pojo) throws AreaException {
		if (pojo == null)
			throw new AreaException("pojo can not be null");
		Area db_pojo = Db.ar(Area.class).find("byName", pojo.getName()).first();
		if (db_pojo != null && !pojo.getId().equals(db_pojo.getId()))
			throw new AreaException("area.name duplicate");
		
		if (pojo.getCoords() != null)
			pojo.setCoords(pojo.getCoords().trim());
		
		Db.ar(pojo).save();
	}

	@Override
	public Area get(Long id) throws AreaException {
		Area area = Db.ar(Area.class).dao().fetch("mapper").selectAll().where().field("id").equal(id).queryOne();
		if (area != null && area.getMapper() != null){
			Db.ar(area.getMapper()).cascade().fetch("image");
		}
		
		return area;
	}

}
