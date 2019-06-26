package com.yarcl.springquart.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

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
@MapperScan(basePackages = {"com.yarcl.springquart.dao.oracle"},  sqlSessionTemplateRef="oracleSessionTemplate")
public class OracleConfig {
    // 构造单例对象
    private static PropertiesConfiguration propertiesConfiguration = PropertiesConfiguration.getSingleInstance(true);

    /*@Value("${spring.datasource.oracle.url}")
    private String oracleUrl;

    @Value("${spring.datasource.oracle.username}")
    private String oracleUsername;

    @Value("${spring.datasource.oracle.password}")
    private String oraclePassword;

    @Value("${spring.datasource.oracle.driverClassName}")
    private String oracleDriverClassName;

    @Value("${spring.datasource.oracle.filters}")
    private String oracleFilters;

    @Value("${spring.datasource.oracle.principalSessionName}")
    private String oraclePrincipalSessionName;*/

    @Bean("oracleDataSource")
    @Primary
    public static DruidDataSource dataSource2() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertiesConfiguration.getOracleUrl());
        dataSource.setUsername(propertiesConfiguration.getOracleUsername());
        dataSource.setPassword(propertiesConfiguration.getOraclePassword());
        dataSource.setDriverClassName(propertiesConfiguration.getOracleDriverClassName());
        dataSource.setFilters(propertiesConfiguration.getOracleFilters());
        return dataSource;
    }
    // 事物管理器
    @Bean(name = "oracleTransManager")
    public static PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource2());
    }
    //提供SqlSeesionFactory
    @Bean(name = "oracleSessionFactory")
    public static SqlSessionFactory oracleSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    //提供SqlSeesionTemplate
    @Bean(name = "oracleSessionTemplate")
    public static SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(oracleSqlSessionFactoryBean());
    }
}
