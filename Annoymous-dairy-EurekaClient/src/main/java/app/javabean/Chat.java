package app.javabean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "chat")
public class Chat {

	@Id
	private String id;
	
	/**
	 * 发送者
	 */
	@Column(length = 255)
	private String from_userid;
	
	/**
	 * 接收者
	 */
	@Column(length = 255)
	private String to_userid;
	
	/**
	 * 内容
	 */
	@Lob
	@Column(columnDefinition = "text")
	private String chat_context;
	
	/**
	 * 内容发送时间
	 */
	@Column(length = 25)
	private Date chatTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom_userid() {
		return from_userid;
	}

	public void setFrom_userid(String from_userid) {
		this.from_userid = from_userid;
	}

	public String getTo_userid() {
		return to_userid;
	}

	public void setTo_userid(String to_userid) {
		this.to_userid = to_userid;
	}

	public String getChat_context() {
		return chat_context;
	}

	public void setChat_context(String chat_context) {
		this.chat_context = chat_context;
	}

	public Date getChatTime() {
		return chatTime;
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
}
