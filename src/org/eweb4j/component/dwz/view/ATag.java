package org.eweb4j.component.dwz.view;

import java.lang.reflect.Method;

import org.eweb4j.util.ReflectUtil;

/**
 * <a标签
 * 
 * @author weiwei
 * 
 */
public class ATag {
	public ATag() {
	}

	public ATag(String tname, Object tvalue, boolean checked, String target,
			String width, String height, String rel, String external,
			String reloadFlag, String href, String value) {
		this.tname = tname;
		this.tvalue = tvalue;
		this.checked = checked;
		this.target = target;
		this.width = width;
		this.height = height;
		this.rel = rel;
		this.external = external;
		this.reloadFlag = reloadFlag;
		this.href = href;
		this.value = value;
	}

	private String tname;
	private Object tvalue;
	private boolean checked;

	private String target;
	private String width;
	private String height;
	private String rel;
	private String external;
	private String reloadFlag;
	private String href;
	private String value;

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Object getTvalue() {
		return tvalue;
	}

	public void setTvalue(Object tvalue) {
		this.tvalue = tvalue;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	public String getReloadFlag() {
		return reloadFlag;
	}

	public void setReloadFlag(String reloadFlag) {
		this.reloadFlag = reloadFlag;
	}

	public String getHref() {
		if (href == null)
			return null;
		
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("<a ");
			ReflectUtil ru = new ReflectUtil(this);
			String[] fieldsName = ru.getFieldsName();
			for (String name : fieldsName) {
				if (!"value".equals(name)) {
					Method m = ru.getGetter(name);
					if (m != null) {
						Object value = m.invoke(this);
						if (value != null && !"".equals(value)) {
							sb.append(" ").append(name).append("=\"");
							sb.append(m.invoke(this));
							sb.append("\"");
						}
					}
				}
			}

			sb.append(">").append(this.getValue()).append("</a>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}
