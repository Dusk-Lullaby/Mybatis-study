package com.sonnet.dao;

import com.sonnet.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    // 动态查询·
    public List<User> findByCondition(User user);

    // 动态查询2
    public List<User> findByCondition2(User user);

    // 动态修改
    public void updateUserByCondition(User user);

    // 动态新增
    public void addUserSelective(User user);

    // 动态新增2
    public List<User> findByCondition3(User user);

    // 批量删除 将id存在在数组里
    public void deleteUserByIds(@Param("ids") Integer[] ids);

    // 批量删除 将id存在在集合里
    public void deleteUserByIds2(List<Integer> list);

    // 新增
    public void addUser(User user);
}
