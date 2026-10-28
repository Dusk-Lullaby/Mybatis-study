package com.sonnet.sqlSession;

public interface SqlSession {

    // 获取代理对象的方法
    public <T> T getMapper(Class<T> tClass);

    // 释放资源的方法
    public void close();
}
