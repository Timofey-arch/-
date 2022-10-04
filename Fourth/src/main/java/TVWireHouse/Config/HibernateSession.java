package TVWireHouse.Config;

import TVWireHouse.Entities.TV;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import javax.annotation.PostConstruct;

public class HibernateSession {
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init(){
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("show_sql", "false")
                .setProperty("hibernate.format_sql", "false")
                .setProperty("hibernate.use_sql_comments", "false")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.generate_statistics", "false");
        configuration.addAnnotatedClass(TV.class);
        StandardServiceRegistryBuilder builder = new
                StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        this.sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public HibernateSession(){

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
