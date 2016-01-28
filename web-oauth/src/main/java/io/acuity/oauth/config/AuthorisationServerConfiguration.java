package io.acuity.oauth.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
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
@EnableAuthorizationServer
public class AuthorisationServerConfiguration extends AuthorizationServerConfigurerAdapter implements TransactionManagementConfigurer {

    /**
     * 
     */
    public AuthorisationServerConfiguration() {
        // TODO Auto-generated constructor stub
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    
        clients.jdbc(dataSource());
    }
    

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
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

    /* (non-Javadoc)
     * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());

    }
}

