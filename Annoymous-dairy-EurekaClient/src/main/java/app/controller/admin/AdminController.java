package app.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app.config.DailyJsonObject;
import app.config.JsonObject;
import app.javabean.Announcement;
import app.javabean.Daily;
import app.javabean.Record;
import app.javabean.User;
import app.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	/**
	 * 管理员注册界面
	 */
	@RequestMapping("/register")
	public String register(){
		return "manager/register";
	}
	
	/**
	 * 管理员注册账号
	 */
	@RequestMapping("/admin_register_now")
	@ResponseBody
	public JsonObject admin_register_now(String username,String password){
		boolean flag = adminService.admin_register_now(username,password);
		JsonObject jsonObject = new JsonObject();
		jsonObject.setMsg("failure");
		if(flag){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
		}
		return jsonObject;
	}
	
	/**
	 * 管理员登录界面
	 */
	@RequestMapping("/login")
	public String login(){
		return "admin/admin_login";
	}
	
	/**
	 * 管理员登录的账户与密码确认
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login_eq")
	public JsonObject login_eq(String username,String password,HttpSession session){
		JsonObject jsonObjet = new JsonObject();
		User user = adminService.login_eq(username,password);
		if(user != null){
			session.setAttribute("admin", user);
			jsonObjet.setCode(1);
			jsonObjet.setMsg("success");
			jsonObjet.setObject(user);
		}
		return jsonObjet;
	}
	
	/**
	 * 管理员index界面
	 */
	@RequestMapping("/index")
	public String index(HttpSession session){
		User user = (User) session.getAttribute("admin");
		if(user != null){
			//查看"当日暂时统计的数据"
			Map<String,Integer> map = adminService.count_data();
			session.setAttribute("count_data", map);
			return "admin/index";
		}else{
			return "admin/admin_login";
		}
	}
	
	/**
	 * 展示公开的Daily的数据
	 */
	@RequestMapping("/daily_list")
	public String daily_list(HttpSession session){
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			Map<String,Object> map = adminService.daily_list();
			session.setAttribute("admin_daily_list", map);
		}
		return "admin/daily-list";
	}
	
	/**
	 * 管理员删除公开的Daily数据
	 */
	@RequestMapping("/delete_daily_list")
	@ResponseBody
	public JsonObject delete_daily_list(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			boolean flag = adminService.delete_daily_list(id);
			if(flag){
				jsonObject.setCode(1);			
			}
		}
		return jsonObject;
	}
	
	/**
	 * 管理员向daily发出警告信息
	 */
	@ResponseBody
	@RequestMapping("/warn_daily_list")
	public JsonObject warn_daily_list(String id,String user_id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		boolean flag = adminService.warn_daily_list(id,user_id);
		if(flag){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
		}
		return jsonObject;
	}
	
	/**
	 * 展示公开的Voice的数据
	 * @param session
	 * @return
	 */
	@RequestMapping("/voice_list")
	public String voice_list(HttpSession session){
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			Map<String,Object> map = adminService.voice_list();
			session.setAttribute("admin_voice_list", map);
		}
		return "admin/voice-list";
	}
	
	/**
	 * 管理员删除公开的Voice数据
	 */
	@RequestMapping("/delete_voice_list")
	@ResponseBody
	public JsonObject delete_voice_list(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		//查询所有的voice
		if(user != null){
			boolean flag = adminService.delete_voice_list(id);
			if(flag){
				jsonObject.setCode(1);			
			}
		}
		return jsonObject;
	}
	
	/**
	 * 管理员向voice发出警告信息
	 */
	@ResponseBody
	@RequestMapping("/warn_voice_list")
	public JsonObject warn_voice_list(String id,String user_id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		boolean flag = adminService.warn_voice_list(id,user_id);
		if(flag){
			jsonObject.setCode(1);
			jsonObject.setMsg("success");
		}
		return jsonObject;
	}
	
	/**
	 * 展示用户的数据
	 */
	@RequestMapping("/user_list")
	public String user_list(HttpSession session){
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			List<User> user_list = adminService.user_list();
			session.setAttribute("user_list", user_list);
		}
		return "admin/user-list";
	}
	
	/**
	 * 管理员删除user数据
	 */
	@RequestMapping("/delete_user_list")
	@ResponseBody
	public JsonObject delete_user_list(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			boolean flag = adminService.delete_user_list(id);
			if(flag){
				jsonObject.setCode(1);			
			}
		}
		return jsonObject;
	}
	
	/**
	 * 评论管理
	 * @param session
	 * @return
	 */
	@RequestMapping("/comment_list")
	public String comment_list(HttpSession session){
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			Map<String,Object> map = adminService.comment_list();
			session.setAttribute("admin_comment_list", map);
		}
		return "admin/comment-list";
	}
	
	/**
	 * 删除评论
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete_comment_list")
	@ResponseBody
	public JsonObject delete_comment_list(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			boolean flag = adminService.delete_comment_list(id);
			if(flag){
				jsonObject.setCode(1);			
			}
		}
		return jsonObject;
	}
	
	///////////////////////////////////////////////////////////
	/**
	 * 公告管理
	 * @return
	 */
	@RequestMapping("/announcement_list")
	public String announcement_list(HttpSession session){
		User user = (User) session.getAttribute("admin");
		//查询所有的日记
		if(user != null){
			Map<String,Object> map = adminService.announcement_list();
			session.setAttribute("admin_announcement_list", map);
		}
		return "admin/announcement-list";	
	}
	
	///////////////////////////////////////////////////////////
	/**
	 * 公告发布页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/announcement")
	public String announcement(HttpSession session){
		User user = (User) session.getAttribute("admin");
		if(user != null){
			return "dairy/editor/examples/announcement";
		}
		return "redirect:/dairy/four";
	}
	
	/**
	 * 删除公告
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete_announcement_list")
	@ResponseBody
	public JsonObject delete_announcement_list(String id,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		if(user != null){
			boolean flag = adminService.delete_announcement_list(id);
			if(flag){
				jsonObject.setCode(1);			
			}
		}
		return jsonObject;
	}

	/**
	 * 阅读公告
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/announcement_read")
	public String announcement_read(String id,HttpSession session){
		User user = (User) session.getAttribute("admin");
		if(user != null){
			Announcement announcement = adminService.announcement_read(id);
			if(announcement != null){
				session.setAttribute("md", announcement.getMd());
			}
		}
		return "dairy/editor/examples/announcement-show";
	}
	
	/**
	 * 公告上传图片内容
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/announcement_uploadfile")
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
	 * 公告保存
	 * @param announcement
	 * @param session
	 * @return
	 */
	@RequestMapping("/announcement_save")
	@ResponseBody
	public JsonObject announcement_save(Announcement announcement,HttpSession session){
		JsonObject jsonObject = new JsonObject();
		User user = (User) session.getAttribute("admin");
		if(user != null){
			boolean flag = adminService.announcement_save(announcement,user);
			if(flag){
				jsonObject.setCode(1);
				jsonObject.setMsg("success");
			}
		}
		return jsonObject;
	}
}
