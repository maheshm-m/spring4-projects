package persistence.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages={"entity"})
@EnableJpaRepositories(basePackages="repository")
public class DatabaseConfig {

  @Bean
  public DataSource getDataSource() {
    DataSource dataSource = new EmbeddedDatabaseBuilder()
                                .setName("test")
                                .setType(EmbeddedDatabaseType.H2)
                                .addScript("dbscripts/H2_DDL_Test.sql")
                                .build();
    return dataSource;
  }
    
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager manager = new JpaTransactionManager();
    manager.setEntityManagerFactory(entityManagerFactory());
    manager.afterPropertiesSet();
    return manager;
  }
  
  @Bean
  public EntityManagerFactory entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
      Properties jpaProperties = new Properties();

      jpaProperties.setProperty("hibernate.cache.use_query_cache", "false");
      jpaProperties.setProperty("hibernate.cache.use_second_level_cache", "false");

      HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
      adapter.setGenerateDdl(true);
      adapter.setShowSql(false);
      adapter.setDatabase(Database.H2);

      bean.setPersistenceUnitName("spring4-jpa-project");
      bean.setJpaVendorAdapter(adapter);
      bean.setJpaProperties(jpaProperties);
      bean.setPackagesToScan("entity");
      bean.setDataSource(getDataSource());
      bean.afterPropertiesSet();

      return bean.getObject();

  }  
  
}

