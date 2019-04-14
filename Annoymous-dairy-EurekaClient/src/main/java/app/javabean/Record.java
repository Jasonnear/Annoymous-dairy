package app.javabean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {

	@Id
	private String id;
	
	/**
	 * 标题(共有)
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
	 * 创建时间(共有)
	 */
	@Column(length = 25)
	private Date createTime;
	
	/**
	 * (共有)
	 * 0:daily
	 * 1:voice
	 * 2:future_daily
	 * 3:future_voice
	 */
	@Column
	private int dairytype_id;
	
	@Column(length = 255)
	private String user_id;
	
	/**
	 * 保存voice音频的路径
	 */
	@Column(length = 40)
	private String url;
	
	/**
	 * 专题类型
	 */
	@Column
	private int topic_id;
	
	/**
	 * 文章的图片
	 */
	@Column(length = 55)
	private String first_image_url;
	
	/**
	 * 文章内容
	 */
	@Column(length = 40)
	private String first_context;
	
	@Column
	private int good_count = 0;

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

	public int getDairytype_id() {
		return dairytype_id;
	}

	public void setDairytype_id(int dairytype_id) {
		this.dairytype_id = dairytype_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public int getGood_count() {
		return good_count;
	}

	public void setGood_count(int good_count) {
		this.good_count = good_count;
	}

}
