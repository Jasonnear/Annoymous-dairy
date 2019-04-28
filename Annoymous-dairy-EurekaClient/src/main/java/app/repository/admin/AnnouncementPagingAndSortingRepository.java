package app.repository.admin;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.javabean.Announcement;

public interface AnnouncementPagingAndSortingRepository extends PagingAndSortingRepository<Announcement, String>{

}
