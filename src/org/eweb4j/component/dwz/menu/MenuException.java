package org.eweb4j.component.dwz.menu;

public class MenuException extends Exception {

	private static final long serialVersionUID = 866230510779251570L;

	public MenuException(String mess) {
		super(mess);
	}

	public MenuException(String mess, Exception e) {
		super(mess, e);
	}

}
