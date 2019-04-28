package app.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Warn;

public interface WarnRepositorySelect extends Repository<Warn, String>{

	/**
	 * 查出用户对应的警告信息
	 * @param id
	 * @return
	 */
	@Query(value = "from Warn where user_id = ?1 order by warnTime desc")
	List<Warn> findAllByuser_id(String user_id);

}
