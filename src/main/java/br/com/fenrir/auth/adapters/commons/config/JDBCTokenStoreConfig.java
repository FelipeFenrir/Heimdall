/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.commons.config;

import br.com.fenrir.app.config.ProfileUtils;
import br.com.fenrir.multitenant.utils.MultiTenantConstants;
import br.com.fenrir.commons.utils.enums.EnumInfraProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * JDBCTokenStoreConfig class. Configures data source for tokens.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories
//@RefreshScope
public class JDBCTokenStoreConfig {

    @Autowired
    private ProfileUtils profileUtil;

    /**
     * Creates the default "master" datasource {@link DataSource}.
     *
     * @return {@link DataSource}
     */
    @Bean
//    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
//    @RefreshScope
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Method to configure new TokenStore DataSource.
     *
     * @return {@link TokenStore}
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    /**
     * Configures the Hibernate JPA service with multi-tenant support enabled.
     *
     * @param builder {@link EntityManagerFactoryBuilder}
     * @return LocalContainerEntityManagerFactoryBean
     */
//    @PersistenceContext
//    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> props = new HashMap<>();

        List<String> profiles = profileUtil.getActiveProfiles();
        profiles.forEach((p) -> {
            if (p.equals(EnumInfraProfile.NATIVE.getName())
                    || p.equals(EnumInfraProfile.DEV.getName())
                    || p.equals(EnumInfraProfile.STAGING.getName())) {
                props.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
                props.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
            }
        });

        props.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);

        DataSource dsDefault = dataSource();

        LocalContainerEntityManagerFactoryBean result = builder.dataSource(dsDefault)
                .persistenceUnit(MultiTenantConstants.PERSISTENCE_UNIT_NAME)
                .properties(props)
                .packages("br.com.fenrir").build();
        result.afterPropertiesSet();

        return result;
    }
//    /**
//     * Configures the Hibernate JPA service with multi-tenant support enabled.
//     *
//     * @return LocalContainerEntityManagerFactoryBean
//     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        //vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("br.com.fenrir");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        return factory;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
}
