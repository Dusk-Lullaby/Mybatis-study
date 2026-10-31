package com.sonnet.dao;

import com.sonnet.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Results(id = "userMap", value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address")
    })
    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);
}
