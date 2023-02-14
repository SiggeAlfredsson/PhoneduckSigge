package com.alf.phoneduck;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.model.User;
import com.alf.phoneduck.repo.JpaChannelRepository;
import com.alf.phoneduck.repo.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneduckApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneduckApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(JpaChannelRepository jpaChannelRepository) {
        return args -> {
            System.out.println("-------initalized database-------");


            Channel channel = new Channel();
            channel.setName("Permanent Channel");
            channel.setDescription("Channel to advertise other channels for users to subscribe to");
            jpaChannelRepository.save(channel);

        };
    }
}
