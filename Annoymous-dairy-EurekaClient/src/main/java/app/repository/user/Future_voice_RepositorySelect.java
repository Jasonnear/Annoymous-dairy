package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import app.javabean.Future_voice;

public interface Future_voice_RepositorySelect extends Repository<Future_voice, String>{

	@Query(value = "from Future_voice where isOpen = false")
	public List<Future_voice> find_all();

	/**
	 * 查询用户所有对应的future voice
	 * @param id
	 */
	@Query(value = "from Future_voice where user_id = ?1 order by createTime")
	public List<Future_voice> find_all1(String user_id);
}
