package com.tyrael.laundry;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.tyrael.laundry.reports.service"
    },
    entityManagerFactoryRef = "reportsEntityManagerFactory",
    transactionManagerRef = "reportsTransactionManager",
    repositoryImplementationPostfix = "CustomImpl"
)
public class ReportsPersistenceConfig {

    @Autowired
    private Environment env;

    /**
     * This is the secondary datasource (used for reports)
     * @throws PropertyVetoException 
     */
    @Bean
    public DataSource reportsDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("reports.datasource.driverClass"));
        dataSource.setJdbcUrl(env.getProperty("reports.datasource.url"));
        dataSource.setUser(env.getProperty("reports.datasource.username"));
        dataSource.setPassword(env.getProperty("reports.datasource.password"));

        //c3p0-specific properties follow
        dataSource.setAcquireIncrement(1);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(5);
        dataSource.setMaxIdleTime(600);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean reportsEntityManagerFactory(EntityManagerFactoryBuilder builder) throws PropertyVetoException {
        return builder
                .dataSource(reportsDataSource())
                .packages("com.tyrael.laundry.reports.model")
                .persistenceUnit("reports")
                .properties(ImmutableMap.of("hibernate.hbm2ddl.auto", env.getProperty("reports.datasource.hibernate.ddl-auto"),
                        "hibernate.dialect", env.getProperty("reports.datasource.database-platform")))
                .build();
    }

    @Bean
    public PlatformTransactionManager reportsTransactionManager(EntityManagerFactoryBuilder builder) throws PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(reportsEntityManagerFactory(builder).getObject());
        return transactionManager;
    }

}
