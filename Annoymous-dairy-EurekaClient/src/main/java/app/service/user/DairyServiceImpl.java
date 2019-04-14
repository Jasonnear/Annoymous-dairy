package app.service.user;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import app.config.JsonObject;
import app.javabean.Collect;
import app.javabean.Comment;
import app.javabean.Daily;
import app.javabean.DairyType;
import app.javabean.Future_daily;
import app.javabean.Future_voice;
import app.javabean.Good;
import app.javabean.Record;
import app.javabean.Topic;
import app.javabean.User;
import app.javabean.Voice;
import app.repository.user.ChatRepository;
import app.repository.user.ChatRepositorySelect;
import app.repository.user.CollectRepository;
import app.repository.user.CollectRepositorySelect;
import app.repository.user.CommentPagingAndSortingRepository;
import app.repository.user.CommentRepository;
import app.repository.user.CommentRepositorySelect;
import app.repository.user.DailyRepository;
import app.repository.user.DailyRepositorySelect;
import app.repository.user.DairyTypeRepositorySelect;
import app.repository.user.Future_daily_Repository;
import app.repository.user.Future_daily_RepositorySelect;
import app.repository.user.Future_voice_Repository;
import app.repository.user.Future_voice_RepositorySelect;
import app.repository.user.GoodRepository;
import app.repository.user.GoodRepositorySelect;
import app.repository.user.RecordRepository;
import app.repository.user.RecordRepositorySelect;
import app.repository.user.TopicRepositorySelect;
import app.repository.user.UserRepository;
import app.repository.user.UserRepositorySelect;
import app.repository.user.VoiceRepository;
import app.repository.user.VoiceRepositorySelect;
import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
@Service
public class DairyServiceImpl implements DairyService{

	@Resource(name = "dairyServiceDelete")
	private DairyServiceDelete dairyServiceDelete;
	
	@Resource(name = "userRepository")
	private UserRepository userRepository;
	
	@Resource(name = "userRepositorySelect")
	private UserRepositorySelect userRepositorySelect;
	
	@Resource(name = "dailyRepository")
	private DailyRepository dailyRepository;
	
	@Resource(name = "chatRepository")
	private ChatRepository chatRepository;
	
	@Resource(name = "recordRepository")
	private RecordRepository recordRepository;
	
	@Resource(name = "voiceRepository")
	private VoiceRepository voiceRepository;
	
	@Resource(name = "commentRepository")
	private CommentRepository commentRepository;
	
	@Resource(name = "collectRepository")
	private CollectRepository collectRepository;
	
	@Resource(name = "goodRepositorySelect")
	private GoodRepositorySelect goodRepositorySelect;
	
	@Resource(name = "goodRepository")
	private GoodRepository goodRepository;
	
	@Resource(name = "voiceRepositorySelect")
	private VoiceRepositorySelect voiceRepositorySelect;
	
	@Resource(name = "dailyRepositorySelect")
	private DailyRepositorySelect dailyRepositorySelect;
	
	@Resource(name = "topicRepositorySelect")
	private TopicRepositorySelect topicRepositorySelect;
	
	@Resource(name = "recordRepositorySelect")
	private RecordRepositorySelect recordRepositorySelect;
	
	@Resource(name = "chatRepositorySelect")
	private ChatRepositorySelect chatRepositorySelect;
	
	@Resource(name = "collectRepositorySelect")
	private CollectRepositorySelect collectRepositorySelect;
	
	@Resource(name = "commentRepositorySelect")
	private CommentRepositorySelect commentRepositorySelect;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Resource(name = "dairyTypeRepositorySelect")
	private DairyTypeRepositorySelect dairyTypeRepositorySelect;
	
	@Resource(name = "future_daily_Repository")
	private Future_daily_Repository future_daily_Repository;
	
	@Resource(name = "future_daily_RepositorySelect")
	private Future_daily_RepositorySelect future_daily_RepositorySelect;
	
	@Resource(name = "future_voice_Repository")
	private Future_voice_Repository future_voice_Repository;
	
	@Resource(name = "future_voice_RepositorySelect")
	private Future_voice_RepositorySelect future_voice_RepositorySelect;
	
