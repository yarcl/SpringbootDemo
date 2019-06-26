package com.yarcl.springquart.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;


/**
 * Created by Administrator on 2018/1/12.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.yarcl.springquart.dao.mysql", "com.yarcl.springquart.dao"}, sqlSessionTemplateRef ="mysqlSessionTemplate")
public class MysqlConfig {

    // 构造单例对象
    private static PropertiesConfiguration propertiesConfiguration = PropertiesConfiguration.getSingleInstance(true);

    /*@Value("${spring.datasource.mysql.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.mysql.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.mysql.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.mysql.driverClassName}")
    private String mysqlDriverClassName;

    @Value("${spring.datasource.mysql.filters}")
    private String mysqlFilters;

    @Value("${spring.datasource.mysql.principalSessionName}")
    private String mysqlPrincipalSessionName;*/

    @Bean("mysqlDataSource")
    public static DruidDataSource dataSource1() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertiesConfiguration.getMysqlUrl());
        dataSource.setUsername(propertiesConfiguration.getMysqlUsername());
        dataSource.setPassword(propertiesConfiguration.getMysqlPassword());
        dataSource.setDriverClassName(propertiesConfiguration.getMysqlDriverClassName());
        dataSource.setFilters(propertiesConfiguration.getMysqlFilters());
        dataSource.setRemoveAbandoned(true);
        dataSource.setLogAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(180);
        dataSource.setBreakAfterAcquireFailure(true);
        return dataSource;
    }

    // 事物管理器
    @Bean
    public static PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource1());
    }

    //提供SqlSeesion
    @Bean
    public static SqlSessionFactory mysqlSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource1());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //提供SqlSeesion
    @Bean(name = "mysqlSessionTemplate")
    public static SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSqlSessionFactoryBean());
    }
}
