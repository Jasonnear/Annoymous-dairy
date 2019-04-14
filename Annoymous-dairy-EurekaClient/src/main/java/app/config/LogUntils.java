package app.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogUntils {

	public static final Logger log = LoggerFactory.getLogger(LogUntils.class);
	//app.controller.dairy.DairyController.a()
	
	
	@Before(value = "execution(public * app.controller.*.*.*(..)) && !execution(public void app.controller.user.DairyController.send()) && !execution(public void app.controller.user.DairyController.open())")
	public void get_request_info(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //获取key-value
        Map<String, String[]> map = request.getParameterMap();        
        Set<String> set_key = map.keySet();
        //存放key-value
        Map<String,String> map_all = new HashMap<String,String>();
        for(String key:set_key){
        	String[] value_array = map.get(key);
        	for (int i = 0; i < value_array.length; i++) {
        		map_all.put(key, value_array[i]);
			}       	
        }
        log.info("-----------------------------------");
		log.info("请求方式:" + request.getMethod());
		log.info("请求路径:" + request.getRequestURI());
		if(map.size() != 0){
			log.info("请求参数:" + map_all);
		}
		log.info("响应状态码:" + response.getStatus());
	}
	
	@Before(value = "execution(public * app.service.*.*.*(..))")
	public void get_service_info(){
        log.info("sql执行信息:");
	}
}
