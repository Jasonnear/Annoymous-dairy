package app.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="future_voice")
public class Future_voice implements Serializable{

	/**
	 * 未来录音id主键
	 */
	@Id
	private String id;
	
	/**
	 * 标题
	 */
	@Column(length = 40,nullable = false)
	private String article;
	
	/**
	 * 创建时间
	 */
	@Column(length = 25)
	private Date createTime;
	
	/**
	 * 结束时间
	 */
	@Column(length = 25)
	private Date endTime;
	
	/**
	 * 外键，用于关联user表
	 */
	@Column(length = 255)
	private String user_id;
	
	/**
	 * 0:daily
	 * 1:voice
	 * 2:future_daily
	 * 3:future_voice
	 */
	@Column
	private int dairytype_id;
	
	/**
	 * 保存voice音频的路径
	 */
	@Column(length = 40,nullable = false)
	private String url;
	
	/**
	 * 是否可读
	 * @return
	 */
	@Column
	public boolean isOpen = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getDairytype_id() {
		return dairytype_id;
	}

	public void setDairytype_id(int dairytype_id) {
		this.dairytype_id = dairytype_id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Future_voice [id=" + id + ", article=" + article + ", createTime=" + createTime + ", endTime=" + endTime
				+ ", user_id=" + user_id + ", dairytype_id=" + dairytype_id + ", url=" + url + ", isOpen=" + isOpen
				+ "]";
	}
}
