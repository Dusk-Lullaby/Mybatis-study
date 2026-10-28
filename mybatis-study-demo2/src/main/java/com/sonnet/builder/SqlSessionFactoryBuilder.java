package com.sonnet.builder;

import com.sonnet.cfg.Configuration;
import com.sonnet.factory.DefaultSqlSessionFactory;
import com.sonnet.factory.SqlSessionFactory;
import com.sonnet.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    // 定义一个build方法
    public SqlSessionFactory build(InputStream in) {
        // 通过加载xml配置文件，生成一个Configuration对象
        Configuration configuration = XMLConfigBuilder.loadConfiguration(in);
        return new DefaultSqlSessionFactory(configuration);
    }
}