	@Resource(name = "commentPagingAndSortingRepository")
	private CommentPagingAndSortingRepository commentPagingAndSortingRepository;
	/**
	 * 查询topic类型
	 */
	public List<Topic> dairy_home(){
		return topicRepositorySelect.find_all();
	}
	
	/**
	 * 查询DailyType类型
	 */
	public List<DairyType> dairy_myself(){
		return dairyTypeRepositorySelect.find_all();
	}
	
	/**
	 * 检查邮箱是否已经注册
	 * @param email
	 * @return
	 */
	public JsonObject check_email(String email){
		User user = userRepositorySelect.check_email(email);
		JsonObject jsonObject = new JsonObject();
		if(user == null){
			jsonObject.setCode(1);			
			jsonObject.setMsg("email can use");
			return jsonObject;
		}
		jsonObject.setMsg("email already register");
		return jsonObject;		
	}
	
	/**
	 * 检查账号是否已经注册
	 * @param username
	 * @return
	 */
	public JsonObject check_username(String username){
		User user = userRepositorySelect.check_username(username);
		JsonObject jsonObject = new JsonObject();
		if(user == null){
			jsonObject.setCode(1);			
			jsonObject.setMsg("username can use");
			return jsonObject;
		}
		jsonObject.setMsg("username already register");
		return jsonObject;			
	}
	
	
	//////////////////////////////////////////////////////////////
	/**
	 * 注册
	 */
	public JsonObject register_now(User user){
		JsonObject jsonObject = new JsonObject();
		jsonObject.setMsg("register failure");
		if(user.getEmail() == null){
			if(user.getUsername() == null){
				return jsonObject;
			}else{
				if(this.check_username(user.getUsername()).getCode() == 1){
					user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					user.setCreate_time(new Date());
					User user_ = userRepository.save(user);
					jsonObject.setCode(1);
					jsonObject.setObject(user_);
					jsonObject.setMsg("register success");
					return jsonObject;
				}				
			}
		}else{
			if(this.check_email(user.getEmail()).getCode() == 1 && this.check_username(user.getUsername()).getCode() == 1){
				user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				user.setCreate_time(new Date());
				User user_ = userRepository.save(user);
				jsonObject.setCode(1);
				jsonObject.setObject(user_);
				jsonObject.setMsg("register success");
				return jsonObject;
			}
		}
		return jsonObject;
	}

