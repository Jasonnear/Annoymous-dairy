package app.repository.user;

import org.springframework.data.repository.CrudRepository;

import app.javabean.Chat;

public interface ChatRepository extends CrudRepository<Chat, String>{

}
