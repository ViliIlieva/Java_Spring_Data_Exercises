package com.example.gamestore.services;

import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.entities.users.User;

public interface UserService {
    User register(RegisterDTO registerData);
    User login();
    void logout();

}
