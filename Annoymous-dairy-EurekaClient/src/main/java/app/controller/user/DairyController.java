package app.controller.user;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app.config.DailyJsonObject;
import app.config.JsonObject;
import app.javabean.Announcement;
import app.javabean.Chat;
import app.javabean.Collect;
import app.javabean.Comment;
import app.javabean.Daily;
import app.javabean.DairyType;
import app.javabean.Future_daily;
import app.javabean.Future_voice;
import app.javabean.Topic;
import app.javabean.User;
import app.javabean.Voice;
import app.javabean.Warn;
import app.service.user.DairyService;

@Controller
@RequestMapping("/dairy")
public class DairyController {

	@Resource(name = "dairyServiceImpl")
	private DairyService dairyService;
		
	private List<Daily> daily_list = new ArrayList<Daily>();
	
	private List<Voice> voice_list = new ArrayList<Voice>();
	
	/**
	 * 登录界面
	 */
	@RequestMapping("/login")
	public String login(){
		return "dairy/html/login";
	}

	
	/**
	 * 注册界面
	 */
	@RequestMapping("/register")
	public String register(){
		return "dairy/html/register";
	}
	
	/**
	 * 总界面
	 */
	@RequestMapping("/index")
	public String index(){
		return "dairy/html/index";
	}
	
	/**
	 * 日记home主页
	 */
	@RequestMapping("/dairy_home")
	public String dairy_home(HttpSession session){
		//session存放用于判断是否发布消息
		if(session.getAttribute("send") == null){
			session.setAttribute("send", "");
		}
		//关联send_dairy_show + daily_save
		if(session.getAttribute("all") == null){
			session.setAttribute("all", true);
		}
		//查询topic类型:
		List<Topic> topic_list = dairyService.dairy_home();
		session.setAttribute("topic_list", topic_list);
		//查询所有的公告
		List<Announcement> announcement_list = dairyService.show_announcement();
		session.setAttribute("announcement_list", announcement_list);
		return "dairy/html/dairy_home";
	}
	
	/**
	 * 日记Myself主页
	 */
	@RequestMapping("/dairy_myself")
	public String dairy_myself(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		//查询用户对应的daily、voice,只展示最新的4个
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查询用户对应的daily
			List<Daily> daily_list = dairyService.findUserDaily(user);
			session.setAttribute("daily_list", daily_list);
			//查询用户对应的voice
			List<Voice> voice_list = dairyService.findUserVoice(user);
			session.setAttribute("voice_list", voice_list);
			//查询用户对应future daily
			List<Future_daily> future_daily_list = dairyService.findUserFutureDaily(user);
			session.setAttribute("future_daily_list", future_daily_list);
			//查询用户对应future voice
			List<Future_voice> future_voice_list = dairyService.findUserFutureVoice(user);
			session.setAttribute("future_voice_list", future_voice_list);
			//查询用户对应collect
			List<Collect> collect_list = dairyService.findUserCollect(user);
			session.setAttribute("collect_list", collect_list);
			//用户
			session.setAttribute("user", user);
			return "dairy/html/dairy_myself";
		}
		
