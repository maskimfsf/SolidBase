package org.eweb4j.solidbase.notice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.user.model.User;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2013-4-13 上午10:36:37
 */
@Entity
@Table(name="t_notice_reader")
public class NoticeReader {
	
	@Id
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="notice_id", referencedColumnName="id")
	private Notice notice;
	
	@Column(name="read_tag")
	private int readTag;
	
	@Column(name="read_time")
	private Date readTime;

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

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public int getReadTag() {
		return this.readTag;
	}

	public void setReadTag(int readTag) {
		this.readTag = readTag;
	}

	public Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

}
