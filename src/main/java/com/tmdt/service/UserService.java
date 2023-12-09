package com.tmdt.service;

import com.tmdt.models.User;

public interface UserService {
    User findByUserName(String userName);
}
