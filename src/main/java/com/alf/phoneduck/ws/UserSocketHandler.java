package com.alf.phoneduck.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    public void broadcast(String channel, String message) {
        try {
            for (WebSocketSession webSession : sessions) { // broadcast
                String stateOnline = webSession.getHandshakeHeaders().getFirst("user-state-online");
                String stateOffline = webSession.getHandshakeHeaders().getFirst("user-state-offline");
                if(channel.equals(stateOnline) || channel.equals(stateOffline)) {
                    webSession.sendMessage(new TextMessage(message));
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
