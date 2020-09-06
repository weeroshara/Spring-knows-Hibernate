package lk.ijse.dep.poss;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(ds);
        lsfb.setPackagesToScan("lk.ijse.dep.poss.entity");
        lsfb.setHibernateProperties(hibernateProperties());

        return lsfb;

    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("password");
        ds.setUrl("jdbc:mysql://localhost:3306/hibernateLayPoss");

        return ds;
    }

    public Properties hibernateProperties(){
        Properties properties = new Properties();

        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.format_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");

        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sf){
        return new HibernateTransactionManager(sf);
    }
}
