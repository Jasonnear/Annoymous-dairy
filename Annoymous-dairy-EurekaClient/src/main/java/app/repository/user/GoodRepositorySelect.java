package app.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Good;

public interface GoodRepositorySelect extends Repository<Good, String>{

	@Query(value = "from Good where dairy_id = ?1 and user_id = ?2")
	public Good findBydairy_idAnduser_id(String dairy_id,String user_id);

	/**
	 * 统计日记的点赞数
	 * @param dairy_id
	 */
	@Query(value = "select count(*) from Good where dairy_id = ?1")
	public int count(String dairy_id);
}
