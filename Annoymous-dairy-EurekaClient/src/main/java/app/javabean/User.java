package app.javabean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户表
 * @author Jason
 *
 */
@Entity
@Table(name = "user")
public class User {

	/**
	 * user的唯一标识
	 */
	@Id
	private String id;
	
	/**
	 * 邮箱
	 */	
	@Column(length = 20,unique = true)
	private String email;
	
	/**
	 * 验证码
	 */
	@Column(length = 10)
	private String code;
	
	/**
	 * 账号
	 */
	@Column(length = 20,nullable = false,unique=true)
	private String username;
	
	/**
	 * 密码
	 */
	@Column(length = 20,nullable = false)
	private String password;
	
	/**
	 * 性别:0为女 1为男
	 */
	@Column(length = 1)
	private String sex;
	
	/**
	 * 头像
	 */
	@Lob
	@Column(columnDefinition = "text")
	private String head_image;
	
	/**
	 * 账户创建时间
	 */
	@Column(length = 25)
	private Date create_time;
	
	/**
	 * 用户最近登录的时间
	 */
	@Column(length = 25)
	private Date login_time;

	/**
	 * User:daily = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user_id")
	private List<Daily> daily_list = new ArrayList<Daily>();
	
	/**
	 * User:voice = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user_id")
	private List<Voice> voice_list = new ArrayList<Voice>();
	
	/**
	 * User:future_daily = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user_id")
	private List<Future_daily> future_daily_list = new ArrayList<Future_daily>();
	
	/**
	 * User:future_voice = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user_id")
	private List<Future_voice> future_voice_list = new ArrayList<Future_voice>();
	
	/**
	 * User:chat(from_userid) = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "from_userid")
	private List<Chat> chat_from_userid_list = new ArrayList<Chat>();
	
	/**
	 * User:chat(to_userid) = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "to_userid")
	private List<Chat> chat_to_userid_list = new ArrayList<Chat>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHead_image() {
		return head_image;
	}

	public void setHead_image(String head_image) {
		this.head_image = head_image;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public List<Daily> getDaily_list() {
		return daily_list;
	}

	public void setDaily_list(List<Daily> daily_list) {
		this.daily_list = daily_list;
	}

	public List<Voice> getVoice_list() {
		return voice_list;
	}

	public void setVoice_list(List<Voice> voice_list) {
		this.voice_list = voice_list;
	}

	public List<Future_daily> getFuture_daily_list() {
		return future_daily_list;
	}

	public void setFuture_daily_list(List<Future_daily> future_daily_list) {
		this.future_daily_list = future_daily_list;
	}

	public List<Future_voice> getFuture_voice_list() {
		return future_voice_list;
	}

	public void setFuture_voice_list(List<Future_voice> future_voice_list) {
		this.future_voice_list = future_voice_list;
	}

	public List<Chat> getChat_from_userid_list() {
		return chat_from_userid_list;
	}

	public void setChat_from_userid_list(List<Chat> chat_from_userid_list) {
		this.chat_from_userid_list = chat_from_userid_list;
	}

	public List<Chat> getChat_to_userid_list() {
		return chat_to_userid_list;
	}

	public void setChat_to_userid_list(List<Chat> chat_to_userid_list) {
		this.chat_to_userid_list = chat_to_userid_list;
	}
	
	
}
