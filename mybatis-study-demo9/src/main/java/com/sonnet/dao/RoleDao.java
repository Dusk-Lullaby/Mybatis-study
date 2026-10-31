package com.sonnet.dao;

import com.sonnet.pojo.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> findRolesByUid(Integer id);
}
