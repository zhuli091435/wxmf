package com.wxmf;

import com.wxmf.dao.impl.PackageVersionDaoImpl;
import com.wxmf.pojo.PackageVersion;

import java.io.File;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //
        //System.out.println( "Hello World!" );

        String relativePath = "PackageVersion/file.txt";

        // 创建File对象
        File file = new File(relativePath);

        // 输出文件的绝对路径
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
    }
}
