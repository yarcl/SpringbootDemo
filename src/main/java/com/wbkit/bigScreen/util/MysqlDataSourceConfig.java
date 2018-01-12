package com.wbkit.bigScreen.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2018/1/12.
 */
@Configuration
@MapperScan(basePackages = {"com.wbkit.bigScreen.dao.mysql"}, sqlSessionTemplateRef ="mysqlSessionTemplate")
public class MysqlDataSourceConfig {

    //static PropertiesConfiguration propertiesConfiguration = PropertiesConfiguration.getPropertiesInstance();

    @Bean(name="mysqlDataSource")     //声明其为Bean实例
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    public static DataSource getMysqlDataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        /*dataSource.setUrl(propertiesConfiguration.getMysqlUrl());
        dataSource.setUsername(propertiesConfiguration.getMysqlUsername());
        dataSource.setPassword(propertiesConfiguration.getMysqlPassword());
        dataSource.setDriverClassName(propertiesConfiguration.getMysqlDriverClassName());*/
        return dataSource;
    }

    // 事物管理器
    @Bean(name = "mysqlTransManager")
    @Transactional("mySqlTransactionManager")
    public static PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getMysqlDataSource());
    }

    //提供SqlSeesion
    @Bean(name = "mysqlSessionFactory")
    public static SqlSessionFactory mysqlSqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getMysqlDataSource());
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
