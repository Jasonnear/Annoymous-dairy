package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//注册端点
		registry.addEndpoint("/point").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/**
		 * 设置客户端可订阅消息的前缀
		 * 设置服务器端推送消息的前缀
		 */
		registry.enableSimpleBroker("/chat","/push");
		
		/**
		 * 设置客户端可发送消息的前缀
		 */
		registry.setApplicationDestinationPrefixes("/app");
	}

	
}
