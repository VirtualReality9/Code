package com.unexpect.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryConfig {
    private DruidDataSource dataSource;

    private static ConnectionFactoryConfig connectionFactoryConfig;

    private Connection getConnection() throws SQLException{
        System.err.println("实例化");
        Properties properties = new Properties();
        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/oa?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String driverClassName="com.mysql.cj.jdbc.Driver";
        properties.put("driverClassName",driverClassName);
        properties.put("url",url);
        properties.put("username",user);
        properties.put("password",password);

        try{
            dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            try {
                init();
            } catch (Exception e2) {
            }
        }
        return dataSource.getConnection();
    }

    public static Connection getDatabaseConnection() throws SQLException {
        if(connectionFactoryConfig==null){
            connectionFactoryConfig = new ConnectionFactoryConfig();
        }
        System.out.println("实例化成功");
        return connectionFactoryConfig.getConnection();

    }

    public void init(){
        try {
            if (dataSource != null)
                dataSource.close();
        } catch (Exception e) {
        }
    }

}
