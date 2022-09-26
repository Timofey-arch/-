package TVWireHouse.Config;

import TVWireHouse.DAO.TVDAO;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("TVWireHouse")
public class SpringConfig{
    @Bean
    public HibernateSession hibernateSession(){
        return new HibernateSession();
    }

    @Bean
    public TVDAO tvdao(){
        return new TVDAO();
    }

    @Bean
    public SessionFactory sessionFactory(){
        return hibernateSession().getSessionFactory();
    }
}
