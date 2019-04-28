package app.service.admin;

import java.util.List;
import java.util.Map;

import app.javabean.Announcement;
import app.javabean.Daily;
import app.javabean.Record;
import app.javabean.User;

public interface AdminService {
	
	/**
	 * 管理员验证账户
	 * @param username
	 * @param password
	 * @return
	 */
	public User login_eq(String username,String password);

	/**
	 * 管理员注册
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean admin_register_now(String username, String password);

	/**
	 * 查看"当日暂时统计的数据"
	 * @return
	 */
	public Map count_data();

	/**
	 * 展示公开的Daily的数据
	 * @return
	 */
	public Map<String,Object> daily_list();

	/**
	 * 管理员删除公开的Daily数据
	 */
	public boolean delete_daily_list(String id);

	/**
	 * 管理员向daily发出警告信息
	 */
	public boolean warn_daily_list(String id, String user_id);

	/**
	 * 展示公开的Voice的数据
	 * @return
	 */
	public Map<String, Object> voice_list();

	/**
	 * 管理员删除公开的Voice数据
	 * @param id
	 * @return
	 */
	public boolean delete_voice_list(String id);

	/**
	 * 管理员向voice发出警告信息
	 * @param id
	 * @param user_id
	 * @return
	 */
	public boolean warn_voice_list(String id, String user_id);

	/**
	 * 展示用户的数据
	 * @return
	 */
	public List<User> user_list();

	/**
	 * 删除user的数据
	 * @param id
	 * @return
	 */
	public boolean delete_user_list(String id);

	/**
	 * 评论管理
	 * @return
	 */
	public Map<String, Object> comment_list();

	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public boolean delete_comment_list(String id);

	/**
	 * 公告保存
	 * @param announcement
	 * @param user
	 */
	public boolean announcement_save(Announcement announcement, User user);

	/**
	 * 公告管理
	 * @return
	 */
	public Map<String, Object> announcement_list();

	/**
	 * 公告删除
	 * @param id
	 * @return
	 */
	public boolean delete_announcement_list(String id);

	/**
	 * 阅读公告
	 * @param id
	 * @return
	 */
	public Announcement announcement_read(String id);

}
