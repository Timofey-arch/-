package TVWireHouse;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("TVWireHouse")
@PropertySource("classpath:dataBase.properties")
public class SpringConfig {
    @Bean
    public TV tv(){
        return new TV();
    }

    @Bean
    public Database TVDataBase(){
        return new Database();
    }
}
