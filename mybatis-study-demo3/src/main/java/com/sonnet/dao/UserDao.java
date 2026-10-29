package com.sonnet.dao;

import com.sonnet.pojo.QueryVo;
import com.sonnet.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    // 查询所有用户信息
    public List<User> findAll();

    // 根据id查询用户信息
    public User findUserById(Integer id);

    // 保存用户
    public void addUser(User user);

    // 修改用户
    public void updateUser(User user);

    // 删除用户信息
    public void deleteUserById(Integer id);

    // 模糊查询1 根据用户名进行模糊查询
    public List<User> findUserByUsername(String username);

    // 模糊查询2 根据用户名进行模糊查询
    public List<User> findUserByUsername2(String username);

    // 聚合函数查询 统计当前数据表中的数据记录
    public Integer getTotal();

    // mybatis中接口存在多个参数问题
    public List<User> findUserBySexAndAddress(String sex, String address);

    // 使用map来解决接口中多个参数的问题
    public List<User> findUserByMap(Map<String, Object> map);

    // 根据id的集合查询相关的用户信息
    public List<User> findUserByIds(QueryVo queryVo);
}
