package com.wbkit.bigScreen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/12.
 */
@ComponentScan
@ConfigurationProperties(locations = "classpath:/application.properties")
public class PropertiesConfiguration {
    //注意不能使用static静态变量，默认不支持
    @Value("${spring.datasource.mysql.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.mysql.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.mysql.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.mysql.driverClassName}")
    private String mysqlDriverClassName;

    @Value("${spring.datasource.oracle.url}")
    private String oracleUrl;

    @Value("${spring.datasource.oracle.username}")
    private String oracleUsername;

    @Value("${spring.datasource.oracle.password}")
    private String oraclePassword;

    @Value("${spring.datasource.oracle.driverClassName}")
    private String oracleDriverClassName;

    public String getMysqlUrl() {
        return mysqlUrl;
    }
    public String getMysqlUsername() {
        return mysqlUsername;
    }
    public String getMysqlPassword() {
        return mysqlPassword;
    }
    public String getMysqlDriverClassName() {
        return mysqlDriverClassName;
    }

    public String getOracleUrl() {
        return oracleUrl;
    }
    public String getOracleUsername() {
        return oracleUsername;
    }
    public String getOraclePassword() {
        return oraclePassword;
    }

    public String getOracleDriverClassName() {
        return oracleDriverClassName;
    }

    public PropertiesConfiguration() {
    }

    public PropertiesConfiguration(boolean flag){
        try{
            InputStream inputStream =
                    PropertiesConfiguration.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            this.mysqlUrl = properties.getProperty("spring.datasource.mysql.url");
            this.mysqlUsername = properties.getProperty("spring.datasource.mysql.username");
            this.mysqlPassword = properties.getProperty("spring.datasource.mysql.password");
            this.mysqlDriverClassName = properties.getProperty("spring.datasource.mysql.driverClassName");
            this.oracleUrl = properties.getProperty("spring.datasource.oracle.url");
            this.oracleUsername = properties.getProperty("spring.datasource.oracle.username");
            this.oraclePassword = properties.getProperty("spring.datasource.oracle.password");
            this.oracleDriverClassName = properties.getProperty("spring.datasource.oracle.driverClassName");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /*public synchronized static PropertiesConfiguration getPropertiesInstance(){
        if(propertiesConfiguration == null){
            return propertiesConfiguration = new PropertiesConfiguration();
        } else {
            return propertiesConfiguration;
        }
    }*/
}
