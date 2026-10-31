package com.sonnet.pojo;

import java.util.Date;
import java.util.List;

/**
 * pojo 与数据表user形成映射关系
 * 定义的规则：
 *      1. 实体类的字段和数据表中的名称保持一致
 *      2. 实体类的字段的数据类型必须和数据表中的字段的数据类型一样
 */
public class User {

    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

/*
    // 一个用户对应有多个账户
    private List<Account> accounts;
*/

    public User() {
    }

    public User(Integer id, String username, Date birthday, String sex, String address) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

/*
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
*/

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", birthday = " + birthday + ", sex = " + sex + ", address = " + address + "}";
    }

/*
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }
*/
}
