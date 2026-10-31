package com.sonnet.dao;

import com.sonnet.pojo.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {

    // 查询用户信息及其关联的账户信息
    @Results(id = "userMap", value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "accountList", column = "id",
                    many = @Many(select = "com.sonnet.dao.AccountDao.findAccountByUId",
                            fetchType = FetchType.LAZY))
    })
    @Select("select * from user")
    public List<User> findAll();
}
