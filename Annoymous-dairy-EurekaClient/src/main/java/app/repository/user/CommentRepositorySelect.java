package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Comment;

public interface CommentRepositorySelect extends Repository<Comment, String>{

	/**
	 * 展示评论
	 * @param dairy_id
	 * @param comment_reply
	 * @return
	 */
	@Query(value = "from Comment where dairy_id = ?1")
	public List<Comment> show_comment(String dairy_id);

	/**
	 * 查询评论daily和voice的评论
	 * @param string
	 * @return
	 */
	@Query(value = "from Comment where dairy_type = ?1")
	public List<Comment> findBydairyType(String dairy_type);

	/**
	 * 根据dairy_id删除对应的评论
	 * @param id
	 * @return
	 */
	@Query(value = "from Comment where dairy_id = ?1")
	public List<Comment> findBydairyId(String dairy_id);


	
}
