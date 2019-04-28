package app.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import app.config.JsonObject;
import app.javabean.Announcement;
import app.javabean.Chat;
import app.javabean.Collect;
import app.javabean.Comment;
import app.javabean.Daily;
import app.javabean.DairyType;
import app.javabean.Future_daily;
import app.javabean.Future_voice;
import app.javabean.Topic;
import app.javabean.User;
import app.javabean.Voice;
import app.javabean.Warn;

public interface DairyService {

	/**
	 * 查询topic类型
	 */
	public List<Topic> dairy_home();
	/**
	 * 查询DairyType类型
	 */
	public List<DairyType> dairy_myself();
	/**
	 * 检查邮箱是否已经注册
	 * @param email
	 * @return
	 */
	public JsonObject check_email(String email);
	
	/**
	 * 检查账号是否已经注册
	 * @param username
	 * @return
	 */
	public JsonObject check_username(String username);
	
	/**
	 * 点击注册按钮进行注册
	 * @param user
	 * @return
	 */
	public JsonObject register_now(User user);
	
	/**
	 * 用户使用邮箱登录时获取验证码
	 * @param email
	 */
	public JsonObject login_getcode(String email);
	
	/**
	 * 通过email认证
	 * @param user
	 * @return
	 */
	public User login_authenticate_email(User user);
	
	/**
	 * 获取topic的内容
	 */
	public List<Topic> get_topic();
	
	/**
	 * 通过username和password认证
	 * @param user
	 * @return
	 */
	public User login_authenticate_username(User user);
	
	/**
	 * 将md和html存入到Mysql中
	 */
	public JsonObject daily_save(Daily daily,User user);
	
	
	/**
	 * 保存voice
	 */
	public Voice voice_upload(String url,User user,Voice voice);
	
	/**
	 * 发布消息的第一种情况
	 */
	public void send1(List<Daily> daily_list, List<Voice> voice_list);
	
	/**
	 * 发布消息的第二种情况
	 */
	public void send2(Daily daily);
	
	/**
	 * 发布消息的第三种情况
	 */
	public void send3(Voice voice);
	
	/**
	 * 展示所有的发布消息
	 */
	public JsonObject show_all(String id);
	
	/**
	 * 展示部分发布的消息
	 */
	public JsonObject show_part(String id,HttpSession session);
	
	/**
	 * 选择器的Daily数据展示
	 */
	public JsonObject show_daily(String id);

	/**
	 * 选择器的Voice数据展示
	 */
	public JsonObject show_voice(String id);

	/**
	 * 选择器 topic-feel
	 * @param id
	 * @return
	 */
	public JsonObject show_topic(int topic_id,String id);
	
	/**
	 * future_daily保存用户所编写的内容
	 * @param future_daily
	 * @param user
	 * @return
	 */
	public JsonObject future_daily_save(Future_daily future_daily, User user);
	
	/**
	 * future_voice上传录音
	 */
	public Future_voice future_voice_upload(String url, User user, Future_voice future_voice);
	
	/**
	 * 定时器
	 * 每天的0点0时0分触发
	 */
	public void open();
	
	/**
	 * 查找所有的User对应的Daily
	 * @param user
	 */
	public List<Daily> findUserDaily(User user);
	
	
	/**
	 * 查询用户对应的voice
	 * @param user
	 * @return
	 */
	public List<Voice> findUserVoice(User user);
	
	/**
	 * 查询用户对应的future daily
	 * @param user
	 * @return
	 */
	public List<Future_daily> findUserFutureDaily(User user);
	
	/**
	 * 查询用户对应future voice
	 */
	public List<Future_voice> findUserFutureVoice(User user);
	
	/**
	 * 查询用户对应collect
	 */
	public List<Collect> findUserCollect(User user);
	
	/**
	 * 点击ReadMore展示用户完整的daily
	 * @param id
	 * @return
	 */
	public Daily show_daily_myself(String id);
	
	/**
	 * 删除用户完整的daily
	 * @param id
	 * @return
	 */
	public boolean delete_daily_myself(String id);
	
	/**
	 * 删除用户完整的voice
	 * @param id
	 * @return
	 */
	public boolean delete_voice_myself(String id);
	
	/**
	 * 点击ReadMore展示用户完整的future_daily
	 * @param id
	 * @return
	 */
	public Future_daily show_future_daily_myself(String id);
	
	/**
	 * 显示更多
	 * @param dairy_type
	 * @param dairy_type2 
	 * @return 
	 */
	public List<Object> show_more(String user_id,String dairy_type);
	
	/**
	 * 删除用户完整的future_daily
	 */
	public boolean delete_future_daily_myself(String id);
	
	/**
	 * 删除用户完整的future_voice
	 */
	public boolean delete_future_voice_myself(String id);
	
	/**
	 * 收藏daily
	 * @param id
	 * @param user_id
	 */
	public Collect collect_daily(String id,String user_id);
	
	/**
	 * 收藏voice
	 * @param id
	 * @param user_id
	 * @return
	 */
	public Collect collect_voice(String id, String user_id);
	
	/**
	 * 保存comment
	 * @param comment
	 * @return
	 */
	public Comment save_comment(Comment comment);
	
	/**
	 * 展示文章的评论
	 * @param dairy_id
	 * @return
	 */
	public List<Comment> show_comment(String dairy_id);
	
	/**
	 * 增加点赞数
	 * @param id
	 * @return
	 */
	public int add_gooCount(String dairy_id,String user_id);
	
	/**
	 * 查询所有的公告
	 * @return
	 */
	public List<Announcement> show_announcement();
	
	/**
	 * 公告发布
	 */
	public void sendAnnouncement();
	
	/**
	 * 阅读公告
	 * @param id
	 * @return
	 */
	public Announcement announcement_read(String id);
	
	/**
	 * 用户修改个人信息
	 * @param user1
	 */
	public User user_update_info(User user1);
	
	/**
	 * 查出用户对应的警告信息
	 * @param user
	 * @return
	 */
	public List<Warn> dairy_remind(User user);
	
	/**
	 * 非邮件提醒将未读变为可读
	 * @param id
	 * @return
	 */
	public boolean can_read(String id);
	
	/**
	 * 查看是否存在有未读的非邮件，若有图标则显示【1】，否则不显示【0】
	 * @param id
	 * @return
	 */
	public boolean isremind(String id);
	
}
