package app.javabean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 点赞
 * @author Jason
 *
 */
@Entity
@Table(name = "good")
public class Good {

	/**
	 * 点赞的id
	 */
	@Id
	private String id;
	
	/**
	 * 谁点赞的
	 */
	@Column(length = 255,nullable = false)
	private String user_id;
	
	/**
	 * 点赞了什么
	 */
	@Column(length = 255,nullable = false)
	private String dairy_id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDairy_id() {
		return dairy_id;
	}

	public void setDairy_id(String dairy_id) {
		this.dairy_id = dairy_id;
	}
	
}
