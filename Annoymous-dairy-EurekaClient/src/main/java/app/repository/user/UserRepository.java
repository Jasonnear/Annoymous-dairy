package app.repository.user;

import org.springframework.data.repository.CrudRepository;

import app.javabean.User;

public interface UserRepository extends CrudRepository<User, String>{

}
