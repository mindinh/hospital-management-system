package com.udpt.appointments.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.udpt.appointments.repository.write",
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
public class WriteDataSourceConfig {

    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceInitializer writeDataSourceInitializer(
            @Qualifier("writeDataSource") DataSource writeDataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema_write.sql"));

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(writeDataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Bean(name = "writeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(
            @Qualifier("writeDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.udpt.appointments.entity.write");
        emf.setPersistenceUnitName("write");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.hbm2ddl.auto", "none");
        jpaProps.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        emf.setJpaPropertyMap(jpaProps);

        return emf;
    }

    @Bean(name = "writeTransactionManager")
    public PlatformTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
