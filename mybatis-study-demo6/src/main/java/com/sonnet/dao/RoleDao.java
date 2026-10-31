package com.sonnet.dao;

import com.sonnet.pojo.Role;

import java.util.List;

public interface RoleDao {

    // 查询所有角色信息及其对应的用户信息
    public List<Role> findAll();
}
