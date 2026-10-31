package com.sonnet;

import com.sonnet.dao.UserDao;
import com.sonnet.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestMybatis {

    SqlSession sqlSession;
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

    @Test
    public void test02() {
        User user = userDao.findUserById(41);
        System.out.println(user);
    }

/*
    @Test
    public void test03() {
        User user = new User();
        user.setUsername("jiayi");
        user.setAddress("浙江");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.addUser(user);
        System.out.println("新增的主键是：" + user.getId());
    }

    @Test
    public void test04() {
        User user = new User();
        user.setId(67);
        user.setAddress("中国");
        user.setUsername("嫁衣");
        userDao.updateUser(user);
    }

    @Test
    public void test05() {
        userDao.deleteUserById(67);
    }

    @Test
    public void test06() {
        Integer i = userDao.getCount();
        System.out.println(i);
    }
*/
}
