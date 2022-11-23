package com.sgi.springtraining.rest.service;

import com.sgi.springtraining.rest.model.Book;
import com.sgi.springtraining.rest.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    // Mock service

    public User createUser(User user) {
        return user;
    }

    public User updateUser(Long id, User user) {
        return user;
    }

    public User getUser(Long id) {
        return new User();
    }

    public ArrayList<Book> allUser() {
        return new ArrayList<Book>();
    }

    public User deleteUser(Long id) {
        return new User();
    }
}
