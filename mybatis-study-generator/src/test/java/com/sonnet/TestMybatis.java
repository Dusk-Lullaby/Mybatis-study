package com.sonnet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sonnet.mapper.UserMapper;
import com.sonnet.pojo.User;
import com.sonnet.pojo.UserExample;
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
    UserMapper userMapper;
    //在@Test注解修饰的方法之前执行
    @Before
    public void init() throws Exception{
        InputStream in =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new
                SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    //在@Test注解修饰的方法之后执行
    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void test01() {
        List<User> userList = userMapper.selectByExample(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void test02() {
        UserExample userExample = new UserExample();
/*
        userExample.createCriteria().andIdEqualTo(41).andSexEqualTo("男");
        userExample.or().andAddressEqualTo("美国");
*/
        userExample.createCriteria().andUsernameLike("%男%");
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void test03() {
        User user = new User();
        user.setId(68);
        user.setUsername("jiayi");
        user.setSex("女");
        //userMapper.updateByPrimaryKey(user);
        userMapper.updateByPrimaryKeySelective(user);
        sqlSession.commit();
    }

    @Test
    public void test04() throws Exception{
        //开启分页操作
        PageHelper.startPage(2,4);
        List<User> userList = userMapper.selectByExample(null);
        // mybatis分页插件帮我们分装好的分页模型
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        System.out.println(userPageInfo);
    }
}
