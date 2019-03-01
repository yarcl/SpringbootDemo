package com.yarcl.springquart.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private static PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(true);

    @Bean(name="oracleDatasource")
    public static DruidDataSource dataSource2() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertiesConfiguration.getOracleUrl());
        dataSource.setUsername(propertiesConfiguration.getOracleUsername());
        dataSource.setPassword(propertiesConfiguration.getOraclePassword());
        dataSource.setDriverClassName(propertiesConfiguration.getOracleDriverClassName());
        dataSource.setFilters(propertiesConfiguration.getMysqlFilters());
        return dataSource;
    }

    // 事物管理器
    @Bean(name = "oracleTransManager")
    public static PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource2());
    }

    //提供SqlSeesion
    @Bean(name = "oracleSessionFactory")
    public static SqlSessionFactory oracleSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //提供SqlSeesion
    @Bean(name = "oracleSessionTemplate")
    public static SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(oracleSqlSessionFactoryBean());
    }
}
