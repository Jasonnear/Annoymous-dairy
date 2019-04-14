package app.javabean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 收藏
 * @author Jason
 *
 */
@Entity
@Table(name="collect")
public class Collect {

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 收藏时间
	 */
	@Column(length = 25)
	private Date collectionTime;
	
	/**
	 * 自己的id
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
	 * 收藏对应的类型主键
	 */
	@Column(length = 255 , unique = true)
	private String collect_id;
	
	/**
	 * daily的first_image_url
	 */
	@Column(length = 55)
	private String first_image_url;
	
	/**
	 * daily和voice的article
	 */
	@Column(length = 40,nullable = false)
	private String article;
	
	/**
	 * daily的first_context
	 */
	@Column(length = 40)
	private String first_context;
	
	/**
	 * voice的url
	 */
	@Column(length = 40)
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
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

	public String getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(String collect_id) {
		this.collect_id = collect_id;
	}

	public String getFirst_image_url() {
		return first_image_url;
	}

	public void setFirst_image_url(String first_image_url) {
		this.first_image_url = first_image_url;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getFirst_context() {
		return first_context;
	}

	public void setFirst_context(String first_context) {
		this.first_context = first_context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
