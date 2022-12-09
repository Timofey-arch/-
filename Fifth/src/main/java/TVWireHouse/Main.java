package TVWireHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "TVWireHouse", exclude = {DataSourceAutoConfiguration.class })
public class Main{
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
