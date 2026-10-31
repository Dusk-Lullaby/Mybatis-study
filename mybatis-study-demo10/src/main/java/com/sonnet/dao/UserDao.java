package com.sonnet.dao;

import com.sonnet.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

/*
    // 查询所有用户信息
    @Select("select * from user")
    public List<User> findAll();

    // 根据id查询用户信息
    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);

    */
/**
     * 新增用户
     * @param user
     * @SelectKey 注解 用来描述新增之后主键字段回显
     *      keyProperty 主键对应的实体字段名称
     *      keyColumn 主键字段名称
     *      resultType 主键实体类型
     *      before 生成主键的时机 false 在新增之后，生成主键
     *      statement 查询主键的sql
     *//*

    @Insert("insert into user(username, birthday, sex, address) values(#{username}, #{birthday}, #{sex}, #{address})")
    @SelectKey(keyProperty = "id", keyColumn = "id", resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    public void addUser(User user);

    // 修改操作
    @Update("update user set username=#{username}, birthday=#{birthday}, sex=#{sex}, address=#{address} where id = #{id}")
    public void updateUser(User user);

    // 删除操作
    @Delete("delete from user where id = #{id}")
    public void deleteUserById(Integer id);

    // 聚合统计
    @Select("select count(id) from user")
    public Integer getCount();
*/

    /**
     * 基于前后字段不一致情形
     * @return
     */
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "address", property = "userAddress")
    })
    @Select("select * from user")
    public List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @ResultMap("userMap")
    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);
}
