package app.repository.admin;

import org.springframework.data.repository.CrudRepository;

import app.javabean.Announcement;

public interface AnnouncementRepository extends CrudRepository<Announcement, String>{

}
