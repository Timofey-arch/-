package TVWireHouse;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("TVWireHouse")
public class SpringConfig {
    @Bean
    public DatabaseController Database(){
        return new DatabaseController();
    }
}
