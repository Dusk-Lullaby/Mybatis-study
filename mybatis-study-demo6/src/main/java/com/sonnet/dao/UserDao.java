package com.sonnet.dao;

import com.sonnet.pojo.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();
}
