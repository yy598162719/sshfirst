package com.ssh.dao;

import com.ssh.domain.User;

public interface UserDao {
    void regist(User user);

    User login(User user);
}
