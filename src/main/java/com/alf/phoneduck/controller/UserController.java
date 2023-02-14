package com.alf.phoneduck.controller;

import com.alf.phoneduck.model.User;
import com.alf.phoneduck.service.UserService;
import com.alf.phoneduck.ws.UserStateSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserStateSocketHandler userStateSocketHandler;

    @Autowired
    private UserService userService;


    @GetMapping("user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .header("x-info", "no users was found in database")
                    .build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @PostMapping("user")
    @PatchMapping("user")
    @PutMapping("user")
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        userService.save(user);
        userStateSocketHandler.broadcast("new-user", "A new user was created, username :" + user.getUsername() + ".");
        return getAllUsers();
    }


    @DeleteMapping("user/{userId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable long userId) {
        userService.delete(userId);
        return getAllUsers();
    }

    @PatchMapping("user/online/{state}/{userId}")
    public void setOnlineState(@PathVariable String state, @PathVariable long userId) {
        User newUser = userService.get(userId);
        User oldUser = newUser.clone();

        switch(state) {
            case "online": newUser.setOnline(true); break;
            case "offline": newUser.setOnline(false); break;
            default: throw new IllegalStateException(state + " was illdefined");
        }


        userService.save(newUser);

        // broadcast student changes
        if(newUser.isOnline()) {
            userStateSocketHandler.broadcast("online", newUser.getUsername() + " is now online");
        } else {
            userStateSocketHandler.broadcast("offline", newUser.getUsername() + " is now offline");
        }
    }
}