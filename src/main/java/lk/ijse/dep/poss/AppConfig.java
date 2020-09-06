package lk.ijse.dep.poss;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("lk.ijse.dep.poss")
@Configuration
@Import(HibernateConfig.class)
public class AppConfig {
}
