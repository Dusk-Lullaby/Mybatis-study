package com.sonnet;

import com.sonnet.dao.AccountDao;
import com.sonnet.dao.UserDao;
import com.sonnet.pojo.Account;
import com.sonnet.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    SqlSession sqlSession;
    AccountDao accountDao;
    UserDao userDao;

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
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After // 在@Test方法执行之后执行
    public void after() throws Exception{
        sqlSession.close();
    }

    @Test
    public void test01() {
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
