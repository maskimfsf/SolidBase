package org.eweb4j.solidbase.mapper.model;

import java.util.Collection;

import org.eweb4j.mvc.view.PageMod;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午10:49:06
 */
public interface MapperService {
	void create(Mapper mapper) throws MapperException;

	PageMod<Mapper> getPage(int p, int n) throws MapperException;

	void remove(Long id) throws MapperException;

	void batchRemove(Long[] ids) throws MapperException;

	void update(Mapper mapper) throws MapperException;

	Mapper get(Long id) throws MapperException;

	Collection<Mapper> getAllMappers() throws MapperException;
}
