package com.alf.phoneduck.ws;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.model.Message;
import com.alf.phoneduck.repo.JpaMessageRepository;
import com.alf.phoneduck.service.ChannelService;
import com.alf.phoneduck.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    private List<WebSocketSession> sessions = new ArrayList<>();


    public boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        for (Map.Entry<String, List<String>> entry : session.getHandshakeHeaders().entrySet()) {
            String headerKey = entry.getKey();

            if (isNumeric(headerKey)) { //inte super nöjd här, bla att value e hårdkodat + ej används
                session.getAttributes().put("key", headerKey);
                broadcast(headerKey,  message.getPayload());
                Message newMessage = new Message();
                newMessage.setMessage(message.getPayload());
                newMessage.setChannelId(Long.parseLong(headerKey));
                newMessage.setCreatedAt(new Date());
                newMessage.setSentAtTimeStamp(LocalTime.now());
                messageService.save(newMessage);

            }
        }
    }

    public void broadcast(String key,  String message) {
        try {
            for (WebSocketSession webSession : sessions) {
                String sessionKey = (String) webSession.getAttributes().get("key");
                System.out.println(sessionKey);
                if (sessionKey != null && sessionKey.equals(key)) {
                    webSession.sendMessage(new TextMessage("New message: " + message));
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        //inte super nöjd här..

        List<Channel> channels = channelService.getAll();

        TextMessage welcomeMessage = new TextMessage("Welcome! Here are all the active channels!");
        session.sendMessage(welcomeMessage);

        String connectedMessageString = "You are currently connected to the following channels: ";

        for (Map.Entry<String, List<String>> entry : session.getHandshakeHeaders().entrySet()) {
            String headerKey = entry.getKey();
            if (isNumeric(headerKey)) {
                connectedMessageString += headerKey + ", ";
            }
        }
        TextMessage connectedMessage = new TextMessage(connectedMessageString.substring(0,connectedMessageString.length()-2)+".");
        session.sendMessage(connectedMessage);


        for (Channel channel : channels) {
            TextMessage activeChannelMessage = new TextMessage(
                    "ID: " + channel.getId()
                    + ". Name: " + channel.getName()
                    + ". Description: " + channel.getDescription()
                    + "."
            );
            session.sendMessage(activeChannelMessage);

        }

            sessions.add(session);
            System.out.println("New session created");

        }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session was removed");
    }
}