package com.sonnet.proxy;

import com.sonnet.cfg.Mapper;
import com.sonnet.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class ProxyFactory implements InvocationHandler {

    private Map<String, Mapper> mappers;
    private Connection connection;

    public ProxyFactory(Map<String, Mapper> mappers, Connection connection) {
        this.mappers = mappers;
        this.connection = connection;
    }

    // 增强的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取方法名称
        String methodName = method.getName();
        // 获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 拼接key值
        String key = className + "." + methodName;
        // 获取Map集合中的Mapper对象
        Mapper mapper = mappers.get(key);
        if (mapper == null) {
            throw new IllegalAccessException("传入的参数有误");
        }
        return new Executor().selectList(mapper, connection);
    }
}
