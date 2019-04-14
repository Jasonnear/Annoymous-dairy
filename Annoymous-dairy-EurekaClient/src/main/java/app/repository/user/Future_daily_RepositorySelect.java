package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Future_daily;

public interface Future_daily_RepositorySelect extends Repository<Future_daily, String>{

	@Query(value = "from Future_daily where isOpen = false")
	public List<Future_daily> find_all();
	
	/**
	 * 查询用户所有的future daily
	 */
	@Query(value = "from Future_daily where user_id = ?1 order by createTime")
	public List<Future_daily> find_all1(String user_id);
}
