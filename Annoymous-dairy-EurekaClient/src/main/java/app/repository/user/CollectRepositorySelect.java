package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Collect;

public interface CollectRepositorySelect extends Repository<Collect, String>{

	@Query(value = "from Collect where collect_id = ?1 and user_id = ?2")
	public Collect findByidAnduserid(String id,String user_id);
	
	/**
	 * 查询用户对应的全部collect
	 */
	@Query(value = "from Collect where user_id = ?1 order by collectionTime")
	public List<Collect> find_all(String user_id);
}
