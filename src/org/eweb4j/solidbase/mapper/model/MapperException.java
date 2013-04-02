package org.eweb4j.solidbase.mapper.model;
/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午11:02:28
 */
public class MapperException extends Exception{
	private static final long serialVersionUID = 1L;

	public MapperException(String mess) {
		super(mess);
	}

	public MapperException(String mess, Exception e) {
		super(mess, e);
	}
}
