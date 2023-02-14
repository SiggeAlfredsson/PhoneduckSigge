package com.alf.phoneduck.service;

import com.alf.phoneduck.model.User;
import com.alf.phoneduck.repo.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    public List<User> getAll() {
        return jpaUserRepository.findAll();
    }

    public User get(long id) {
        return jpaUserRepository.getReferenceById(id);
    }

    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    public void delete(long userId) {
        jpaUserRepository.deleteById(userId);
    }
}