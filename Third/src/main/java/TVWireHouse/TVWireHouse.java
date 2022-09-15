package TVWireHouse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TVWireHouse {
    public static void printMenu(){
        System.out.println("1) Add TV to database");
        System.out.println("2) Delete TV from database");
        System.out.println("3) Change data of TV in database");
        System.out.println("4) Show all objects in database");
        System.out.println("5) Search in database");
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Database TVDataBase = context.getBean("TVDataBase", Database.class);
        TV tv = context.getBean("tv", TV.class);

        context.close();
    }
}
