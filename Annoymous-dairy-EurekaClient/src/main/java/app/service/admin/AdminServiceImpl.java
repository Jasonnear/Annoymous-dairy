package app.service.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import app.javabean.Announcement;
import app.javabean.Comment;
import app.javabean.Daily;
import app.javabean.Future_daily;
import app.javabean.Future_voice;
import app.javabean.Record;
import app.javabean.User;
import app.javabean.Voice;
import app.javabean.Warn;
import app.repository.admin.AnnouncementPagingAndSortingRepository;
import app.repository.admin.AnnouncementRepository;
import app.repository.admin.WarnRepository;
import app.repository.admin.WarnRepositorySelect;
import app.repository.user.CommentRepository;
import app.repository.user.CommentRepositorySelect;
import app.repository.user.DailyRepository;
import app.repository.user.DailyRepositorySelect;
import app.repository.user.RecordRepositorySelect;
import app.repository.user.UserRepository;
import app.repository.user.UserRepositorySelect;
import app.repository.user.VoiceRepository;
import app.repository.user.VoiceRepositorySelect;
import app.service.user.DairyServiceDelete;
import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
@Service
public class AdminServiceImpl implements AdminService{

	@Resource(name = "dairyServiceDelete")
	private DairyServiceDelete dairyServiceDelete;
	
	@Resource(name = "userRepository")
	private UserRepository userRepository;
	
	@Resource(name = "userRepositorySelect")
	private UserRepositorySelect userRepositorySelect;
	
	@Resource(name = "dailyRepository")
	private DailyRepository dailyRepository;
	
	@Resource(name = "voiceRepository")
	private VoiceRepository voiceRepository;
	
	@Resource(name = "dailyRepositorySelect")
	private DailyRepositorySelect dailyRepositorySelect;
	
	@Resource(name = "voiceRepositorySelect")
	private VoiceRepositorySelect voiceRepositorySelect;
	
	@Resource(name = "recordRepositorySelect")
	private RecordRepositorySelect recordRepositorySelect;
	
	@Resource(name = "commentRepositorySelect")
	private CommentRepositorySelect commentRepositorySelect;
	
	@Resource(name = "commentRepository")
	private CommentRepository commentRepository;
	
	@Resource(name = "announcementRepository")
	private AnnouncementRepository announcementRepository;
	
	@Resource(name = "announcementPagingAndSortingRepository")
	private AnnouncementPagingAndSortingRepository announcementPagingAndSortingRepository;
	
	@Resource(name = "warnRepository")
	private WarnRepository warnRepository;
	
	@Resource(name = "warnRepositorySelect")
	private WarnRepositorySelect warnRepositorySelect;
	
	/**
	 * 管理员验证
	 */
	@Override
	public User login_eq(String username,String password) {
		User user =  userRepositorySelect.check_admin(username,password);
		if(user != null){
			user.setLogin_time(new Date());
			return userRepository.save(user);
		}
		return user;
	}

	/**
	 * 管理员注册
	 */
	@Override
	public boolean admin_register_now(String username, String password) {
		//先查看user表示是否有该管理员
		User user = userRepositorySelect.check_admin(username,password);
		if(user == null){
			User user1 = new User();
			user1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			user1.setUsername(username);
			user1.setPassword(password);
			user1.setCora(1);
			user1.setCreate_time(new Date());
			user1.setLogin_time(new Date());
			User user2 = userRepository.save(user1);
			return true;
		}
		return false;
	}

	@Override
	public Map count_data() {
		//查看今日的发布daily和voice的数量[record]
		List<Record> daily_list = recordRepositorySelect.findAllBydairy_type(0);
		List<Record> voice_list = recordRepositorySelect.findAllBydairy_type(1);
		//查看进入用户访问量
		List<User> user_list = userRepositorySelect.finAll();
		//new SimpleDateFormat("yyyy-MM-dd").format(new Date())
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		int daily_count = 0;
		int voice_count = 0;
		int user_count = 0;
		if(daily_list.size() != 0 ){
			for(Record record : daily_list){
				if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(new SimpleDateFormat("yyyy-MM-dd").format(record.getCreateTime()))){
					daily_count++;
				}
			}
		}
		
