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

	
}
