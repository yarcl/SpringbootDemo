package com.wbkit.bigScreen.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/1/12.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.wbkit.bigScreen.dao.oracle"},  sqlSessionTemplateRef="oracleSessionTemplate")
public class OracleDataSourceConfig {

    //static PropertiesConfiguration propertiesConfiguration = PropertiesConfiguration.getPropertiesInstance();

    @Value("${server.port}")
    private static String name;

    @Bean(value="oracleDataSource")     //声明其为Bean实例
    @Primary
    public static DataSource getOracleDataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=utf8&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        /*dataSource.setUrl(propertiesConfiguration.getOracleUrl());
        dataSource.setUsername(propertiesConfiguration.getOracleUsername());
        dataSource.setPassword(propertiesConfiguration.getOraclePassword());
        dataSource.setDriverClassName(propertiesConfiguration.getOracleDriverClassName());*/
        return dataSource;
    }

    // 事物管理器
    @Bean(name = "oracleTransManager")
    @Transactional("oracleTransactionManager")
    @Primary
    public static PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getOracleDataSource());
    }

    //提供SqlSeesion
    @Bean(name = "oracleSessionFactory")
    @Primary
    public static SqlSessionFactory oracleSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getOracleDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //提供SqlSeesion
    @Bean(name = "oracleSessionTemplate")
    @Primary
    public static SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(oracleSqlSessionFactoryBean());
    }
}
