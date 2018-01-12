package com.wbkit.bigScreen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/*@Configuration
@EnableAutoConfiguration*/
public class DataSourceConfig {
    //注意不能使用static静态变量，默认不支持
    /*@Value("${spring.datasource.mysql.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.mysql.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.mysql.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.mysql.driverClassName}")
    private String mysqlDriverClassName;*/

    /*@Bean(name="mysqlDatasource")
    @ConfigurationProperties(locations = "classpath:/application.properties", prefix = "spring.datasource.mysql") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="oracleDatasource")
    @ConfigurationProperties(locations = "classpath:/application.properties", prefix = "spring.datasource.oracle") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }*/
}
