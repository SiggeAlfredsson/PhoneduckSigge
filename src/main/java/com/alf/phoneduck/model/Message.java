package com.alf.phoneduck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Entity
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String message;
    private long channelId;
    private Date createdAt;
    private LocalTime sentAtTimeStamp;

    //private String senderUsername;


}
