package org.eweb4j.solidbase.mapper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.Db;
import org.eweb4j.orm.Page;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午10:50:08
 */
public class MapperServiceImpl implements MapperService{

	public void create(Mapper mapper) throws MapperException {
		if (mapper == null)
			throw new MapperException("mapper can not be null");
		Mapper db_mapper = Db.ar(Mapper.class).find("byName", mapper.getName()).first();
		if (db_mapper != null)
			throw new MapperException("mapper.name duplicate");
		
		Db.ar(mapper).create();
	}

	public PageMod<Mapper> getPage(int p, int n) throws MapperException {
		if (p <= 0)
			p = 1;
		if (n <= 0)
			n = 20;
		
		Page<Mapper> page = 
			Db.ar(Mapper.class)
				.dao()
				.fetch("image")
				.selectAll()
				.getPage(p, n);
		
		return new PageMod<Mapper>(page.getList(), page.getTotalRowCount());
	}

	@Override
	public void remove(Long id) throws MapperException {
		batchRemove(new Long[]{id});
	}

	@Override
	public void batchRemove(Long[] ids) throws MapperException {
		if (ids == null || ids.length == 0) 
			throw new MapperException("ids can not be empty");
		
		List<Mapper> mappers = new ArrayList<Mapper>(ids.length);
		for (Long id : ids){
			Mapper mapper = new Mapper();
			mapper.setId(id);
			mappers.add(mapper);
		}
		
		Db.batchDelete(mappers.toArray());
	}

	@Override
	public void update(Mapper mapper) throws MapperException {
		if (mapper == null)
			throw new MapperException("mapper can not be null");
		Mapper db_mapper = Db.ar(Mapper.class).find("byName", mapper.getName()).first();
		if (db_mapper != null && !mapper.getId().equals(db_mapper.getId()))
			throw new MapperException("mapper.name duplicate");
		
		Db.ar(mapper).save();
	}

	@Override
	public Mapper get(Long id) throws MapperException {
		return Db.ar(Mapper.class).dao().fetch("image").selectAll().where().field("id").equal(id).queryOne();
	}

	@Override
	public Collection<Mapper> getAllMappers() throws MapperException {
		return Db.ar(Mapper.class).findAll();
	}

}
