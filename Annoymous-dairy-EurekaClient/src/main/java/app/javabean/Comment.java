package app.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
/**
 * 评论
 * @author Jason
 *
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable{

	/**
	 * 评论id
	 */
	@Id
	private String id;
	
	/**
	 * 评论的内容
	 */
	@Lob
	@Column(columnDefinition = "text")
	private String comment_context;
	
	/**
	 * 该评论对应的日记id
	 */
	@Column(length = 255)
	private String dairy_id;
	
	/**
	 * 日记类型
	 */
	@Column(length = 20,nullable = false)
	private String dairy_type;
	
	/**
	 * 评论时间
	 */
	@Column(length = 25)
	private Date commentTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment_context() {
		return comment_context;
	}

	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}

	public String getDairy_id() {
		return dairy_id;
	}

	public void setDairy_id(String dairy_id) {
		this.dairy_id = dairy_id;
	}

	public String getDairy_type() {
		return dairy_type;
	}

	public void setDairy_type(String dairy_type) {
		this.dairy_type = dairy_type;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
}
