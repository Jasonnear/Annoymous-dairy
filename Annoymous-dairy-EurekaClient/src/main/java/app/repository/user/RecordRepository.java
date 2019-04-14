package app.repository.user;

import org.springframework.data.repository.CrudRepository;

import app.javabean.Record;

public interface RecordRepository extends CrudRepository<Record, String>{

}
