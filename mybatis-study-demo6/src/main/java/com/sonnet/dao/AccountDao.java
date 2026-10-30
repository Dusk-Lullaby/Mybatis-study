package com.sonnet.dao;

import com.sonnet.pojo.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();
}
