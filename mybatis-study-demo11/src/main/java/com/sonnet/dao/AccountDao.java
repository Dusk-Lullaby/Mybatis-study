package com.sonnet.dao;

import com.sonnet.pojo.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    // 查询所有的账户信息及其关联的用户信息
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user",
                    one = @One(select = "com.sonnet.dao.UserDao.findUserById",
                    fetchType = FetchType.LAZY))
    })
    @Select("select * from account")
    public List<Account> findAll();
}
