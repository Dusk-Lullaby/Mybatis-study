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
import java.util.ArrayList;
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

    // 动态查询
    @Test
    public void test01() {
        User user = new User();
        user.setUsername("嫁衣");
        userDao.findByCondition(user).forEach(System.out::println);
    }

    // 动态查询
    @Test
    public void test02() {
        User user = new User();
        user.setUsername("嫁衣");
        userDao.findByCondition2(user).forEach(System.out::println);
    }

    // 动态修改
    @Test
    public void test03() {
        User user = new User();
        user.setUsername("夹心饼干");
        user.setAddress("中国");
        userDao.updateUserByCondition(user);
    }

    // 动态新增
    @Test
    public void test04() {
        User user = new User();
        user.setUsername("夹心饼干");
        user.setSex("女");
        userDao.addUserSelective(user);
    }

    // 动态查询，测试choose when otherwise
    @Test
    public void test05() {
        User user = new User();
        user.setUsername("嫁衣");
        user.setSex("女");
        user.setAddress("zhejiang");
        userDao.findByCondition3(user).forEach(System.out::println);
    }

    // 批量删除 传递的是数组对象
    @Test
    public void test06() {
        Integer[] ids = {1,2,3,4,5};
        userDao.deleteUserByIds(ids);
    }

    // 批量删除 传递的是数组对象
    @Test
    public void test07() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        userDao.deleteUserByIds2(list);
    }

    // 批量新增
    @Test
    public void test08() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("数据1");
        user1.setSex("男");
        user1.setBirthday(new Date());
        user1.setAddress("数据1");
        User user2 = new User();
        user2.setUsername("数据2");
        user2.setSex("男");
        user2.setBirthday(new Date());
        user2.setAddress("数据2");
        User user3 = new User();
        user3.setUsername("数据3");
        user3.setSex("男");
        user3.setBirthday(new Date());
        user3.setAddress("数据3");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        for (User user : list) {
            userDao.addUser(user);
        }
        sqlSession.commit();
    }
}
