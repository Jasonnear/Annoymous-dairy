package app.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="daily")
public class Daily implements Serializable{
	
	/**
	 * 主键
	 */
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

	/**
	 * 是否公开
	 * true:公开
	 * false:非公开
	 */
	@Column
	private boolean permission;
	
	/**
	 * 属于什么专题：
	 * 	0:所有
	 * 	1:感情
	 * 	2:心情
	 * 	3:娱乐
	 * 	4:政治
	 */
	@Column
	private int topic_id;

	/**
	 * pm处理
	 */
	@Column
	private boolean pm = false;
	
	/**
	 * 0:daily
	 * 1:voice
	 * 2:future_daily
	 * 3:future_voice
	 */
	@Column
	private int dairytype_id;
	
	/**
	 * 文章内容中第一张照片
	 */
	@Column(length = 55)
	private String first_image_url;
	
	/**
	 * 文章内容
	 */
	@Column(length = 40)
	private String first_context;
	
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

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}


	public boolean isPm() {
		return pm;
	}

	public void setPm(boolean pm) {
		this.pm = pm;
	}

	public int getDairytype_id() {
		return dairytype_id;
	}

	public void setDairytype_id(int dairytype_id) {
		this.dairytype_id = dairytype_id;
	}

	public String getFirst_image_url() {
		return first_image_url;
	}

	public void setFirst_image_url(String first_image_url) {
		this.first_image_url = first_image_url;
	}

	public String getFirst_context() {
		return first_context;
	}

	public void setFirst_context(String first_context) {
		this.first_context = first_context;
	}

}
