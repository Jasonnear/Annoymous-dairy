package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Daily;
import app.javabean.Voice;

public interface VoiceRepositorySelect extends Repository<Voice, String>{

	/**
	 * topic为0的时候查出全部用户id
	 */
	@Query(value = "select distinct user_id from Voice")
	public List<String> find_all();
	
	@Query(value = "from Voice where id in(?1)")
	public List<Voice> find_in(List<String> voice_list);
	
	/**
	 * topic不为0的时候查出发布过该专题的用户
	 * @return
	 */
	@Query(value = "select distinct user_id from Voice where topic_id = ?1")
	public List<String> send(int topic_id);
	
	/**
	 * 通过Voice的主键查找
	 * @param id
	 * @return
	 */
	@Query(value = "from Voice where id = ?1")
	public Voice find_one(String id);
	
	/**
	 * 查询用户所有的voice
	 */
	@Query(value = "from Voice where user_id = ?1 order by createTime")
	public List<Voice> find_all1(String user_id);

}
