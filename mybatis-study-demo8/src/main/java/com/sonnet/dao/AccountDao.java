package com.sonnet.dao;

import com.sonnet.pojo.Account;

import java.util.List;

public interface AccountDao {

    // 根据用户id查询对应的账户信息
    public List<Account> findAccountByUid(Integer uid);
}
