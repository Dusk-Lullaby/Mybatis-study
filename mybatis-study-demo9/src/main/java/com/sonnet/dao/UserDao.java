package com.sonnet.dao;

import com.sonnet.pojo.User;

import java.util.List;

public interface UserDao {

    // 查询用户信息及其对应的角色信息
    public List<User> findAll();

}
