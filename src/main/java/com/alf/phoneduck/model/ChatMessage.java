package com.alf.phoneduck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@Entity
public class ChatMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String senderUsername;
    private LocalDate sendAtDate;
    private LocalTime sendTimeStamp;

    private String message;


}