	/**
	 * 用户使用邮箱登录时获取验证码
	 * @param email 用户的邮箱
	 * @return 
	 */
	@Override
	public JsonObject login_getcode(String email) {
		User user = userRepositorySelect.login_getcode(email);
		JsonObject jsonObject = new JsonObject();
		//判断该邮箱是否已注册,只有注册了才能登录
		if(user == null){
			jsonObject.setMsg("send email failure");
			return jsonObject;
		}else{
			//发送信息
			Connection conn = new Connection("39.108.236.62");
			InputStream ins = null;
			StringBuffer bf = new StringBuffer();
			StringBuffer alert_info = new StringBuffer();
			StringBuffer code_info = new StringBuffer();
			int b = -1;
			try {
				conn.connect();
				conn.authenticateWithPassword("root","jiezi,./15");
				Session session;
				session = conn.openSession();
				session.execCommand("/usr/dairy/sendmail/register_mail.sh " + email);
				ins = new StreamGobbler(session.getStdout());
				session.waitForCondition(ChannelCondition.EXIT_STATUS, 5000);
				while((b=ins.read())!=-1){
					bf.append((char)b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				conn.close();
			}
			//返回:success or failure + code
			for (int i = 0; i < bf.length(); i++) {
				if(bf.charAt(i) >= 'a' && bf.charAt(i) <= 'z'){
					alert_info.append(bf.charAt(i));
				}else if(bf.charAt(i) >= '0' && bf.charAt(i) <= '9'){
					code_info.append(bf.charAt(i));
				}else{
					continue;
				}
			}
			if(alert_info.toString().equals("success")){
				jsonObject.setCode(1);
				user.setCode(code_info.toString());
				userRepository.save(user);
			}
			jsonObject.setMsg("send email " + alert_info);
			return jsonObject;		
		}
	
	}

	/**
	 * 通过email进行认证
	 */
	@Override
	public User login_authenticate_email(User user) {
		User user_ = userRepositorySelect.login_authenticate_email(user.getEmail(),user.getCode());
		//修改登录时间
		if(user_ != null){
			user_.setLogin_time(new Date());
			userRepository.save(user_);
		}		
		return user_;
	}

	/**
	 * 通过username和password进行认证
	 */
	@Override
	public User login_authenticate_username(User user) {
		User user_ = userRepositorySelect.login_authenticate_username(user.getUsername(), user.getPassword());
		//修改登录时间
		if(user_ != null){
			user_.setLogin_time(new Date());
			userRepository.save(user_);
		}
		return user_;			
	}
	

	/**
	 * 获取topic的内容
	 */
	public List<Topic> get_topic(){
		List<Topic> topic_list = topicRepositorySelect.find_all();
		return topic_list;
	}
	
	/**
	 * 将md和html存入到Mysql中
	 */
	@Override
	public JsonObject daily_save(Daily daily, User user) {
		daily.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		daily.setCreateTime(new Date());
		daily.setUser_id(user.getId());
		//将内容的第一张图片存到daily的成员变量first_image <img src="/pic/3f3d2414b73345b2aa26449e332fc249.jpg">
		String html = daily.getHtml();
		if(html.contains(".jpg") || html.contains(".png") || html.contains(".jpeg")){
			int start = daily.getHtml().indexOf("/pic/",0);
			int end = daily.getHtml().indexOf("\"", daily.getHtml().indexOf("/pic/"));	
			String first_image = daily.getHtml().substring(start,end);
			daily.setFirst_image_url(first_image);
		}else{
			daily.setFirst_image_url("/pic/default_first_image_url.jpg");
		}
		//将文章的内容截取部分用于展示
		String last = sub(0,daily.getHtml(),null);
		String first_context = null;
		if(last.length() > 33){
			first_context = last.substring(0, 33).concat("...");
		}else{
			first_context = last.concat("...");
		}
		daily.setFirst_context(first_context);
		//
		Daily daily_ = dailyRepository.save(daily);
		JsonObject jsonObject = new JsonObject();
		if(daily_ == null){
			jsonObject.setMsg("save failure");
			return jsonObject;
		}
		jsonObject.setCode(1);
		jsonObject.setObject(daily_);
		jsonObject.setObject("save success");
		return jsonObject;
	}

	/**
	 * 截取文章的文字，用于展示
	 * @return
	 */
	public String sub(int index,String s,String index1){
		int eq = s.indexOf("<p>",index);
		if(eq !=-1){
			int start = eq + 2;
			int end = s.indexOf("</p>",index);
			
			String last = null;			
			if(index1 == null){
				last = s.substring(start, end).replaceAll("[^\\u4e00-\\u9fa5\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]", "");
			}else{
				last = s.substring(start, end).replaceAll("[^\\u4e00-\\u9fa5\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]", "");
				last = index1.concat(last);
			}
			
			if(last.length() != 0 && last.length() > 33 ){
				return last;
			}else{
				return sub(end+3,s,last);
			}
		}
		return index1;
		
	}
	/**
	 * 保存voice
	 */
	@Override
	public Voice voice_upload(String url, User user,Voice voice) {
		voice.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		voice.setUrl("/pic/"+ url);
		voice.setCreateTime(new Date());
		voice.setUser_id(user.getId());
		Voice voice_ = voiceRepository.save(voice);
		if(voice_ == null){
			return null;
		}
		return voice_;
	}

	/**
	 * 发送消息第一种情况
	 */
	@Override
	public void send1(List<Daily> daily_list, List<Voice> voice_list) {
		List<Object> all_list = new ArrayList<Object>();
		//先查出完整的数据
		List<String> daily_list1 = new ArrayList<String>();
		for(Daily daily : daily_list){
			daily_list1.add(daily.getId());
		}
		
		List<String> voice_list2 = new ArrayList<String>();
		for(Voice voice : voice_list){
			voice_list2.add(voice.getId());
		}
		
		List<Daily> daily_list_ = dailyRepositorySelect.find_in(daily_list1);
		List<Voice> voice_list_ = voiceRepositorySelect.find_in(voice_list2);
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < daily_list_.size() && j < voice_list_.size()){
			if(daily_list_.get(i).getCreateTime().compareTo(voice_list_.get(j).getCreateTime()) == -1){
				all_list.add(daily_list_.get(i));
				i++;
				k++;
			}else{
				all_list.add(voice_list_.get(j));
				j++;
				k++;
			}
		}
		
		while(i < daily_list_.size()){
			all_list.add(daily_list_.get(i));
			i++;
			k++;
		}
		
		while(j < voice_list_.size()){
			all_list.add(voice_list_.get(j));
			j++;
			k++;
		}
		
		for(int z = 0 ; z < all_list.size() ; z++){
			//判断属于什么类型的
			if(all_list.get(z) instanceof Daily){
				//查找要发布的所有id
				Daily daily = (Daily) all_list.get(z);
				List<String> user_id_list = null;
				//查找发过相同topic的所有用户的id,如果topic_id为0则查出全部
				if(daily.getTopic_id() == 0){
					user_id_list = userRepositorySelect.find_all();
					System.out.println("send2-1");
				}else{
					user_id_list = dailyRepositorySelect.send(daily.getTopic_id());
					System.out.println("send2-2");
				}
				//通过id查找整条Daily【由于本方法的参数Daily是一个不完整的数据】	
				System.out.println(user_id_list.toString());
				Daily daily_ = dailyRepositorySelect.find_one(daily.getId());		
				if(user_id_list.size() != 0 ){
					for(String id:user_id_list){
						simpMessagingTemplate.convertAndSend("/push/dairy" + id, daily_);
					}
				}
			}else{
				Voice voice = (Voice) all_list.get(z);
				List<String> voice_id_list = null;
				if(voice.getTopic_id() == 0){
					voice_id_list = userRepositorySelect.find_all();
				}else{
					voice_id_list = voiceRepositorySelect.send(voice.getTopic_id());
				}
				Voice voice_ = voiceRepositorySelect.find_one(voice.getId());
				if(voice_id_list.size() != 0 ){
					for(String id:voice_id_list){
						simpMessagingTemplate.convertAndSend("/push/dairy" + id, voice_);
					}
				}
			}
		}
			
	}
	
	/**
	 * 发布消息第二种情况
	 */
	@Override
	public void send2(Daily daily) {
		System.out.println("send2" + daily.getId());
		List<String> user_id_list = null;
		//查找发过相同topic的所有用户的id,如果topic_id为0则查出全部
		if(daily.getTopic_id() == 0){
			user_id_list = userRepositorySelect.find_all();
		}else{
			user_id_list = dailyRepositorySelect.send(daily.getTopic_id());
		}
		//通过id查找整条Daily【由于本方法的参数Daily是一个不完整的数据】	
		Daily daily_ = dailyRepositorySelect.find_one(daily.getId());
		if(user_id_list.size() != 0 ){
			for(String id:user_id_list){
				simpMessagingTemplate.convertAndSend("/push/dairy" + id, daily_);
			}
		}
	}
	
	/**
	 * 发送消息第三种情况
	 */
	@Override
	public void send3(Voice voice) {
		List<String> voice_id_list = null;
		if(voice.getTopic_id() == 0){
			voice_id_list = userRepositorySelect.find_all();
		}else{
			voice_id_list = voiceRepositorySelect.send(voice.getTopic_id());
		}
		Voice voice_ = voiceRepositorySelect.find_one(voice.getId());
		if(voice_id_list.size() != 0 ){
			for(String id:voice_id_list){
				simpMessagingTemplate.convertAndSend("/push/dairy" + id, voice_);
			}
		}	
	}

	/**
	 * 展示发布的所有消息
	 * 找出Record的topic为0的数据【所有人都发过的数据】 Record-topic=0
	 * 以及自身也发过除了topic为0的数据 自身发过的数据
	 * 别人推送给我的数据
	 */
	@Override
	public JsonObject show_all(String id) {
		JsonObject jsonObject = new JsonObject();
		User user = userRepository.findOne(id);
		
		List<Daily> daily_list = user.getDaily_list();
		List<Voice> voice_list = user.getVoice_list();
		
		Set<Integer> topic_id_set = new HashSet<Integer>();
		
		for(Daily daily : daily_list){
			if(daily != null && daily.getTopic_id() != 0){
				topic_id_set.add(daily.getTopic_id());
			}
		}
		
		for(Voice voice : voice_list){
			if(voice != null && voice.getTopic_id() != 0){
				topic_id_set.add(voice.getTopic_id());
			}
		}
		//保证必然能查出公开的数据
		topic_id_set.add(0);
		//查出全部公开的数据
		List<Record> record_list = recordRepositorySelect.show_all(topic_id_set);
		//查出当前用户在公开表种每一个专题的最早记录
		//Iterator<Integer> topic_min= topic_id_set.iterator();
		
		if(record_list.size() != 0){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
			jsonObject.setObject(record_list);
			return jsonObject;
		}
		
		jsonObject.setMsg("failure");
		return jsonObject;
	}

	/**
	 * 展示发布的部分消息
	 */
	@Override
	public JsonObject show_part(String id,HttpSession session) {
		session.setAttribute("all", true);
		JsonObject jsonObject = new JsonObject();
		User user = userRepository.findOne(id);
		
		List<Daily> daily_list = user.getDaily_list();
		List<Voice> voice_list = user.getVoice_list();
		
		Set<Integer> topic_id_set = new HashSet<Integer>();
		
		//查看用户发送过什么类型的数据
		
		for(Daily daily : daily_list){
			if(daily != null && daily.getTopic_id() != 0){
				topic_id_set.add(daily.getTopic_id());
			}
		}
		
		for(Voice voice : voice_list){
			if(voice != null && voice.getTopic_id() != 0){
				topic_id_set.add(voice.getTopic_id());
			}
		}
		
		topic_id_set.add(0);
		List<Record> record_list = recordRepositorySelect.show_all(topic_id_set);
		if(record_list != null){
			if(record_list.size() >= 1){
				System.out.println(record_list.toString());				
				record_list.remove(record_list.size()-1);
				jsonObject.setCode(1);
				jsonObject.setObject(record_list);
				jsonObject.setMsg("success");
				return jsonObject;
			}
		}
		return jsonObject;
		
	}

	/**
	 * 选择器Daily数据展示
	 */
	@Override
	public JsonObject show_daily(String id) {
		//先去查查个人Daily发过什么类型的数据
		JsonObject jsonObject = new JsonObject();
		User user = userRepository.findOne(id);
		List<Daily> daily_list = user.getDaily_list();
		Set<Integer> set_topic_id = new HashSet<Integer>();
		if(daily_list.size() != 0){
			for(Daily daily : daily_list){
				set_topic_id.add(daily.getTopic_id());
			}
		}
		set_topic_id.add(0);
		List<Record> record_list = recordRepositorySelect.show_daily(set_topic_id);
		if(record_list.size() != 0){
			jsonObject.setCode(1);
			jsonObject.setObject(record_list);
			jsonObject.setMsg("have value");
			return jsonObject;
		}
		return jsonObject;
	}

	/**
	 * 选择器Voice数据展示
	 */
	@Override
	public JsonObject show_voice(String id) {
		JsonObject jsonObject = new JsonObject();
		User user = userRepository.findOne(id);
		List<Voice> voice_list = user.getVoice_list();
		Set<Integer> set_topic_id = new HashSet<Integer>();
		if(voice_list.size() != 0){
			for(Voice voice : voice_list){
				set_topic_id.add(voice.getTopic_id());
			}
		}
		set_topic_id.add(0);
		List<Record> record_list = recordRepositorySelect.show_voice(set_topic_id);
		if(record_list.size() != 0){
			jsonObject.setCode(1);
			jsonObject.setObject(record_list);
			jsonObject.setMsg("have value");
			return jsonObject;
		}
		return jsonObject;
	}

	/**
	 * 选择器topic-feel数据
	 */
	@Override
	public JsonObject show_topic(int topic_id,String id) {
		JsonObject jsonObject = new JsonObject();
		
		//有topic的主键了，需要检查用户是否公开发过"feel"的daily和voice
		if(topic_id == 0){
			User user = userRepository.findOne(id);		
			List<Daily> daily_list = user.getDaily_list();
			List<Voice> voice_list = user.getVoice_list();
			Set<Integer> topic_id_set = new HashSet<Integer>();			
			//查看用户发送过什么类型的数据		
			for(Daily daily : daily_list){
				if(daily != null && daily.getTopic_id() != 0){
					topic_id_set.add(daily.getTopic_id());
				}
			}		
			for(Voice voice : voice_list){
				if(voice != null && voice.getTopic_id() != 0){
					topic_id_set.add(voice.getTopic_id());
				}
			}
			topic_id_set.add(0);	
			List<Record> record_list = recordRepositorySelect.show_all(topic_id_set);	
			if(record_list.size() != 0){
				jsonObject.setCode(1);
				jsonObject.setMsg("success");
				jsonObject.setObject(record_list);
				return jsonObject;
			}
			
		}else{
			List<Record> record_list = recordRepositorySelect.find_topic(id,topic_id);
			if(record_list.size() != 0 ){
				List<Record> record_list_ = recordRepositorySelect.show_topic(topic_id);
				System.out.println(record_list_.toString());
				if(record_list_.size() != 0){
					jsonObject.setCode(1);
					jsonObject.setObject(record_list_);
					jsonObject.setMsg("success");
					return jsonObject;
				}
			}
		}
		
		return jsonObject;
	}

	/**
	 * future_daily保存用户所编写的内容
	 */
	@Override
	public JsonObject future_daily_save(Future_daily future_daily, User user) {
		future_daily.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		future_daily.setCreateTime(new Date());
		future_daily.setUser_id(user.getId());
		//将内容的第一张图片存到daily的成员变量first_image <img src="/pic/3f3d2414b73345b2aa26449e332fc249.jpg">
		String html = future_daily.getHtml();
		if(html.contains(".jpg") || html.contains(".png") || html.contains(".jpeg")){
			int start = future_daily.getHtml().indexOf("/pic/",0);
			int end = future_daily.getHtml().indexOf("\"", future_daily.getHtml().indexOf("/pic/"));	
			String first_image = future_daily.getHtml().substring(start,end);
			future_daily.setFirst_image_url(first_image);
		}else{
			future_daily.setFirst_image_url("/pic/default_first_image_url.jpg");
		}
		
		Future_daily future_daily_ = future_daily_Repository.save(future_daily);
		JsonObject jsonObject = new JsonObject();
		if(future_daily_ == null){
			jsonObject.setMsg("future_daily save failure");
			return jsonObject;
		}
		jsonObject.setCode(1);
		jsonObject.setObject(future_daily_);
		jsonObject.setObject("save success");
		return jsonObject;
	}
	

	/**
	 * 定时器
	 * 每天的0点0时0分触发
	 * 目的:将future_daily和future_voice设置为可读
	 */
	@Override
	public void open() {
		//查询future_daily不能读的文件
		List<Future_daily> future_daily_list = future_daily_RepositorySelect.find_all();
		List<Future_voice> future_voice_list = future_voice_RepositorySelect.find_all();
		
		//记录所要发送的user的邮箱,若没有则在用户登录进来的时候做一个提醒的功能
		//记录处理了的user的主键
		Set<String> user_id_list = new HashSet<String>();
		String now_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(future_daily_list.size() != 0){
			for(Future_daily future_daily : future_daily_list){
				String future_daily_endTime = new SimpleDateFormat("yyyy-MM-dd").format(future_daily.getEndTime());
				if(now_date.equals(future_daily_endTime)){
					//设置Isopen为true
					future_daily.isOpen = true;
					future_daily_Repository.save(future_daily);
					user_id_list.add(future_daily.getUser_id());
				}
			}
		}
		
		if(future_voice_list.size() != 0){
			for(Future_voice future_voice : future_voice_list){
				String future_voice_endTime = new SimpleDateFormat("yyyy-MM-dd").format(future_voice.getEndTime());
				if(now_date.equals(future_voice_endTime)){
					future_voice.isOpen = true;
					future_voice_Repository.save(future_voice);
					user_id_list.add(future_voice.getUser_id());
				}
			}
		}
		
		/*
		 * 通过邮箱发送：
		 * 	1.查出用户可读的future_daily和future_voice
		 */
		if(user_id_list.size() != 0){
			for(String user_id : user_id_list){
				User user = userRepository.findOne(user_id);
				if(user.getEmail() != null){
					Connection conn = new Connection("39.108.236.62");
					try {
						conn.connect();
						conn.authenticateWithPassword("root","jiezi,./15");
						Session session;
						session = conn.openSession();
						session.execCommand("/usr/dairy/sendmail/remind_mail.sh " + user.getEmail());
						session.waitForCondition(ChannelCondition.EXIT_STATUS, 5000);
					} catch (IOException e) {
						e.printStackTrace();
					} finally{
						conn.close();
					}
				}
			}
		}
	}

	@Override
	public Future_voice future_voice_upload(String url, User user, Future_voice future_voice) {
		future_voice.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		future_voice.setUrl("/pic/" + url);
		future_voice.setCreateTime(new Date());
		future_voice.setUser_id(user.getId());
		Future_voice future_voice_ = future_voice_Repository.save(future_voice);
		if(future_voice_ == null){
			return null;
		}
		return future_voice_;
	}

	/**
	 * 查找User对应的Daily
	 * @return 
	 */
	@Override
	public List<Daily> findUserDaily(User user) {
		return  dailyRepositorySelect.find_all(user.getId());
	}

	/**
	 * 点击ReadMore展示用户完整的daily
	 */
	@Override
	public Daily show_daily_myself(String id) {
		return dailyRepository.findOne(id);
	}
	
	/**
	 * 查询用户对应的voice
	 */
	@Override
	public List<Voice> findUserVoice(User user) {
		return voiceRepositorySelect.find_all1(user.getId());
	}

	/**
	 * 查询用户对应的future daily
	 */
	@Override
	public List<Future_daily> findUserFutureDaily(User user) {
		return future_daily_RepositorySelect.find_all1(user.getId());
	}

	/**
	 * 点击ReadMore展示用户完整的future_daily
	 */
	@Override
	public Future_daily show_future_daily_myself(String id) {	
		return future_daily_Repository.findOne(id);
	}

	/**
	 * 查询用户对应的future voice
	 */
	@Override
	public List<Future_voice> findUserFutureVoice(User user) {
		return future_voice_RepositorySelect.find_all1(user.getId());
	}
	
	/**
	 * 查询用户对应的collect
	 */
	@Override
	public List<Collect> findUserCollect(User user) {
		return collectRepositorySelect.find_all(user.getId());
	}

	/**
	 * 显示更多
	 */
	@Override
	public List<Object> show_more(String user_id,String dairy_type) {
		
		int id = dairyTypeRepositorySelect.findBydairy_type(dairy_type);
		User user = userRepository.findOne(user_id);
		List<Object> object_list = new ArrayList<Object>();
		if(id == 0){
			List<Daily> daily_list = user.getDaily_list();
			for(Daily daily : daily_list){
				object_list.add(daily);
			}
		}else if(id == 1){
			List<Voice> voice_list = user.getVoice_list();
			for(Voice voice : voice_list){
				object_list.add(voice);
			}
		}else if(id == 2){
			List<Future_daily> future_daily_list = user.getFuture_daily_list();
			for(Future_daily future_daily : future_daily_list){
				object_list.add(future_daily);
			}
		}else{
			List<Future_voice> future_voice_list = user.getFuture_voice_list();
			for(Future_voice future_voice : future_voice_list){
				object_list.add(future_voice);
			}
		}
		
		return object_list;
	}

	/**
	 * 删除用户完整的daily
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete_daily_myself(String id) {
		Daily daily = dailyRepository.findOne(id);
		if(!daily.isPermission()){
			try {
				dairyServiceDelete.delete_daily(id);
				System.out.println("我是非公开");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
		}else{
			try {
				dairyServiceDelete.delete_daily(id);
				dairyServiceDelete.delete_record(id);
				System.out.println("我是公开");
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		}
		return true;
	}

	/**
	 * 删除用户完整的voice
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete_voice_myself(String id) {
		Voice voice = voiceRepository.findOne(id);
		if(!voice.isPermission()){
			try {
				dairyServiceDelete.delete_voice(id);
			} catch (Exception e) {
				return false;
			}
		}else{
			try {
				dairyServiceDelete.delete_voice(id);
				dairyServiceDelete.delete_record(id);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 删除用户完整的future_daily
	 * @param id
	 * @return
	 */
	public boolean delete_future_daily_myself(String id){
		Future_daily future_daily = future_daily_Repository.findOne(id);
		if(future_daily == null){
			return false;
		}else{
			future_daily_Repository.delete(id);
			return true;
		}
	}
	
	/**
	 * 删除用户完整的future_voice
	 * @param id
	 * @return
	 */
	public boolean delete_future_voice_myself(String id){
		Future_voice future_voice = future_voice_Repository.findOne(id);
		if(future_voice == null){
			return false;
		}else{
			future_voice_Repository.delete(id);
			return true;
		}
	}

	/**
	 * 收藏daily
	 */
	@Override
	public Collect collect_daily(String id,String user_id) {
		Daily daily = dailyRepository.findOne(id);
		if(daily == null){
			return null;
		}else{
			Collect collect = new Collect();
			collect.setId(UUID.randomUUID().toString().replaceAll("-", ""));;
			collect.setCollectionTime(new Date());
			collect.setDairytype_id(daily.getDairytype_id());
			collect.setUser_id(user_id);
			collect.setCollect_id(id);
			collect.setFirst_image_url(daily.getFirst_image_url());
			collect.setArticle(daily.getArticle());
			collect.setFirst_context(daily.getFirst_context());
			Collect collect1 = null;
			try{
				//成功收藏
				collect1 = collectRepository.save(collect);		
				if(collect1 != null){
					return collect1;
				}else{
					return null;
				}
			}catch(Exception e ){
				//已经收藏了，因此再次点击是取消收藏
				Collect collect2 = collectRepositorySelect.findByidAnduserid(id, user_id);
				collectRepository.delete(collect2);
				System.out.println("daily 取消收藏");
				return collect2;
			}
			
		}
	}

	/**
	 * 收藏voice
	 */
	@Override
	public Collect collect_voice(String id, String user_id) {
		Voice voice = voiceRepository.findOne(id);
		if(voice == null){
			return null;
		}else{
			Collect collect = new Collect();
			collect.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			collect.setCollectionTime(new Date());
			collect.setDairytype_id(voice.getDairytype_id());
			collect.setUser_id(user_id);
			collect.setCollect_id(id);
			collect.setArticle(voice.getArticle());
			collect.setUrl(voice.getUrl());
			Collect collect1 = null;
			try{
				//成功收藏
				collect1 = collectRepository.save(collect);		
				if(collect1 != null){
					return collect1;
				}else{
					return null;
				}
			}catch(Exception e ){
				//已经收藏了，因此再次点击是取消收藏
				Collect collect2 = collectRepositorySelect.findByidAnduserid(id, user_id);
				collectRepository.delete(collect2);
				System.out.println("daily 取消收藏");
				return collect2;
			}
		}
	}

	/**
	 * 保存评论
	 */
	@Override
	public Comment save_comment(Comment comment) {
		comment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return commentRepository.save(comment);
	}

	/**
	 * 展示文章的评论
	 */
	@Override
	public List<Comment> show_comment(String dairy_id) {
		return commentRepositorySelect.show_comment(dairy_id);
	}

	/**
	 * 增加点赞数
	 */
	@Override
	public int add_gooCount(String dairy_id,String user_id) {
		//先查出有没有相同的人点赞了同一个日记
		Good good = goodRepositorySelect.findBydairy_idAnduser_id(dairy_id, user_id);
		if(good == null){
			//增加点赞的数据
			Good good1 = new Good();
			good1.setDairy_id(dairy_id);
			good1.setUser_id(user_id);
			good1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			Good good_save = goodRepository.save(good1);			
		}else{
			//取消点赞
			goodRepository.delete(good);
		}
		//统计当前点赞数
		int count = goodRepositorySelect.count(dairy_id);
		//修改record表
		Record record = recordRepository.findOne(dairy_id);
		if(record != null){
			record.setGood_count(count);
			Record record1 = recordRepository.save(record);
			if(record1 != null){
				return count;
			}
		}
		return -1;
	}

}
