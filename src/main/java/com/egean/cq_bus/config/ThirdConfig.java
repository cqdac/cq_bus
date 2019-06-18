package com.egean.cq_bus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryThird",
        transactionManagerRef="transactionManagerThird",
        basePackages= { "com.egean.cq_bus.BusInOut.dao" }) //设置Repository所在位置
public class ThirdConfig {

    @Autowired @Qualifier("thirdDataSource")
    private DataSource thirdDataSource;

    @Value("${spring.jpa.hibernate.third-dialect}")
    // 获取对应的数据库方言
    private String thirdDialect;

    @Bean(name = "entityManagerThird")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryThird")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(thirdDataSource)
                .properties(getVendorProperties(thirdDataSource))
                //设置实体类所在位置
                .packages("com.egean.cq_bus.BusInOut.domain")
                .persistenceUnit("thirdPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        Map<String,String> map = new HashMap<>();
        // 设置对应的数据库方言
        map.put("hibernate.dialect",thirdDialect);
        jpaProperties.setProperties(map);
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerThird")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
