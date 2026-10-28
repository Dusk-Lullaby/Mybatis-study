package com.sonnet.utils;


import com.sonnet.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 负责执行SQL语句，并且封装结果集
 */
public class Executor {

    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.取出mapper中的数据
            String queryString = mapper.getQueryString();//select * from user
            String resultType = mapper.getResultType();//com.xq.pojo.User
            Class domainClass = Class.forName(resultType);
            //2.获取PreparedStatement对象
            pstm = conn.prepareStatement(queryString);
            //3.执行SQL语句，获取结果集
            rs = pstm.executeQuery();
            //4.封装结果集
            List<E> list = new ArrayList<E>();//定义返回值
            while(rs.next()) {
                //实例化要封装的实体类对象
                E obj = (E)domainClass.newInstance();

                //取出结果集的元信息：ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出总列数
                int columnCount = rsmd.getColumnCount();
                //遍历总列数
                for (int i = 1; i <= columnCount; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//要求：实体类的属性和数据库表的列名保持一种
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    columnValue = convertValue(columnName, columnValue, pd.getPropertyType());
                    writeMethod.invoke(obj, columnValue);
                }
                //把赋好值的对象加入到集合中
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm,rs);
        }
    }


    static Object convertValue(String columnName, Object value, Class<?> targetType) {
        Class<?> wrappedTargetType = wrapPrimitiveType(targetType);
        if (value == null) {
            if (targetType.isPrimitive()) {
                throw new IllegalArgumentException("Cannot map null from column '" + columnName
                        + "' to primitive type " + targetType.getName());
            }
            return null;
        }

        if (wrappedTargetType.isInstance(value)) {
            return value;
        }

        if (wrappedTargetType == Date.class && value instanceof LocalDateTime) {
            return Date.from(((LocalDateTime) value).atZone(ZoneId.systemDefault()).toInstant());
        }
        if (wrappedTargetType == Date.class && value instanceof LocalDate) {
            return Date.from(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (value instanceof Number && Number.class.isAssignableFrom(wrappedTargetType)) {
            try {
                return convertNumber((Number) value, wrappedTargetType);
            } catch (ArithmeticException | NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert column '" + columnName + "' from "
                        + value.getClass().getName() + " to " + targetType.getName(), e);
            }
        }

        throw new IllegalArgumentException("Cannot map column '" + columnName + "' from "
                + value.getClass().getName() + " to " + targetType.getName());
    }

    private static Object convertNumber(Number value, Class<?> targetType) {
        BigDecimal decimalValue = toBigDecimal(value);
        if (targetType == Byte.class) {
            return Byte.valueOf(decimalValue.byteValueExact());
        }
        if (targetType == Short.class) {
            return Short.valueOf(decimalValue.shortValueExact());
        }
        if (targetType == Integer.class) {
            return Integer.valueOf(decimalValue.intValueExact());
        }
        if (targetType == Long.class) {
            return Long.valueOf(decimalValue.longValueExact());
        }
        if (targetType == Float.class) {
            return Float.valueOf(value.floatValue());
        }
        if (targetType == Double.class) {
            return Double.valueOf(value.doubleValue());
        }
        if (targetType == BigInteger.class) {
            return decimalValue.toBigIntegerExact();
        }
        if (targetType == BigDecimal.class) {
            return decimalValue;
        }
        throw new IllegalArgumentException("Unsupported numeric type: " + targetType.getName());
    }

    private static BigDecimal toBigDecimal(Number value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        return new BigDecimal(value.toString());
    }

    private static Class<?> wrapPrimitiveType(Class<?> type) {
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == float.class) return Float.class;
        if (type == double.class) return Double.class;
        if (type == boolean.class) return Boolean.class;
        if (type == char.class) return Character.class;
        return type;
    }

    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
