package com.sonnet.factory;

import com.sonnet.sqlSession.SqlSession;

public interface SqlSessionFactory {

    // 获取SqlSession对象
    public SqlSession openSession();
}
