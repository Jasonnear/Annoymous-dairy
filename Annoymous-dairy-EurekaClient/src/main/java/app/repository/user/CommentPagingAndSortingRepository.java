package app.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.javabean.Comment;

public interface CommentPagingAndSortingRepository extends PagingAndSortingRepository<Comment, String>{

}
