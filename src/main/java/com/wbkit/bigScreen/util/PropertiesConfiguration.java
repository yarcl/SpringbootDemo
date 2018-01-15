package com.wbkit.bigScreen.util;

import org.apache.log4j.Logger;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/12.
 */
@ComponentScan
@ConfigurationProperties(locations = "classpath:/application.properties")
public class PropertiesConfiguration {

    private Logger logger = Logger.getLogger(PropertiesConfiguration.class);
    //注意不能使用static静态变量，默认不支持
    private String mysqlUrl;
    private String mysqlUsername;
    private String mysqlPassword;
    private String mysqlDriverClassName;
    private String oracleUrl;
    private String oracleUsername;
    private String oraclePassword;
    private String oracleDriverClassName;
    private String mysqlFilters;
    private String oracleFilters;
    private String mysqlPrincipalSessionName;
    private String oraclePrincipalSessionName;



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

    public String getMysqlFilters() {
        return mysqlFilters;
    }

    public String getOracleFilters() {
        return oracleFilters;
    }

    public String getMysqlPrincipalSessionName() {
        return mysqlPrincipalSessionName;
    }

    public String getOraclePrincipalSessionName() {
        return oraclePrincipalSessionName;
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
            this.mysqlFilters = properties.getProperty("spring.datasource.oracle.filters");
            this.mysqlPrincipalSessionName = properties.getProperty("spring.datasource.mysql.principalSessionName");
            this.oracleUrl = properties.getProperty("spring.datasource.oracle.url");
            this.oracleUsername = properties.getProperty("spring.datasource.oracle.username");
            this.oraclePassword = properties.getProperty("spring.datasource.oracle.password");
            this.oracleDriverClassName = properties.getProperty("spring.datasource.oracle.driverClassName");
            this.oracleFilters = properties.getProperty("spring.datasource.oracle.filters");
            this.oraclePrincipalSessionName = properties.getProperty("spring.datasource.oracle.principalSessionName");
        } catch (Exception e){
            logger.error(e);
        }
    }
}
