package com.alf.phoneduck.config;


import com.alf.phoneduck.ws.ChatSocketHandler;
import com.alf.phoneduck.ws.UserSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import java.util.Map;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private UserSocketHandler userSocketHandler;



    @Autowired
    private ChatSocketHandler chatSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(userSocketHandler, SOCKET_PREFIX + "/user/state");
        //registry.addHandler(chatSocketHandler, SOCKET_PREFIX +  "/channel");
        registry.addHandler(chatSocketHandler, SOCKET_PREFIX + "/chat");

        registry.addHandler(chatSocketHandler, SOCKET_PREFIX + "/channel")
                .addInterceptors(new HandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                        // Set the default key value to 1
                        attributes.put("key", "1");
                        return true;
                    }
                    @Override
                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {}
                });

    }


}