		return "dairy/html/dairy_myself";
	}
	
	/**
	 * 日记Myself_voice主页
	 */
	@RequestMapping("/dairy_myself_voice")
	public String dairy_myself_voice(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		//查询用户对应的daily、voice,只展示最新的4个
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查询用户对应的daily
			List<Daily> daily_list = dairyService.findUserDaily(user);
			session.setAttribute("daily_list", daily_list);
			//查询用户对应的voice
			List<Voice> voice_list = dairyService.findUserVoice(user);
			session.setAttribute("voice_list", voice_list);
			//查询用户对应future daily
			List<Future_daily> future_daily_list = dairyService.findUserFutureDaily(user);
			session.setAttribute("future_daily_list", future_daily_list);
			//查询用户对应future voice
			List<Future_voice> future_voice_list = dairyService.findUserFutureVoice(user);
			session.setAttribute("future_voice_list", future_voice_list);
			//查询用户对应collect
			List<Collect> collect_list = dairyService.findUserCollect(user);
			session.setAttribute("collect_list", collect_list);
			//用户
			session.setAttribute("user", user);
			return "dairy/html/dairy_myself_voice";
		}
		
		return "dairy/html/dairy_myself";
	}
	
	/**
	 * 日记Myself_future_daily主页
	 */
	@RequestMapping("/dairy_myself_future_daily")
	public String dairy_myself_future_daily(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		//查询用户对应的daily、voice,只展示最新的4个
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查询用户对应的daily
			List<Daily> daily_list = dairyService.findUserDaily(user);
			session.setAttribute("daily_list", daily_list);
			//查询用户对应的voice
			List<Voice> voice_list = dairyService.findUserVoice(user);
			session.setAttribute("voice_list", voice_list);
			//查询用户对应future daily
			List<Future_daily> future_daily_list = dairyService.findUserFutureDaily(user);
			session.setAttribute("future_daily_list", future_daily_list);
			//查询用户对应future voice
			List<Future_voice> future_voice_list = dairyService.findUserFutureVoice(user);
			session.setAttribute("future_voice_list", future_voice_list);
			//查询用户对应collect
			List<Collect> collect_list = dairyService.findUserCollect(user);
			session.setAttribute("collect_list", collect_list);
			//用户
			session.setAttribute("user", user);
			return "dairy/html/dairy_myself_future_daily";
		}
		
		return "dairy/html/dairy_myself";
	}
	
	/**
	 * 日记Myself_future_daily主页
	 */
	@RequestMapping("/dairy_myself_future_voice")
	public String dairy_myself_future_voice(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		//查询用户对应的daily、voice,只展示最新的4个
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查询用户对应的daily
			List<Daily> daily_list = dairyService.findUserDaily(user);
			session.setAttribute("daily_list", daily_list);
			//查询用户对应的voice
			List<Voice> voice_list = dairyService.findUserVoice(user);
			session.setAttribute("voice_list", voice_list);
			//查询用户对应future daily
			List<Future_daily> future_daily_list = dairyService.findUserFutureDaily(user);
			session.setAttribute("future_daily_list", future_daily_list);
			//查询用户对应future voice
			List<Future_voice> future_voice_list = dairyService.findUserFutureVoice(user);
			session.setAttribute("future_voice_list", future_voice_list);
			//查询用户对应collect
			List<Collect> collect_list = dairyService.findUserCollect(user);
			session.setAttribute("collect_list", collect_list);
			//用户
			session.setAttribute("user", user);
			return "dairy/html/dairy_myself_future_voice";
		}
		
		return "dairy/html/dairy_myself";
	}
	
	/**
	 * 日记Myself_collect主页
	 */
	@RequestMapping("/dairy_myself_collect")
	public String dairy_myself_collect(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		//查询用户对应的daily、voice,只展示最新的4个
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查询用户对应的daily
			List<Daily> daily_list = dairyService.findUserDaily(user);
			session.setAttribute("daily_list", daily_list);
			//查询用户对应的voice
			List<Voice> voice_list = dairyService.findUserVoice(user);
			session.setAttribute("voice_list", voice_list);
			//查询用户对应future daily
			List<Future_daily> future_daily_list = dairyService.findUserFutureDaily(user);
			session.setAttribute("future_daily_list", future_daily_list);
			//查询用户对应future voice
			List<Future_voice> future_voice_list = dairyService.findUserFutureVoice(user);
			session.setAttribute("future_voice_list", future_voice_list);
			//查询用户对应collect
			List<Collect> collect_list = dairyService.findUserCollect(user);
			session.setAttribute("collect_list", collect_list);
			//用户
			session.setAttribute("user", user);
			return "dairy/html/dairy_myself_collect";
		}
		
		return "dairy/html/dairy_myself";
	}
	
	/**
	 * 点击注册,进行加载的页面
	 */
	@RequestMapping("/register_success")
	public String register_success(){		
		return "dairy/html/register_success";
	}
	///////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 检查邮箱是否已经注册
	 * @param email
	 * @return
	 */
	@RequestMapping("check_email")
	@ResponseBody
	public JsonObject check_email(@RequestParam("email") String email){
		return dairyService.check_email(email);
	}
	
	/**
	 * 检查用户名是否已经注册
	 * @param email
	 * @return
	 */
	@RequestMapping("check_username")
	@ResponseBody
	public JsonObject check_username(@RequestParam("username") String username){		
		return dairyService.check_username(username);
	}
	
	/**
	 * 点击注册按钮进行注册
	 */
	@RequestMapping("/register_now")
	@ResponseBody
	public JsonObject register_now(User user,HttpSession session){
		
		JsonObject jsonObject = new JsonObject();
		if(user == null){
			jsonObject = new JsonObject();
			jsonObject.setMsg("register failure");
			return jsonObject;
		}	
		JsonObject jsonObject_ = dairyService.register_now(user);
		session.setAttribute("register_msg", jsonObject_);
		return jsonObject_;	
	}
	
	/**
	 * 用户使用邮箱登录时获取验证码
	 * @return
	 */
	@RequestMapping("/login_getcode")
	@ResponseBody
	public JsonObject login_getcode(@RequestParam("email") String email){
		JsonObject jsonObject = dairyService.login_getcode(email);
		return jsonObject;
	}
	
	/**
	 * 登录认证
	 * @param user
	 * @return
	 */
	@RequestMapping("/login_authenticate")
	@ResponseBody
	public JsonObject login_authenticate(User user,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		//若email不为空则用认证，否则用username进行认证
		if(user.getEmail() != null){
			User user_ = dairyService.login_authenticate_email(user);
			if(user_ == null){
				jsonObject.setMsg("authenticate failure");
				return jsonObject;
			}
			jsonObject.setCode(1);
			jsonObject.setObject(user_);
			jsonObject.setMsg("authenticate success");
			session.setAttribute("user", user_);
			return jsonObject;			
		}else{
			User user_ = dairyService.login_authenticate_username(user);
			if(user_ == null){
				jsonObject.setMsg("authenticate failure");
				return jsonObject;
			}
			jsonObject.setCode(1);
			jsonObject.setObject(user_);
			jsonObject.setMsg("authenticate success");
			session.setAttribute("user", user_);
			return jsonObject;
		}
	}
	
	
	/////////////////////////////////////////////////////////
	/**
	 * Daily
	 */
	
	/**
	 * 跳转编辑页面 + 获取日记的Topic
	 * @return
	 */
	@RequestMapping("/daily_index")
	public String daily_index(HttpSession session){
		//获取日记的topic，供用户选择
		List<Topic> topic_list = dairyService.get_topic();
		session.setAttribute("topic_list", topic_list);
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		return "dairy/editor/examples/index";
	}
	
	/**
	 * 上传图片内容
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/daily_uploadfile")
	public DailyJsonObject daily_uploadfile(@RequestParam(value = "editormd-image-file")MultipartFile file){
		 DailyJsonObject info = new DailyJsonObject();
		 String filename = file.getOriginalFilename();
		 String newFileName = (UUID.randomUUID()+filename.substring(filename.lastIndexOf("."))).replace("-", "");
		try {
			file.transferTo(new File("F:\\image\\" + newFileName));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			info.setSuccess(0);
			info.setMessage("upload failure");
			info.setUrl(null);
			return info;
		}
		info.setSuccess(1);
		info.setMessage("upload success");
		info.setUrl("/pic/" + newFileName);
		return info;
	}
	
	/**
	 * 保存用户编写的日记内容
	 * @param editor
	 */
	@RequestMapping("/daily_save")
	@ResponseBody
	public JsonObject daily_save(Daily daily,HttpSession session){	
		User user = (User) session.getAttribute("user");
		if(user == null){
			JsonObject jsonObject_ = new JsonObject();
			jsonObject_.setMsg("save failure");
		}
		JsonObject jsonObject = dairyService.daily_save(daily,user);
		session.setAttribute("send", "ok");
		session.setAttribute("all", false);
		return jsonObject;
	}
		
	/**
	 * 点击ReadMore展示用户完整的daily
	 */
	@RequestMapping("/show_daily_myself")
	public String show_daily_myself(String id,HttpSession session){
		
		Daily daily = dairyService.show_daily_myself(id);
		session.setAttribute("md", daily.getMd());
		return "/dairy/editor/examples/show";
	}
	
	/**
	 * 删除用户完整的daily
	 */
	@RequestMapping("/delete_daily_myself")
	public String delete_daily_myself(String id,HttpSession session){
		
		boolean flag  = dairyService.delete_daily_myself(id);
		if(flag){
			return "redirect:/dairy/dairy_myself";
		}else{
			return "redirect:/dairy/four";
		}
	}
	/////////////////////////////////////////////////////////
	/**
	 * Voice + Topic
	 */
	
	/**
	 * 录音页面
	 * @return
	 */
	@RequestMapping("/voice_index")
	public String voice_index(HttpSession session){
		//获取topic
		List<Topic> topic_list = dairyService.get_topic();
		session.setAttribute("topic_list", topic_list);
		//获取DairyType
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		return "dairy/voice/examples/index";
	}
	
	/**
	 * 上传录音
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/voice_upload")
	@ResponseBody
	public JsonObject voice_upload(MultipartFile file,Voice voice,HttpSession session){		
		JsonObject jsonObject = new JsonObject();	
		if(file == null){
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
		
		User user = (User) session.getAttribute("user");
		if(user == null){
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
		
		/**
		 * 发送的是audio.wav文件，请注意这是一个ajax请求
		 */
		
		String voice_name = file.getOriginalFilename();
		String new_voice_name = voice_name.substring(0, voice_name.lastIndexOf(".")) + new SimpleDateFormat("yyyyMMdHHmmss").format(new Date()) + ".wav";
		try {
			file.transferTo(new File("F:\\image\\" + new_voice_name));
			Voice voice_ = dairyService.voice_upload(new_voice_name, user,voice);
			if(voice_ == null){
				return jsonObject;
			}
			jsonObject.setCode(1);
			jsonObject.setObject(voice_);
			jsonObject.setMsg("upload success");			
			return jsonObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
	}
	
	
	/**
	 * 删除用户完整的voice
	 */
	@RequestMapping("/delete_voice_myself")
	public String delete_voice_myself(String id,HttpSession session){
		
		boolean flag  = dairyService.delete_voice_myself(id);
		if(flag){
			return "redirect:/dairy/dairy_myself";
		}else{
			return "redirect:/dairy/four";
		}
	}
	
	/////////////////////////////////////////////////////////////////
	/**
	 * linux触发处理
	 */
	@RequestMapping("/accept")
	@ResponseBody
	public void accept(Daily daily){
		daily_list.add(daily);
	}
	
	/**
	 * linux触发处理
	 */
	@RequestMapping("/accept1")
	@ResponseBody
	public void accept1(Voice voice){
		voice_list.add(voice);
	}
	
	/**
	 * 消息推送
	 */		
	@MessageMapping("/send")
	public void send(){
		
		if(daily_list.size() != 0 && voice_list.size() != 0){
			dairyService.send1(daily_list,voice_list);
			
		}else if(daily_list.size() != 0 && voice_list.size() == 0){
			for(int i = 0; i < daily_list.size(); i++) {
				//公开的daily
				dairyService.send2(daily_list.get(i));
			}
			
		}else if(daily_list.size() == 0 && voice_list.size() != 0){
			for(int i = 0; i < daily_list.size(); i++) {
				dairyService.send3(voice_list.get(i));
			}
		}else{
			
		}
		daily_list.clear();
		voice_list.clear();
	}
	
	/**
	 * 页面进入到"发布主页面"的时候触发,目的是进来先展示以前其他用户或者自己所发过的daily
	 * @param model
	 * @return
	 */
	@RequestMapping("/show_all_part")
	@ResponseBody
	public JsonObject show_all_part(HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = null;
		boolean all;
		if(session.getAttribute("user") != null && session.getAttribute("all") != null){
			user = (User) session.getAttribute("user");
			all = (Boolean) session.getAttribute("all");
			if(all == true){
				JsonObject jsonObject_ = dairyService.show_all(user.getId());
				return jsonObject_;
			}else{
				System.out.println("false");
				JsonObject jsonObject_1 = dairyService.show_part(user.getId(),session);
				return jsonObject_1;
			}
		}
		jsonObject.setMsg("failure");
		return jsonObject;
		
	}
	
	//////////////////////////////////////////////
	/**
	 * 选择器Daily数据展示
	 */
	@RequestMapping("/show_daily")
	@ResponseBody
	public JsonObject show_daily(HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			JsonObject jsonObject_ = dairyService.show_daily(user.getId());
			return jsonObject_;
		}else{
			jsonObject.setMsg("show_daily failure");
			return jsonObject;
		}		
	}
	
	/**
	 * 选择器Voice数据展示
	 */
	@RequestMapping("/show_voice")
	@ResponseBody
	public JsonObject show_voice(HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			JsonObject jsonObject_ = dairyService.show_voice(user.getId());
			return jsonObject_;
		}else{
			jsonObject.setMsg("show_voice failure");
			return jsonObject;
		}		
	}
	
	/**
	 * 选择器Topic数据展示
	 */
	@RequestMapping("/show_topic")
	@ResponseBody
	public JsonObject show_topic(int topic_id,HttpSession session){
		
		JsonObject jsonObject = new JsonObject();
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			JsonObject jsonObject_ = dairyService.show_topic(topic_id,user.getId());
			return jsonObject_;
		}else{
			jsonObject.setMsg("show_feel failure");
			return jsonObject;
		}
	}
	
	///////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////
	/**
	 * future_dairy
	 */
	
	/**
	 * future_daily页面
	 * @return
	 */
	@RequestMapping("/future_daily_index")
	public String future_daily_index(HttpSession session){
		//获取DairyType
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		return "dairy/editor/examples/future_index";
	}
	
	/**
	 * future_daily保存用户所编写的内容
	 */
	@RequestMapping("/future_daily_save")
	@ResponseBody
	public JsonObject future_daily_save(Future_daily future_daily,String end_time,HttpSession session){
		User user = (User) session.getAttribute("user");
		JsonObject jsonObject_ = new JsonObject();
		if(end_time.equals("") || end_time == null){
			jsonObject_.setMsg("future_daily save failure");
			return jsonObject_;
		}
		if(user == null){
			jsonObject_.setMsg("future_daily save failure");
			return jsonObject_;
		}
		//String转换为Date类型
		end_time = end_time + " 00:00:00";
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endtime_;
		try {
			endtime_ = simpleDate.parse(end_time);
			future_daily.setEndTime(endtime_);
			JsonObject jsonObject = dairyService.future_daily_save(future_daily,user);
			return jsonObject;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject_;
		
	}
	/**
	 * 点击ReadMore展示用户完整的future_daily
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/show_future_daily_myself")
	public String show_future_daily_myself(String id,HttpSession session){
		Future_daily future_daily = dairyService.show_future_daily_myself(id);
		session.setAttribute("md", future_daily.getMd());
		return "dairy/editor/examples/show";
	}
	
	/**
	 * 删除用户完整的future_daily
	 */
	@RequestMapping("/delete_future_daily_myself")
	public String delete_future_daily_myself(String id,HttpSession session){
		boolean flag  = dairyService.delete_future_daily_myself(id);
		if(flag){
			return "redirect:/dairy/dairy_myself";
		}else{
			return "redirect:/dairy/four";
		}
	}
	/////////////////////////////////////////////
	/**
	 * future_voice页面
	 */
	@RequestMapping("/future_voice_index")
	public String future_voice_index(HttpSession session){
		//获取DairyType
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		return "dairy/voice/examples/future_index";
	}
	
	/**
	 * 保存future_voice的录音内容
	 */
	/**
	 * 上传录音
	 * @param file
	 * @throws ParseException 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/future_voice_upload")
	@ResponseBody
	public JsonObject future_voice_upload(MultipartFile file,Future_voice future_voice,String end_time,HttpSession session) throws ParseException{	
		
		JsonObject jsonObject = new JsonObject();
		
		if(end_time.equals("") || end_time == null){
			jsonObject.setMsg("future_voice save failure");
			return jsonObject;
		}
		if(file == null){
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
		
		User user = (User) session.getAttribute("user");
		if(user == null){
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
		//处理时间
		//String转换为Date类型
		end_time = end_time + " 00:00:00";
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endtime_ = simpleDate.parse(end_time);
		future_voice.setEndTime(endtime_);
		
		String future_voice_name = file.getOriginalFilename();
		String new_future_voice_name = future_voice_name.substring(0, future_voice_name.lastIndexOf(".")) + new SimpleDateFormat("yyyyMMdHHmmss").format(new Date()) + ".wav";
		try {
			file.transferTo(new File("F:\\image\\" + new_future_voice_name));
			Future_voice Future_voice_ = dairyService.future_voice_upload(new_future_voice_name, user,future_voice);
			if(Future_voice_ == null){
				return jsonObject;
			}
			jsonObject.setCode(1);
			jsonObject.setObject(Future_voice_);
			jsonObject.setMsg("upload success");			
			return jsonObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonObject.setMsg("upload failure");
			return jsonObject;
		}
		
	}
	
	/**
	 * 删除用户完整的future_voice
	 */
	@RequestMapping("/delete_future_voice_myself")
	public String delete_future_voice_myself(String id,HttpSession session){
		
		boolean flag  = dairyService.delete_future_voice_myself(id);
		if(flag){
			return "redirect:/dairy/dairy_myself";
		}else{
			return "redirect:/dairy/four";
		}
	}
	
	/**
	 * 定时器：
	 * 每天的0点0时0分触发
	 * 秒 分钟 时 日 月 星期 [年]
	 * 0 0 0 * * * 
	 */	
	@Scheduled(cron="0 0 0 * * *")
	public void open(){
		dairyService.open();
	}
	
	@RequestMapping("/testopen")
	@ResponseBody
	public void testopen(){
		dairyService.open();
	}
	
	///////////////////////////////////////////////////////////
	
	/**
	 * chat
	 */
	
	/**
	 * 显示更多
	 * type:
	 * 	0:daily dairy
	 * 	1:voice dairy
	 * 	2:future_daily dairy
	 * 	3:future_vouce dairy
	 */
	@RequestMapping("/show_more")
	public String show_more(HttpSession session,String dairy_type){
		//初始化Session
		session.setAttribute("daily_list_showMore", null);
		session.setAttribute("voice_list_showMore", null);
		session.setAttribute("future_daily_list_showMore", null);
		session.setAttribute("future_voice_list_showMore", null);
		
		User user = (User) session.getAttribute("user");
		if(user == null){
			JsonObject jsonObject_ = new JsonObject();
			jsonObject_.setMsg("save failure");
			return "dairy/error/404";
		}
		List<Object> object_list =  dairyService.show_more(user.getId(),dairy_type);
		if(object_list.size() != 0){
			if(object_list.get(0) instanceof Daily){
				List<Daily> daily_list = new ArrayList<Daily>();
				for(Object object : object_list){
					daily_list.add((Daily) object);
				}
				session.setAttribute("daily_list_showMore", daily_list);
				
			}else if(object_list.get(0) instanceof Voice){
				List<Voice> voice_list = new ArrayList<Voice>();
				for(Object object : object_list){
					voice_list.add((Voice) object);
				}
				session.setAttribute("voice_list_showMore", voice_list);
				
			}else if(object_list.get(0) instanceof Future_daily){
				List<Future_daily> future_daily_list = new ArrayList<Future_daily>();
				for(Object object : object_list){
					future_daily_list.add((Future_daily) object);
				}
				session.setAttribute("future_daily_list_showMore", future_daily_list);
				
			}else{
				List<Future_voice> future_voice_list = new ArrayList<Future_voice>();
				for(Object object : object_list){
					future_voice_list.add((Future_voice) object);
				}
				session.setAttribute("future_voice_list_showMore", future_voice_list);
			}
		}
		return "dairy/html/showMore_daily";
	}
	
	/**
	 * 收藏daily
	 */
	@RequestMapping("/collect_daily")
	@ResponseBody
	public JsonObject collect_daily(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user == null){
			
			jsonObject.setMsg("save failure");
		}
		Collect collect = dairyService.collect_daily(id,user.getId());
		if(collect == null){
			jsonObject.setMsg("save failure");
		}else{
			jsonObject.setCode(1);
			jsonObject.setMsg("save success");
			jsonObject.setObject(collect);
		}
		return jsonObject;
	}
	
	
	/**
	 * 收藏daily
	 */
	@RequestMapping("/collect_voice")
	@ResponseBody
	public JsonObject collect_voice(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user == null){
			
			jsonObject.setMsg("save failure");
		}
		Collect collect = dairyService.collect_voice(id,user.getId());
		if(collect == null){
			jsonObject.setMsg("save failure");
		}else{
			jsonObject.setCode(1);
			jsonObject.setMsg("save success");
			jsonObject.setObject(collect);
		}
		return jsonObject;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	/**
	 * 评论
	 */
	
	/**
	 * 保存评论
	 * @return
	 */
	@RequestMapping("/save_comment")
	@ResponseBody
	public JsonObject save_comment(String dairy_id,String comment_context,int dairy_type,String commentTime,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user == null){		
			jsonObject.setMsg("save failure");
		}
		Comment comment = new Comment();
		comment.setDairy_id(dairy_id);
		comment.setComment_context(comment_context);
		comment.setDairy_type(String.valueOf(dairy_type));
		try {
			comment.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(commentTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return jsonObject;
		}
		Comment comment1 = dairyService.save_comment(comment);
		if(comment1 != null){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
			jsonObject.setObject(comment1);
		}
		return jsonObject;
	}
	
	/**
	 * 展示文章的评论
	 */
	@RequestMapping("/show_comment")
	@ResponseBody
	public JsonObject show_comment(String dairy_id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user == null){		
			jsonObject.setMsg("save failure");
		}
		List<Comment> comment_list = dairyService.show_comment(dairy_id);
		if(comment_list != null){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
			jsonObject.setObject(comment_list);
		}
		return jsonObject;
	}
	
	/**
	 * 增加点赞数
	 */
	@RequestMapping("/add_gooCount")
	@ResponseBody
	public JsonObject add_gooCount(String dairy_id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user == null){		
			jsonObject.setMsg("save failure");
		}
		int count = dairyService.add_gooCount(dairy_id,user.getId());
		jsonObject.setObject(count);
		return jsonObject;
	}
	
	/**
	 * 公告发布
	 */
	/*
	@MessageMapping("/sendAnnouncement")
	public void sendAnnouncement(){
		dairyService.sendAnnouncement();
	}
	*/
	
	/**
	 * 阅读公告
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/announcement_read")
	public String announcement_read(String id,HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null){
			Announcement announcement = dairyService.announcement_read(id);
			if(announcement != null){
				session.setAttribute("md", announcement.getMd());
			}
		}
		return "dairy/editor/examples/announcement-show";
	}
	
	////////////////////////////////////////////////////////////////////
	/**
	 * 404页面
	 */
	@RequestMapping("/four")
	public String four(){
		return "dairy/error/404";
	}
	
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * 跳转到设置页面
	 * @return
	 */
	@RequestMapping("/dairy_set")
	public String dairy_set(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null){
			session.setAttribute("user", user);
			return "dairy/html/dairy_set";
		}
		return "dairy/error/404";
	}
	
	/**
	 * 用户修改个人信息
	 * @param user1
	 * @param session
	 * @return
	 */
	@RequestMapping("/user_update_info")
	@ResponseBody
	public JsonObject user_update_info(User user1,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("user");
		if(user != null){
			User user2 = dairyService.user_update_info(user1);
			if(user2 != null){
				jsonObject.setCode(1);
				jsonObject.setMsg("update info success");
			}
		}
		return jsonObject;
	}
	
	
	///////////////////////
	
	/**
	 * 非邮件提醒功能页面
	 * @return
	 */
	@RequestMapping("/dairy_remind")
	public String dairy_remind(HttpSession session){
		//查出dairyType类型
		List<DairyType> dairyType_list = dairyService.dairy_myself();
		session.setAttribute("dairyType_list", dairyType_list);
		User user = null;
		if(session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			//查出用户对应的警告信息
			List<Warn> warn_list = dairyService.dairy_remind(user);
			session.setAttribute("warn_list", warn_list);
			session.setAttribute("user", user);
			return "dairy/html/dairy_remind";
		}else{
			return "redirect:/dairy/error/404";
		}
	}
	
	/**
	 * 非邮件提醒将未读变为可读
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/can_read")
	@ResponseBody
	public JsonObject can_read(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		jsonObject.setMsg("failure");
		User user = (User) session.getAttribute("user");
		if(user != null){
			boolean flag = dairyService.can_read(id);
			if(flag){
				jsonObject.setCode(1);
				jsonObject.setMsg("success");
			}
		}
		return jsonObject;
	}
	
	/**
	 * 查看是否存在有未读的非邮件，若有图标则显示【1】，否则不显示【0】
	 * @param id
	 * @return
	 */
	@RequestMapping("/isremind")
	@ResponseBody
	public JsonObject isremind(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		jsonObject.setMsg("failure");
		User user = (User) session.getAttribute("user");
		if(user != null){
			boolean flag = dairyService.isremind(id);
			if(flag){
				jsonObject.setCode(1);
			}
		}
		return jsonObject;
	}
	
	////////////////////
	/**
	 * 视频首页
	 */
	@RequestMapping("/video")
	public String video(HttpSession session){
		return "dairy/video/html/all-index";
	}
	
	//////////////////
	/**
	 * 音乐首页
	 * @return
	 */
	@RequestMapping("/music")
	public String music(){
		return "dairy/music/index";
	}
	
	/**
	 * 精选
	 * @return
	 */
	@RequestMapping("/music_handpick")
	public String music_handpick(){
		return "dairy/music/music_handpick";
	}
	
	/**
	 * 语言
	 * @return
	 */
	@RequestMapping("/music_language")
	public String music_language(){
		return "dairy/music/music_language";
	}
	
	/**
	 * 排行
	 * @return
	 */
	@RequestMapping("/music_ranking")
	public String music_ranking(){
		return "dairy/music/music_ranking";
	}
	
	/**
	 * 更多歌曲
	 * @return
	 */
	@RequestMapping("/music_ranking_detail")
	public String music_ranking_detail(){
		return "dairy/music/music_ranking_detail";
	}
}
