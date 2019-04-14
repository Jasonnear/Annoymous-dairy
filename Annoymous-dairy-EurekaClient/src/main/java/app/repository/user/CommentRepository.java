package app.repository.user;

import org.springframework.data.repository.CrudRepository;

import app.javabean.Comment;

public interface CommentRepository extends CrudRepository<Comment, String>{

	
}
