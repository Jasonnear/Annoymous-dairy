package app.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import app.javabean.Comment;
import app.javabean.Daily;
import app.javabean.Future_daily;
import app.javabean.Future_voice;
import app.javabean.Record;
import app.javabean.User;
import app.javabean.Voice;
import app.javabean.Warn;
import app.repository.admin.WarnRepository;
import app.repository.user.CommentRepository;
import app.repository.user.CommentRepositorySelect;
import app.repository.user.DailyRepository;
import app.repository.user.Future_daily_Repository;
import app.repository.user.Future_voice_Repository;
import app.repository.user.RecordRepository;
import app.repository.user.UserRepository;
import app.repository.user.VoiceRepository;

@Service
public class DairyServiceDelete {

	@Resource(name = "dailyRepository")
	private DailyRepository dailyRepository;
	
	@Resource(name = "voiceRepository")
	private VoiceRepository voiceRepository;
	
	@Resource(name = "recordRepository")
	private RecordRepository recordRepository;
	
	@Resource(name = "future_daily_Repository")
	private Future_daily_Repository future_daily_Repository;
	
	@Resource(name = "future_voice_Repository")
	private Future_voice_Repository future_voice_Repository;
	
	@Resource(name = "userRepository")
	private UserRepository userRepository;
	
	@Resource(name = "commentRepositorySelect")
	private CommentRepositorySelect commentRepositorySelect;
	
	@Resource(name = "commentRepository")
	private CommentRepository commentRepository;
	
	@Resource(name = "warnRepository")
	private WarnRepository warnRepository;
	
	/**
	 * 删除daily
	 * @param id
	 * @throws Exception
	 */
	public void delete_daily(String id) throws Exception{
		Daily daily1 = dailyRepository.findOne(id);
		if(daily1 != null){
			dailyRepository.delete(id);
			Daily daily = dailyRepository.findOne(id);
			if(daily != null){
				throw new Exception();
			}
		}		
	}

	/**
	 * 删除record_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_record(String id) throws Exception{
		Record record1 = recordRepository.findOne(id);
		if(record1 != null){
			recordRepository.delete(id);
			Record record = recordRepository.findOne(id);
			if(record != null){
				throw new Exception();
			}
		}			
	}
	
	/**
	 * 删除voice_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_voice(String id) throws Exception{
		Voice voice1 = voiceRepository.findOne(id);
		if(voice1 != null){
			voiceRepository.delete(id);
			Voice voice = voiceRepository.findOne(id);
			if(voice != null){
				throw new Exception();
			}
		}		
	}
	
	/**
	 * 删除future_daily_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_future_daily(String id) throws Exception{
		Future_daily future_daily1 = future_daily_Repository.findOne(id);
		if(future_daily1 != null){
			future_daily_Repository.delete(id);
			Future_daily future_daily = future_daily_Repository.findOne(id);
			if(future_daily != null){
				throw new Exception();
			}
		}	
	}
	
	/**
	 * 删除future_voice_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_future_voice(String id) throws Exception{
		Future_voice future_voice1 = future_voice_Repository.findOne(id);
		if(future_voice1 != null){
			future_voice_Repository.delete(id);
			Future_voice future_voice = future_voice_Repository.findOne(id);
			if(future_voice != null){
				throw new Exception();
			}
		}	
	}
	
	/**
	 * 删除future_voice_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_user(String id) throws Exception{
		User user1 = userRepository.findOne(id);
		if(user1 != null){
			userRepository.delete(id);
			User user = userRepository.findOne(id);
			if(user != null){
				throw new Exception();
			}
		}	
	}

	/**
	 * 删除daily对应的评论
	 * @param id daily_id和voice_id
	 * @throws Exception 
	 */
	public void delete_comment(String id) throws Exception {		
		List<Comment> comment_list = commentRepositorySelect.findBydairyId(id);
		if(comment_list.size() != 0){
			for(Comment comment : comment_list){
				commentRepository.delete(comment.getId());
			}
		}
		List<Comment> comment_list1 = commentRepositorySelect.findBydairyId(id);
		if(comment_list1.size() != 0){
			throw new Exception();
		}
	}
}
