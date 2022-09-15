package First;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("First")
@PropertySource("classpath:theMostExpensiveDevices.properties")
public class SpringConfig {
    @Bean
    public AppleMusicPlayer theMostExpensiveMusicPlayer(){
        return AppleMusicPlayer.createAppleMusicPlayer();
    }

    @Bean
    public SDcard theMostExpensiveSDCard(){
        return new SDcard();
    }
}
