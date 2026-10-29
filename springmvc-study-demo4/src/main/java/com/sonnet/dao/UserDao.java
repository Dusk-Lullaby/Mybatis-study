package com.sonnet.dao;

import com.sonnet.pojo.User;

import java.util.List;

public interface UserDao {

    // 查询所有用户信息
    public List<User> findAll();

    // 根据id查询
    public User findById(Integer id);
}