		if(voice_list.size() != 0 ){
			for(Record record : voice_list){
				if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(new SimpleDateFormat("yyyy-MM-dd").format(record.getCreateTime()))){
					voice_count++;
				}
			}
		}
		
		if(user_list.size() != 0){
			for(User user : user_list){
				if(user.getLogin_time() != null){
					if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(new SimpleDateFormat("yyyy-MM-dd").format(user.getLogin_time()))){
						user_count++;
					}
				}			
			}
		}
		
		map.put("daily_count", daily_count);
		map.put("voice_count", voice_count);
		map.put("user_count", user_count);
		return map;
	}

	/**
	 * 展示所有daily数据
	 */
	@Override
	public Map<String,Object> daily_list() {
		List<Daily> daily_list = dailyRepositorySelect.findByPermission(true,true);
		//日记对应的用户信息
		List<User> user_daily_list = new ArrayList<User>();
		//返回值
		Map<String,Object> map = new HashMap<String,Object>();
		for(Daily daily : daily_list){
			//查出Daily对应的用户信息
			User user = userRepository.findOne(daily.getUser_id());
			user_daily_list.add(user);
		}
		map.put("daily_list", daily_list);
		map.put("user_daily_list", user_daily_list);
		return map;
	}

	/**
	 * 管理员删除公开的Daily数据
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delete_daily_list(String id) {
		try {
			dairyServiceDelete.delete_daily(id);
			dairyServiceDelete.delete_record(id);
			dairyServiceDelete.delete_comment(id);
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	/**
	 * 管理员向公开的daily发出警告信息
	 */
	@Override
	public boolean warn_daily_list(String id, String user_id) {
		User user = userRepository.findOne(user_id);
		Daily daily = dailyRepository.findOne(id);
		if(user.getEmail() != null){
			int b = -1;
			StringBuffer all_info = new StringBuffer();
			StringBuffer warn_info = new StringBuffer();
			InputStream ins = null;
			Connection conn = new Connection("39.108.236.62");			
			try {
				conn.connect();
				conn.authenticateWithPassword("root","jiezi,./15");
				Session session;
				session = conn.openSession();
				session.execCommand("/usr/dairy/sendmail/warn_mail.sh " + user.getEmail() + " " + daily.getArticle());
				ins = new StreamGobbler(session.getStdout());
				session.waitForCondition(ChannelCondition.EXIT_STATUS, 5000);
				while((b=ins.read())!=-1){
					all_info.append((char)b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				conn.close();
			}
			//处理返回的数据
			for (int i = 0; i < all_info.length(); i++) {
				if(all_info.charAt(i) >= 'a' && all_info.charAt(i) <= 'z'){
					warn_info.append(all_info.charAt(i));
				}
			}
			if(warn_info.toString().equals("success")){
				System.out.println("success");
				//为warn数据库插入数据 warnRepository
				Warn warn = new Warn();
				warn.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				warn.setArticle("日常日记警告通知");
				warn.setContext("您的日常日记：" + daily.getArticle() + " 涉嫌传播非正能量，请尽快删除，否则系统管理员自行删除!");
				warn.setUser_id(user_id);
				warn.setWarnTime(new Date());
				Warn warn1 = warnRepository.save(warn);
				if(warn1 != null){
					return true;
				}else{
					warnRepository.delete(warn1.getId());
					return false;
				}
				
			}else{
				System.out.println("failure");
				return false;
			}
		}else{
			//为warn数据库插入数据 warnRepository
			Warn warn = new Warn();
			warn.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			warn.setArticle("日常日记警告通知");
			warn.setContext("您的日常日记:" + daily.getArticle() + " 涉嫌传播非正能量,请尽快删除,否则系统管理员自行删除");
			warn.setUser_id(user_id);
			warn.setWarnTime(new Date());
			Warn warn1 = warnRepository.save(warn);
			if(warn1 != null){
				return true;
			}else{
				warnRepository.delete(warn1.getId());
				return false;
			}
		}
	
	}

	/**
	 * 展示公开的Voice的数据
	 */
	@Override
	public Map<String, Object> voice_list() {
		List<Voice> voice_list = voiceRepositorySelect.findByPermission(true,true);
		//日记对应的用户信息
		List<User> user_daily_list = new ArrayList<User>();
		//返回值
		Map<String,Object> map = new HashMap<String,Object>();
		for(Voice voice : voice_list){
			//查出Daily对应的用户信息
			User user = userRepository.findOne(voice.getUser_id());
			user_daily_list.add(user);
		}
		map.put("voice_list", voice_list);
		map.put("user_daily_list", user_daily_list);
		return map;
	}

	/**
	 * 管理员删除公开的Voice数据
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delete_voice_list(String id) {
		try {
			dairyServiceDelete.delete_voice(id);
			dairyServiceDelete.delete_record(id);
			dairyServiceDelete.delete_comment(id);
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	/**
	 * 管理员向voice发出警告信息
	 */
	@Override
	public boolean warn_voice_list(String id, String user_id) {
		User user = userRepository.findOne(user_id);
		Voice voice =voiceRepository.findOne(id);
		if(user.getEmail() != null){
			int b = -1;
			StringBuffer all_info = new StringBuffer();
			StringBuffer warn_info = new StringBuffer();
			InputStream ins = null;
			Connection conn = new Connection("39.108.236.62");			
			try {
				conn.connect();
				conn.authenticateWithPassword("root","jiezi,./15");
				Session session;
				session = conn.openSession();
				session.execCommand("/usr/dairy/sendmail/warn_mail.sh " + user.getEmail() + " " + voice.getArticle());
				ins = new StreamGobbler(session.getStdout());
				session.waitForCondition(ChannelCondition.EXIT_STATUS, 5000);
				while((b=ins.read())!=-1){
					all_info.append((char)b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				conn.close();
			}
			//处理返回的数据
			for (int i = 0; i < all_info.length(); i++) {
				if(all_info.charAt(i) >= 'a' && all_info.charAt(i) <= 'z'){
					warn_info.append(all_info.charAt(i));
				}
			}
			if(warn_info.toString().equals("success")){
				System.out.println("success");
				//为warn数据库插入数据 warnRepository
				Warn warn = new Warn();
				warn.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				warn.setArticle("回声日记警告通知");
				warn.setContext("您的回声日记：" + voice.getArticle() + " 涉嫌传播非正能量，请尽快删除，否则系统管理员自行删除!");
				warn.setUser_id(user_id);
				warn.setWarnTime(new Date());
				Warn warn1 = warnRepository.save(warn);
				if(warn1 != null){
					return true;
				}else{
					warnRepository.delete(warn1.getId());
					return false;
				}
			}else{				
				return false;
			}
		}else{
			Warn warn = new Warn();
			warn.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			warn.setArticle("回声日记警告通知");
			warn.setContext("您的回声日记:" + voice.getArticle() + " 涉嫌传播非正能量,请尽快删除,否则系统管理员自行删除");
			warn.setUser_id(user_id);
			warn.setWarnTime(new Date());
			Warn warn1 = warnRepository.save(warn);
			if(warn1 != null){
				return true;
			}else{
				warnRepository.delete(warn1.getId());
				return false;
			}
		}
	}

	/**
	 * 展示用户的数据
	 */
	@Override
	public List<User> user_list() {
		return userRepositorySelect.finAll();
	}

	/**
	 * 删除user的数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete_user_list(String id) {
		User user = userRepository.findOne(id);		
		try {
			dairyServiceDelete.delete_user(id);
			List<Daily> daily_list = user.getDaily_list();
			List<Voice> voice_list = user.getVoice_list();
			List<Future_daily> future_daily_list = user.getFuture_daily_list();
			List<Future_voice> future_voice_list = user.getFuture_voice_list();			
			if(daily_list.size() != 0){
				for(Daily daily : daily_list){
					dairyServiceDelete.delete_daily(daily.getId());
					//删除该daily的评论
					dairyServiceDelete.delete_comment(daily.getId());
				}
			}
			if(voice_list.size() != 0){
				for(Voice voice : voice_list){
					dairyServiceDelete.delete_voice(voice.getId());
					//删除该voice的评论
					dairyServiceDelete.delete_comment(voice.getId());
				}
			}
			if(future_daily_list.size() != 0){
				for(Future_daily future_daily : future_daily_list){
					dairyServiceDelete.delete_future_daily(future_daily.getId());
				}
			}
			if(future_voice_list.size() != 0){
				for(Future_voice future_voice : future_voice_list){
					dairyServiceDelete.delete_future_voice(future_voice.getId());
				}
			}			
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	/**
	 * 评论管理
	 */
	@Override
	public Map<String, Object> comment_list() {
		//查出所有的评论
		List<Comment> comment_daily_list = commentRepositorySelect.findBydairyType("0");
		List<Comment> comment_voice_list = commentRepositorySelect.findBydairyType("1");
		
		//存放user
		List<User> user_comment_daily_list = new ArrayList<User>();
		List<User> user_comment_voice_list = new ArrayList<User>();
		//存放所评论的日记
		List<Daily> daily_list = new ArrayList<Daily>();
		List<Voice> voice_list = new ArrayList<Voice>();
		
		//Map
		Map<String,Object> map = new HashMap<String,Object>();
		
		//查出对应的user
		if(comment_daily_list.size() != 0){
			for(Comment comment_daily : comment_daily_list){
				Daily daily = dailyRepository.findOne(comment_daily.getDairy_id());				
				User user = userRepository.findOne(daily.getUser_id());
				daily_list.add(daily);
				user_comment_daily_list.add(user);
			}
			map.put("comment_daily_list", comment_daily_list);
			map.put("daily_list", daily_list);
			map.put("user_comment_daily_list", user_comment_daily_list);
		}
		
		if(comment_voice_list.size() != 0){
			for(Comment comment_voice : comment_voice_list){
				Voice voice = voiceRepository.findOne(comment_voice.getDairy_id());
				User user = userRepository.findOne(voice.getUser_id());
				voice_list.add(voice);
				user_comment_voice_list.add(user);
			}
			map.put("comment_voice_list", comment_voice_list);
			map.put("voice_list", voice_list);
			map.put("user_comment_voice_list", user_comment_voice_list);
		}
		
		if(map.size() != 0){
			return map;
		}
		return null;
	}

	/**
	 * 删除评论
	 */
	@Override
	public boolean delete_comment_list(String id) {
		Comment comment = commentRepository.findOne(id);
		if(comment != null){
			commentRepository.delete(comment);
			Comment comment1 = commentRepository.findOne(id);
			if(comment1 == null){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	
	/**
	 * 公告保存
	 */
	@Override
	public boolean announcement_save(Announcement announcement, User user) {
		announcement.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		announcement.setCreateTime(new Date());
		announcement.setUpdateTime(new Date());
		announcement.setUser_id(user.getId());
		Announcement announcement1 = announcementRepository.save(announcement);
		if(announcement1 != null){
			return true;
		}
		return false;
	}

	/**
	 * 公告管理
	 */
	@Override
	public Map<String, Object> announcement_list() {
		//存放公告
		List<Announcement> announcement_list  = new ArrayList<Announcement>();
		//存放公告对应的user
		List<User> user_announcement_list = new ArrayList<User>();
		//Map
		Map<String, Object> map = new HashMap<String, Object>();
		Sort sort=new Sort(Sort.DEFAULT_DIRECTION.DESC,"updateTime");
		Iterable<Announcement> iterable_annoucement = announcementPagingAndSortingRepository.findAll(sort);
		Iterator<Announcement> iterator_announcement = iterable_annoucement.iterator();
		while(iterator_announcement.hasNext()){
			Announcement announcement = iterator_announcement.next();
			announcement_list.add(announcement);
		}
		//判断是否有值
		if(announcement_list.size() != 0){
			for(Announcement announcement : announcement_list){
				User user = userRepository.findOne(announcement.getUser_id());
				user_announcement_list.add(user);
			}
		}
		if(announcement_list.size() !=0 && user_announcement_list.size() != 0){
			map.put("announcement_list", announcement_list);
			map.put("user_announcement_list", user_announcement_list);
			return map;
		}
		return null;
	}

	/**
	 * 公告删除
	 */
	@Override
	public boolean delete_announcement_list(String id) {
		Announcement announcement = announcementRepository.findOne(id);
		if(announcement != null){
			announcementRepository.delete(announcement);
			Announcement announcement1 = announcementRepository.findOne(id);
			if(announcement1 == null){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	/**
	 * 阅读公告
	 */
	@Override
	public Announcement announcement_read(String id) {
		return announcementRepository.findOne(id);
	}


}
