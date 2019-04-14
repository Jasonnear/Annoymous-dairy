package app.javabean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="future_daily")
public class Future_daily {

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
	 * 是否可读
	 */
	@Column
	public boolean isOpen = false;
	
	/**
	 * 文章内容中第一张照片
	 */
	@Column(length = 55)
	private String first_image_url;
	
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

	public String getFirst_image_url() {
		return first_image_url;
	}

	public void setFirst_image_url(String first_image_url) {
		this.first_image_url = first_image_url;
	}
}
