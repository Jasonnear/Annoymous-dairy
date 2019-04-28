package app.repository.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Record;

public interface RecordRepositorySelect extends Repository<Record, Integer>{

	/**
	 * dairy_home页面进来时查出之前所发布的所有消息
	 * @return
	 */
	@Query(value = "from Record where topic_id in(?1) order by createTime")
	public List<Record> show_all(Set<Integer> topic_id_set);

	
	/**
	 * 选择器Daily数据
	 * @param set_topic_id
	 * @return
	 */
	@Query(value = "from Record where topic_id in(?1) and dairytype_id = 0 order by createTime")
	public List<Record> show_daily(Set<Integer> set_topic_id);

	/**
	 * 选择器Voice数据
	 * @param set_topic_id
	 * @return
	 */
	@Query(value = "from Record where topic_id in(?1) and dairytype_id = 1 order by createTime")
	public List<Record> show_voice(Set<Integer> set_topic_id);

	/**
	 * 检查用户是否公开发过该topic的daily和voice
	 * @param user_id
	 * @param top_id
	 */
	@Query(value = "from Record where user_id = ?1 and topic_id = ?2")
	public List<Record> find_topic(String user_id, int topic_id);

	/**
	 * 查询topic的数据
	 * @param id
	 */
	@Query(value = "from Record where topic_id = ?1")
	public List<Record> show_topic(int topic_id);


	/**
	 * 查询发布的daily和voice
	 * @param i
	 * @return
	 */
	@Query(value = "from Record where dairytype_id = ?1")
	public List<Record> findAllBydairy_type(int i);




}
