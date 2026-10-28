package com.sonnet.factory;

import com.sonnet.cfg.Configuration;
import com.sonnet.sqlSession.DefaultSqlSession;
import com.sonnet.sqlSession.SqlSession;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
