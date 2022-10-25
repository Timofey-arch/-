package TVWireHouse.Config;

import TVWireHouse.DAO.TVDAO;
import TVWireHouse.DAO.UserDAO;
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
    public UserDAO userDAO(){
        return new UserDAO();
    }

    @Bean
    public SessionFactory sessionFactory(){
        return hibernateSession().getSessionFactory();
    }
}
