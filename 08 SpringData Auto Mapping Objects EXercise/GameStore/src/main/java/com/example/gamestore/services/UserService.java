package com.example.gamestore.services;

import com.example.gamestore.entities.User;

public interface UserService {
    User register();
    User login();
    void logout();

}
