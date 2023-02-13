package com.alf.phoneduck.controller;

import com.alf.phoneduck.model.Channel;
import com.alf.phoneduck.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelController {


    @Autowired
    private ChannelService courseService;

    @GetMapping("channel")
    public ResponseEntity<List<Channel>> getCourses() {
        List<Channel> courses = courseService.getAll();

        if (courses.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .header("x-information", "No data was found in the database")
                    .build();
        } else {
            return ResponseEntity.ok(courses);
        }
    }

}
