package TVWireHouse.Config;

import TVWireHouse.DAO.TVDAO;
import TVWireHouse.DAO.UserDAO;
import TVWireHouse.JMS.Consumer;
import TVWireHouse.JMS.Producer;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;

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
    public UserDAO userDAO(){
        return new UserDAO();
    }

    @Bean
    public SessionFactory sessionFactory(){
        return hibernateSession().getSessionFactory();
    }

    @Bean
    public Producer producer(){
        return new Producer();
    }

    @Bean
    public Consumer consumer(){
        return new Consumer();
    }
}
