package com.sonnet;

import com.sonnet.dao.UserDao;
import com.sonnet.pojo.QueryVo;
import com.sonnet.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

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
        sqlSession = sqlSessionFactory.openSession();
        // 生成接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After // 在@Test方法执行之后执行
    public void after() throws Exception{
        sqlSession.close();
    }

    @Test
    public void test01() throws Exception{
        List<User> userList = userDao.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void test02() throws Exception{
        User user = userDao.findUserById(48);
        System.out.println(user);
    }

    @Test
    public void test03() {
        User user = new User();
        user.setUsername("嫁衣");
        user.setBirthday(new Date());
        user.setAddress("zhejiang");
        user.setSex("女");
        userDao.addUser(user);
        // 获取新增数据的主键
        System.out.println("新增用户的id：" + user.getId());
        // 手动提交事务
        sqlSession.commit();
    }

    @Test
    public void test04() {
        User user = new User();
        user.setId(52);
        user.setUsername("佳颖");
        user.setBirthday(new Date());
        user.setAddress("浙江");
        user.setSex("女");
        userDao.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void test05() {
        userDao.deleteUserById(52);
        sqlSession.commit();
    }

    @Test
    public void test06() {
        // select * from user where username like ?
        // 查询名称中包含王的用户信息
        userDao.findUserByUsername("%王%").forEach(System.out::println);
    }

    @Test
    public void test07() {
        // select * from user where username like '%王%'     sql参数的直接注入，有sql参数注入的风险
        // 查询名称中包含王的用户信息
        userDao.findUserByUsername2("王").forEach(System.out::println);
    }

    @Test
    public void test08() {
        Integer total = userDao.getTotal();
        System.out.println("总记录数是：" + total);
    }

    @Test
    public void test09() {
        // select * from user where sex = '女' and address = 'zhejiang'      有sql注入风险
        userDao.findUserBySexAndAddress("女", "zhejiang").forEach(System.out::println);
    }

    @Test
    public void test010() {
        Map<String, Object> map = new HashMap<>();
        map.put("sex", "女");
        map.put("address", "zhejiang");
        // select * from user where sex = ？ and address = ？
        userDao.findUserByMap(map).forEach(System.out::println);
    }

    @Test
    public void test11() {
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(43);
        ids.add(45);
        ids.add(51);
        queryVo.setIds(ids);
        List<User> userList = userDao.findUserByIds(queryVo);
        userList.forEach(System.out::println);
    }
}
