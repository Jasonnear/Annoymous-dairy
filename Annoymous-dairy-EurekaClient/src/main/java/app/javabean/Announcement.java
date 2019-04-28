package app.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 管理员发布的公告
 * @author Jason
 *
 */
@Entity
@Table(name = "announcement")
public class Announcement implements Serializable{

	@Id
	private String id;
	
	/**
	 * 标题
	 */
	@Column(length = 40,nullable = false)
	private String article;
	
	/**
	 * 存放md
	 */
	@Lob
	@Column(columnDefinition = "text")
	private String md;
	
	/**
	 * 存放html
	 */
	@Lob
	@Column(columnDefinition = "text")
	private String html;
	
	
	/**
	 * 创建时间
	 */
	@Column(length = 25)
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	@Column(length = 25)
	private Date updateTime;
	
	/**
	 * 外键，用于关联user表
	 */
	@Column(length = 255)
	private String user_id;

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

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
