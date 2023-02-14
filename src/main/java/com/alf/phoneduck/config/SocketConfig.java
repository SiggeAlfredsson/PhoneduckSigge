package com.alf.phoneduck.config;


import com.alf.phoneduck.ws.ChatSocketHandler;
import com.alf.phoneduck.ws.UserSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

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
        registry.addHandler(chatSocketHandler, SOCKET_PREFIX +  "/channel");
        registry.addHandler(chatSocketHandler, SOCKET_PREFIX + "/chat");
    }


}
