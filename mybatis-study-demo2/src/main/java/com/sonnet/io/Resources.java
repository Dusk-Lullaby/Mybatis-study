package com.sonnet.io;

import java.io.InputStream;

public class Resources {

    public static InputStream getResourceAsStream(String fileName) {
        // 使用类加载器记载指定的配置文件
        return Resources.class.getClassLoader().getResourceAsStream(fileName);
    }
}
