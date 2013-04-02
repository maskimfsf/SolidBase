package org.eweb4j.solidbase.mapper.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eweb4j.solidbase.area.model.Area;
import org.eweb4j.solidbase.files.model.Files;

/**
 * 映射，例如一个商场的室内平面图中的KFC店铺映射
 * @author weiwei l.weiwei@163.com
 * @date 2013-3-30 下午05:36:46
 */
@Entity
@Table(name="t_mapper")
public class Mapper {

	@Id
	private Long id;
	
	private String name;//映射的名称，例如KFC店铺
	
	private String description;//映射的描述
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="image_id")
	private Files image;//关联的图片文件
	
	private int width;//映射的宽度，像素
	
	private int height;//映射的高度，像素
	
	@OneToMany(mappedBy="mapper")
	private List<Area> areas = new ArrayList<Area>();//映射里的多个热点区域

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Files getImage() {
		return this.image;
	}

	public void setImage(Files image) {
		this.image = image;
	}
	
}
