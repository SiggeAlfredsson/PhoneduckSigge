package com.alf.phoneduck.service;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.repo.JpaChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {


    @Autowired
    private JpaChannelRepository jpaChannelRepository;

    public Channel save(Channel channel) {
        return jpaChannelRepository.save(channel);
    }

    public List<Channel> getAll() {
        return jpaChannelRepository.findAll(); // SELECT * FROM course;
    }

    public void delete(long channelId) {
        jpaChannelRepository.deleteById(channelId);
    }

}
