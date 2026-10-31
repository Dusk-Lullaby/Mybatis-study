package com.sonnet.dao;

import com.sonnet.pojo.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    // 根据用户id查询账户信息
    @Select("select * from account where uid = #{id}")
    public List<Account> findAccountByUId(Integer id);
}
