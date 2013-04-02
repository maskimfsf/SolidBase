package org.eweb4j.solidbase.area.model;
/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-2 上午11:02:28
 */
public class AreaException extends Exception{
	private static final long serialVersionUID = 1L;

	public AreaException(String mess) {
		super(mess);
	}

	public AreaException(String mess, Exception e) {
		super(mess, e);
	}
}
