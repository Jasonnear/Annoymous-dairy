package app.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 非邮件提醒功能
 * @author Jason
 *
 */
@Entity
@Table(name = "warn")
public class Warn implements Serializable{

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 标题
	 */
	@Column(length = 40 , nullable = false)
	private String article;
	
	/**
	 * 非邮件提醒的内容
	 */
	@Column(length = 255 , nullable = false)
	private String context;
	
	/**
	 * 警告时间
	 */
	@Column
	private Date warnTime;
	
	/**
	 * 用户是否已读改"非邮件"
	 */
	@Column
	private boolean isread = false;
	
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getWarnTime() {
		return warnTime;
	}

	public void setWarnTime(Date warnTime) {
		this.warnTime = warnTime;
	}

	public boolean isIsread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
