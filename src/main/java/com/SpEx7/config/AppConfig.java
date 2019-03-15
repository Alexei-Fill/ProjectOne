package com.SpEx7.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScans(value = {@ComponentScan(basePackages = "com.SpEx7.DAO"), @ComponentScan(basePackages = "com.SpEx7.service")})
public class AppConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties props = new Properties();
        props.put(SHOW_SQL, "true");
        props.put(DIALECT, "org.hibernate.dialect.Oracle10gDialect");
//        props.put(DIALECT, "org.hibernate.dialect.MySQLDialect");
        localSessionFactoryBean.setDataSource(hibernateDataSource());
        localSessionFactoryBean.setPackagesToScan("com.SpEx7.entity");
        localSessionFactoryBean.setHibernateProperties(props);
        return localSessionFactoryBean;
    }

    @Bean
    public DataSource hibernateDataSource(){
        BasicDataSource  dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/newsbd");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}
