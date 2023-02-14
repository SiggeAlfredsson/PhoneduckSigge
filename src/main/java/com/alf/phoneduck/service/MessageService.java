package com.alf.phoneduck.service;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.model.Message;
import com.alf.phoneduck.repo.JpaMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private JpaMessageRepository jpaMessageRepository;

    public Message save(Message message) {
        return jpaMessageRepository.save(message);
    }

}
