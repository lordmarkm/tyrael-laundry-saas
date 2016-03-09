package com.tyrael.laundry;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.tyrael.laundry.core.service",
        "com.tyrael.laundry.pos.service"
    },
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager",
    repositoryImplementationPostfix = "CustomImpl"
)
public class PersistenceConfig {

    @Autowired
    private Environment env;

    /**
     * This is the primary datasource (used by the app)
     */
    @Bean
    @Primary
    public DataSource primaryDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("spring.datasource.driverClass"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUser(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        //c3p0-specific properties follow
        dataSource.setAcquireIncrement(1);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(5);
        dataSource.setMaxIdleTime(600);

        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) throws PropertyVetoException {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.tyrael.laundry.model.**")
                .persistenceUnit("primary")
                .properties(ImmutableMap.of("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"),
                        "hibernate.dialect", env.getProperty("spring.jpa.database-platform")))
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) throws PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(primaryEntityManagerFactory(builder).getObject());
        return transactionManager;
    }

}
