package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Daily;

public interface DailyRepositorySelect extends Repository<Daily, String>{

	
	/**
	 * topic不为0的时候查出发布过该专题的用户
	 * @param topic_id
	 * @return
	 */
	@Query(value = "select distinct user_id from Daily where topic_id = ?1")
	public List<String> send(int topic_id);
	
	@Query(value = "from Daily where id = ?1")
	public Daily find_one(String id);
	
	/**
	 * 用in查找全部
	 */
	@Query(value = "from Daily where id in(?1)")
	public List<Daily> find_in(List<String> daily_list);
	
	/**
	 * 用户查询对应的所有数据
	 */
	@Query(value = "from Daily where user_id = ?1 order by createTime")
	public List<Daily> find_all(String user_id);

	/**
	 * 查找全部
	 * @return
	 */
	@Query(value = "from Daily")
	public List<Daily> findAll();
	
	/**
	 * 查找公开的全部Daily数据
	 */
	@Query(value = "from Daily where permission = ?1 and pm = ?2")
	public List<Daily> findByPermission(boolean permission,boolean pm);

}
