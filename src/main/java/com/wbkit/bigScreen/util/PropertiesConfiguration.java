package com.wbkit.bigScreen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/12.
 */
@Component
@ConfigurationProperties(locations = "classpath:/application.properties")
@EnableAutoConfiguration
public class PropertiesConfiguration {

    private static PropertiesConfiguration propertiesConfiguration;

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
    /*public synchronized static PropertiesConfiguration getPropertiesInstance(){
        if(propertiesConfiguration == null){
            return propertiesConfiguration = new PropertiesConfiguration();
        } else {
            return propertiesConfiguration;
        }
    }*/
}
