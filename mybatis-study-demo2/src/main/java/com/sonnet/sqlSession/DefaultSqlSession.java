package com.sonnet.sqlSession;

import com.sonnet.cfg.Configuration;
import com.sonnet.proxy.ProxyFactory;
import com.sonnet.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession{

    private Configuration cfg;

    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> tClass) {
        // 使用JDK动态代理技术
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new ProxyFactory(cfg.getMappers(), connection));
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
