package com.sonnet;

import com.sonnet.builder.SqlSessionFactoryBuilder;
import com.sonnet.dao.UserDao;
import com.sonnet.factory.SqlSessionFactory;
import com.sonnet.io.Resources;
import com.sonnet.pojo.User;
import com.sonnet.sqlSession.SqlSession;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void test() throws Exception{
        // 加载mybatis的核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 创建SqlSessionFactory对象（构建者设计模式，屏蔽了SqlSessionFactory创建的细节）
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 创建SqlSession对象（工厂模式）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession 生成接口的代理对象（动态代理）
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findAll();
        userList.forEach(System.out::println);
        // 关闭资源
        sqlSession.close();
        inputStream.close();
    }
}
