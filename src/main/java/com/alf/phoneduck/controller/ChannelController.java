package com.alf.phoneduck.controller;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.service.ChannelService;

import com.alf.phoneduck.ws.ChatSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChannelController {

    @Autowired
    private ChatSocketHandler chatSocketHandler;

    @Autowired
    private ChannelService channelService;

    @GetMapping("channel")
    public ResponseEntity<List<Channel>> getAllChannels() {
        List<Channel> channels = channelService.getAll();

        if (channels.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .header("x-information", "No data was found in the database")
                    .build();
        } else {
            return ResponseEntity.ok(channels);
        }
    }

    @PostMapping("channel")
    public ResponseEntity<List<Channel>> createChannel(@RequestBody Channel channel) {
        channelService.save(channel);

        chatSocketHandler.broadcast("1",  "A new channel was created, Id: "
                        + channel.getId() + ". name: " + channel.getName() + ". Description: " + channel.getDescription());

        List<Channel> channels = channelService.getAll();
        return ResponseEntity.status(201).body(channels);
    }


    @DeleteMapping("channel/{channelId}")
    public ResponseEntity<List<Channel>> deleteChannel(@PathVariable long channelId) {

        List<Channel> channels = channelService.getAll();

        if (channelId > channels.size()) {
            return ResponseEntity
                    .status(400)
                    .header("x-error-msg", "Id out of bounds, no channel with that id")
                    .build();
        } else {


            channelService.delete(channelId);
            return getAllChannels();
        }
    }



}
