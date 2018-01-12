package com.wbkit.bigScreen.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@ConfigurationProperties(locations = "classpath:/application.properties")
public class OracleDataSourceConfig {
    //注意不能使用static静态变量，默认不支持
    @Value("${spring.datasource.oracle.url}")
    private String oracleUrl;

    /*@Value("${spring.datasource.oracle.username}")
    private String oracleUsername;

    @Value("${spring.datasource.oracle.password}")
    private String oraclePassword;

    @Value("${spring.datasource.oracle.driverClassName}")
    private String oracleDriverClassName;

    @Bean     //声明其为Bean实例
    public DataSource oracleDataSource(){
        DataSource oracleDataSource = new DataSource();
        oracleDataSource.setUrl(oracleUrl);
        oracleDataSource.setUsername(oracleUsername);
        oracleDataSource.setPassword(oraclePassword);
        oracleDataSource.setDriverClassName(oracleDriverClassName);
        return oracleDataSource;
    }*/
    public String getForYourInfo(){
        System.out.println(oracleUrl);
        return "hello";
    }
}
