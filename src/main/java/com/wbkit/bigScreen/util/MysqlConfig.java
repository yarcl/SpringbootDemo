package com.wbkit.bigScreen.util;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Created by Administrator on 2018/1/12.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.wbkit.bigScreen.dao.mysql"}, sqlSessionTemplateRef ="mysqlSessionTemplate")
@AutoConfigureAfter(OracleDataSourceConfig.class)
public class MysqlConfig {

    private static PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(true);

    @Bean(name="mysqlDatasource")
    public static DataSource dataSource1() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(propertiesConfiguration.getMysqlUrl());
        dataSource.setUsername(propertiesConfiguration.getMysqlUsername());
        dataSource.setPassword(propertiesConfiguration.getMysqlPassword());
        dataSource.setDriverClassName(propertiesConfiguration.getMysqlDriverClassName());
        return dataSource;
    }

    // 事物管理器
    @Bean(name = "mysqlTransManager")
    public static PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource1());
    }

    //提供SqlSeesion
    @Bean(name = "mysqlSessionFactory")
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
