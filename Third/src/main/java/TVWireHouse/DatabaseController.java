package TVWireHouse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

import static TVWireHouse.Main.FIRST_KEY;
import static TVWireHouse.Main.SECOND_KEY;
import static TVWireHouse.Main.THIRD_KEY;
import static TVWireHouse.Main.FOURTH_KEY;
import static TVWireHouse.Main.FIFTH_KEY;
import static TVWireHouse.Main.SIXTH_KEY;
import static TVWireHouse.Main.checkForIntegerInput;
import static TVWireHouse.Main.inputData;

public class DatabaseController {
    private SessionFactory session;

    public DatabaseController(){}

    @PostConstruct
    public void databaseInit(){
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("show_sql", "false")
                .setProperty("hibernate.format_sql", "false")
                .setProperty("hibernate.use_sql_comments", "false")
                .setProperty("connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.format_sql", "false")
                .setProperty("hibernate.use_sql_comments", "false")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.generate_statistics", "false");
        configuration.addAnnotatedClass(TV.class);
        StandardServiceRegistryBuilder builder = new
                StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        this.session = configuration.buildSessionFactory(builder.build());
    }

    public void findById() {
        Scanner input = new Scanner(System.in);
        int id;

        System.out.println("Input id of the tv for searching");
        id = checkForIntegerInput(input, "id");

        Session getIdSession = this.session.openSession();
        Transaction transaction = getIdSession.beginTransaction();
        TV tv = getIdSession.get(TV.class, id);
        if(tv == null){
            System.out.println("This tv does not exist in table");
            return;
        }
        System.out.println("Search result: " + tv);

        transaction.commit();
        getIdSession.close();
    }

    public void add() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input new TV data");
        TV tvToAdd = new TV(
                inputData(input, "company"),
                inputData(input, "model"),
                inputData(input, "screen type"),
                inputData(input, "resolution"),
                checkForIntegerInput(input, "cost"),
                checkForIntegerInput(input, "diagonal")
        );

        Session addSession = this.session.openSession();
        Transaction transaction = addSession.beginTransaction();
        addSession.save(tvToAdd);

        transaction.commit();
        addSession.close();
    }

    public void update() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        int function;
        int id;

        System.out.println("Input id of the tv to update");
        id = checkForIntegerInput(input, "id");

        Session updateSession = this.session.openSession();
        Transaction transaction = updateSession.beginTransaction();
        TV tvToUpdate = updateSession.get(TV.class, id);
        if(tvToUpdate == null){
            System.out.println("This tv does not exist in table");
            return;
        }

        while (flag){
            System.out.println("--------------------------------");
            System.out.println("| Choose the field to modify");
            System.out.println("| 1) Company");
            System.out.println("| 2) Model");
            System.out.println("| 3) Screen type");
            System.out.println("| 4) Resolution");
            System.out.println("| 5) Cost");
            System.out.println("| 6) Diagonal");
            System.out.println("--------------------------------");
            function = checkForIntegerInput(input, "number");

            System.out.println("Input new value for field");
            switch (function){
                case FIRST_KEY -> {
                    tvToUpdate.setCompany(inputData(input, "company"));
                    flag = false;
                }
                case SECOND_KEY -> {
                    tvToUpdate.setModel(inputData(input, "model"));
                    flag = false;
                }
                case THIRD_KEY -> {
                    tvToUpdate.setScreenType(inputData(input, "screen type"));
                    flag = false;
                }
                case FOURTH_KEY -> {
                    tvToUpdate.setResolution(inputData(input, "resolution"));
                    flag = false;
                }
                case FIFTH_KEY -> {
                    tvToUpdate.setCost(checkForIntegerInput(input, "cost"));
                    flag = false;
                }
                case SIXTH_KEY -> {
                    tvToUpdate.setDiagonal(checkForIntegerInput(input, "diagonal"));
                    flag = false;
                }
                default -> System.out.println("Wrong input. Try again.");
            }
        }
        updateSession.update(tvToUpdate);

        transaction.commit();
        updateSession.close();
    }

    public void delete() {
        Scanner input = new Scanner(System.in);
        int id;

        System.out.println("Input id of the tv to delete");
        id = checkForIntegerInput(input, "id");

        Session deleteSession = this.session.openSession();
        Transaction transaction = deleteSession.beginTransaction();
        TV tvToDelete = deleteSession.get(TV.class, id);
        if(tvToDelete == null){
            System.out.println("This tv does not exist in table");
            return;
        }
        deleteSession.delete(tvToDelete);

        transaction.commit();
        deleteSession.close();
    }

    public void showAllTV(){
        List<TV> tvList = (List<TV>)  this.session.openSession().createQuery("From TV ").list();
        System.out.println("--------------------------------");
        for (TV tv: tvList) {
            System.out.println(tv);
        }
        System.out.println("--------------------------------");
    }
}