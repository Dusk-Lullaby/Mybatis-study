package com.sonnet;

import com.sonnet.dao.AccountDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class TestMybatis {

    SqlSession sqlSession;
    AccountDao accountDao;

    @Before // 在@Test方法执行之前执行
    public void before() throws Exception{
        // 加载核心的配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 构建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 生成SqlSession对象
        sqlSession = sqlSessionFactory.openSession(true); // 关闭事务的手动提交
        // 生成接口的代理对象
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After // 在@Test方法执行之后执行
    public void after() throws Exception{
        sqlSession.close();
    }

    @Test
    public void test01() {
        accountDao.findAll().forEach(System.out::println);
    }
}
