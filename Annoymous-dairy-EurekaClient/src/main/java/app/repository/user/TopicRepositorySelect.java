package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Topic;

public interface TopicRepositorySelect extends Repository<Topic, Integer>{

	/**
	 * 获取多有的topic
	 */
	@Query(value = "from Topic")
	public List<Topic> find_all();
	
	/**
	 * 通过内容获取topic的id
	 */
	@Query(value = "from Topic where topic_type = ?1")
	public Topic find_topic_type(String topic_type);
}
