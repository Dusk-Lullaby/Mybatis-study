package com.sonnet.dao;

import com.sonnet.pojo.User;

public interface UserDao {

    // 根域用户id查询对应的用户信息
    public User findUserById(Integer id);
}
