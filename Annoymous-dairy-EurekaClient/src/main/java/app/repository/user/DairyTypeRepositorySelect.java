package app.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import app.javabean.DairyType;

public interface DairyTypeRepositorySelect extends Repository<DairyType, Integer>{

	/**
	 * 查询所有的DairyType
	 */
	@Query(value = "from DairyType")
	public List<DairyType> find_all();
	
	/**
	 * 根据dairy_type查找id
	 */
	@Query(value = "select id from DairyType where dairy_type = ?1")
	public int findBydairy_type(String dairy_type);
}
