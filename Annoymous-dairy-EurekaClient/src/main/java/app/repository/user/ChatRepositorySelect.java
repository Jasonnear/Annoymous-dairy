package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.Chat;

public interface ChatRepositorySelect extends Repository<Chat, String>{

	/**
	 * 查找全部并去重
	 * @return
	 */
	@Query(value = "select count(distinct to_userid) from Chat where from_userid = ?1 order by chatTime")
	public List<Chat> count(String from_userid);
}
