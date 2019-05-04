package app.repository.user;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.User;

public interface UserRepositorySelect extends Repository<User, String>{

	/**
	 * 检查邮箱是否已经注册
	 */
	@Query(value = "from User where email = ?1 and cora = 0")
	public User check_email(String email);
	
	/**
	 * 检查账号是否已经注册
	 */
	@Query(value = "from User where username = ?1 and cora = 0")
	public User check_username(String username);
	
	/**
	 * 通过email主键查找
	 * @param email
	 * @return
	 */
	@Query(value = "from User where email = ?1 and cora = 0")
	public User login_getcode(String email);
	
	/**
	 * 通过email和code验证信息
	 * @param email
	 * @param code
	 * @return
	 */
	@Query(value = "from User where email = ?1 and code = ?2 and cora = 0")
	public User login_authenticate_email(String email,String code);
	
	/**
	 * 通过username和password验证信息
	 * @param username
	 * @param password
	 * @return
	 */
	@Query(value = "from User where username = ?1 and password = ?2 and cora = 0")
	public User login_authenticate_username(String username,String password);

	/**
	 * 查出所有的用户
	 * @return
	 */
	@Query(value = "select id from User where cora = 0")
	public List<String> find_all();

	/**
	 * 检查是否存在该admin
	 * @param username
	 * @param password
	 */
	@Query(value = "from User where username = ?1 and password = ?2 and cora = 1")
	public User check_admin(String username, String password);

	/**
	 * 查询全部
	 * @return
	 */
	@Query(value = "from User where cora = 0")
	public List<User> finAll();

}
