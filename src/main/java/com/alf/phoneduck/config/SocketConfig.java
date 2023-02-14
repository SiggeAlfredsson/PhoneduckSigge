package com.alf.phoneduck.config;

import com.alf.phoneduck.ws.ChannelStateSocketHandler;
import com.alf.phoneduck.ws.ChatStateSocketHandler;
import com.alf.phoneduck.ws.UserStateSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private UserStateSocketHandler userStateSocketHandler;

    @Autowired
    private ChannelStateSocketHandler channelStateSocketHandler;

    @Autowired
    private ChatStateSocketHandler chatStateSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(userStateSocketHandler, SOCKET_PREFIX + "/user/state");
        registry.addHandler(channelStateSocketHandler, SOCKET_PREFIX +  "/channel");
        registry.addHandler(chatStateSocketHandler, SOCKET_PREFIX + "/chatt");
    }


}
