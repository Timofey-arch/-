package TVWireHouse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class Main {
    public static final int FIRST_KEY = 1,
            SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5, SIXTH_KEY = 6;

    public static void printMenu(){
        System.out.println("--------------------------------");
        System.out.println("| 1) Add TV to database");
        System.out.println("| 2) Delete TV from database");
        System.out.println("| 3) Change data of TV in database");
        System.out.println("| 4) Show all objects in database");
        System.out.println("| 5) Search in database");
        System.out.println("| 6) Exit");
        System.out.println("--------------------------------");
    }

    public static int checkForIntegerInput(Scanner input, String field){
        System.out.println("Input " + field);

        while(!input.hasNextInt()){
            System.out.println("It is not a number. Try again");
            input.nextLine();
        }
        return input.nextInt();
    }

    public static String inputData(Scanner input, String field){
        System.out.println("Input " + field);
        return input.next();
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DatabaseController TVDatabase = context.getBean(DatabaseController.class);
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int function;

        while (flag){
            printMenu();
            function = checkForIntegerInput(input, "number");

            switch (function){
                case FIRST_KEY -> TVDatabase.add();
                case SECOND_KEY -> TVDatabase.delete();
                case THIRD_KEY -> TVDatabase.update();
                case FOURTH_KEY -> TVDatabase.showAllTV();
                case FIFTH_KEY -> TVDatabase.findById();
                case SIXTH_KEY -> flag = false;
                default -> System.out.println("Wrong input. Try again");
            }
        }

        context.close();
    }
}
