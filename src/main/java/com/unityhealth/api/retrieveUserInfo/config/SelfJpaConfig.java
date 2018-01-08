package com.unityhealth.api.retrieveUserInfo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by shanefox on 10/10/2016.
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.unityhealth.api.retrieveUserInfo.domain",
    entityManagerFactoryRef = "selfDbClientEntityManager",
    transactionManagerRef = "selfDbClientTransactionManager"
)
@EntityScan(basePackages = "com.unityhealth.api.retrieveUserInfo.domain")
@EnableTransactionManagement
public class SelfJpaConfig {

    @Autowired
    private Environment env;

    @Bean(name = "selfDbClientEntityManager")
    public LocalContainerEntityManagerFactoryBean produceSelfDbClientEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(produceSelfDbClientDataSource());
        em.setPackagesToScan("com.unityhealth.api.retrieveUserInfo.domain");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("selfDbClient.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("selfDbClient.hibernate.dialect"));
        properties.put("hibernate.show_sql", true);
        properties.put("javax.persistence.validation.mode", "NONE");
        properties.put("hibernate.enable_lazy_load_no_trans",true);
          //properties.put("spring.jpa.hibernate.ddl-auto",none
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "selfDbClientDataSource")
    public DataSource produceSelfDbClientDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("selfDbClient.jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("selfDbClient.jdbc.url"));
        dataSource.setUsername(env.getProperty("selfDbClient.jdbc.username"));
        dataSource.setPassword(env.getProperty("selfDbClient.jdbc.password"));
        dataSource.setMaximumPoolSize(env.getProperty("selfDbClient.pool.maxPoolSize", Integer.class));
        return dataSource;
    }

    @Bean(name = "selfDbClientTransactionManager")
    public PlatformTransactionManager produceSelfDbClientTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(produceSelfDbClientEntityManager().getObject());
        return transactionManager;
    }

}
