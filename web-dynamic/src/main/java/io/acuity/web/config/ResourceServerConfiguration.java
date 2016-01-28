package io.acuity.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author Amit Verma
 *
 */
@Configuration
@EnableTransactionManagement
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter implements TransactionManagementConfigurer {

    /**
     * 
     */
    public ResourceServerConfiguration() {
    }


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer)
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("web-api").tokenStore(tokenStore());
    }
    

    /* (non-Javadoc)
     * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());

    }

    @Bean
    public DataSource dataSource() {
        DataSource tokenDataSource = DataSourceBuilder.create()
            .driverClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
            .type(com.mysql.jdbc.jdbc2.optional.MysqlDataSource.class)
            .url("jdbc:mysql://localhost:3306/oauth?user=root")
            .username("root")
            .password("password")
            .build();
        return tokenDataSource;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**")
        .authenticated()
            .and()
                .httpBasic()
                .realmName("OAuth Server");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("org.hibernate.envers.default_schema","fatca_aud");
        jpaProperties.setProperty("hibernate.listeners.envers.autoRegister","true");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);
        adapter.setShowSql(false);
        adapter.setDatabase(Database.MYSQL);
        
        bean.setPersistenceUnitName("default");
        bean.setJpaVendorAdapter(adapter);
        bean.setJpaProperties(jpaProperties);
        bean.setPackagesToScan("uk.gov.hmrc.fatca.persistence.entity.cleansed");
        bean.setDataSource(dataSource());
        bean.afterPropertiesSet();

        return bean.getObject();

    }
    

}

