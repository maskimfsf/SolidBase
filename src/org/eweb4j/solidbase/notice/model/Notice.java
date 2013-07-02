package org.eweb4j.solidbase.notice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.files.model.Files;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.util.CommonUtil;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-13 上午10:25:52
 */
@Entity
@Table(name = "t_notice")
public class Notice {
	@Id
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="file_id", referencedColumnName="id")
	private Files file;
	
	@Column(name="pub_time")
	private Date pubTime;
	
	private String publishTime;
	
	private String title;
	
	@Column(columnDefinition="text")
	private String content;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPubTime() {
		return this.pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Files getFile() {
		return this.file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public String getPublishTime() {
		return CommonUtil.formatTime("yyyy-MM-dd HH:mm", this.pubTime);
	}
}
