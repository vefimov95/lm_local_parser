package leroymerlin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * Configurations that apply to the entire application
 *
 * @author Denis Yarovoy (dyarovoy@bostonsd.ru)
 * Created on December 5, 2019
 */

@Configuration
@EnableTransactionManagement
@EnableAsync
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"leroymerlin.domain"}
)
public class AppConfig {

    @Autowired
    private Environment environment;

    /**
     * Customization Data Source
     * the storage connection parameters are extracted from the application.properties
     *
     * @return {@link DataSource}
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     *  Customization Transaction Manager with using Entity Manager Factory and Data Source
     *
     * @param entityManagerFactory - instance of entity {@link EntityManagerFactory} for Transaction Manager
     * @return configured instance  of entity {@link PlatformTransactionManager}
     */
    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager dbTransactionManager(@Qualifier("entityManagerFactory")
                                                                       EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    /**
     * Customization Entity Manager Factory with Data Source and parameters are extracted from the application.properties
     *
     * @param builder instance of entity {@link EntityManagerFactoryBuilder} for Entity Manager Factory
     * @return configured instance  of entity {@link LocalContainerEntityManagerFactoryBean}
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        return builder
                        .dataSource(dataSource())
                        .packages("leroymerlin.domain")
                        .properties(properties)
                        .build();
    }

}
