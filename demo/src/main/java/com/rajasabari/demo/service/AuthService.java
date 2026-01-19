package com.rajasabari.demo.service;

import com.rajasabari.demo.entity.User;

public interface AuthService {

    User register(User user);

    User login(String email, String password);
}
