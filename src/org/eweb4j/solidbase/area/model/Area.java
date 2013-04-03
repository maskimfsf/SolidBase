package org.eweb4j.solidbase.area.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.solidbase.mapper.model.Mapper;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-3-30 下午05:30:38
 */
@Entity
@Table(name="t_area")
public class Area {

	public static enum Color{
  		burlywood, chartreuse, darkorchid, aqua, lightcoral, crimson, darksalmon, 
  		indigo, peru, maroon, darkturquoise, firebrick, khaki, fuchsia,
  		black, pink, cyan, orange, navy, plum, silver, lime, gray, violet,
  		gold, magenta, blue, brown, teal, olive, yellow, purple, green, red
	}
	
	public static List<String> allColors(){
		Color[] colors = Color.values();
		List<String> result = new ArrayList<String>(colors.length);
		for (Color c : colors){
			result.add(c.toString().toLowerCase());
		}
		
		return result;
	}
	
	@Id
	private Long id;
	
	@Required(mess="请填写热点名称")
	private String name;//该区域的命名，例如KFC-01
	
	@Required(mess="请选择所属映射")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mapper_id")
	private Mapper mapper;
	
	private String coords;//该区域的各顶点坐标，格式：x1,y1 x2,y2 x3,y4...xN,yN (x和y用","隔开，顶点之间用空格" "隔开)
	
	private String color;//该区域的颜色标识
	
	private String description;//对该区域的描述
	

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

	public String getCoords() {
		return this.coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Mapper getMapper() {
		return this.mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
