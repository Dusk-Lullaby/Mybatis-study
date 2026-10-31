package com.sonnet.dao;

import com.sonnet.pojo.Account;

import java.util.List;

public interface AccountDao {

    // 加载账户信息及其对应的用户信息
    public List<Account> findAll();
}
