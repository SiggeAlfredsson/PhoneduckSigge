package com.alf.phoneduck.model;

import lombok.Getter;

@Getter
public class UserStateDetails {
    private User oldState;
    private User newState;

    public UserStateDetails(User oldState, User newState) {
        this.oldState = oldState.clone();
        this.newState = newState.clone();
    }
}
