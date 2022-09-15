import java.util.Scanner;

public class Main {
    static final int FIRST_KEY = 1, SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5;

    /**
     * Function for showing menu on the screen
     */
    static void printMenu(){
        System.out.println("1)Добавить человека в список");
        System.out.println("2)Удалить человека по индексу");
        System.out.println("3)Вывести информацию о всех людях");
        System.out.println("4)Сравнить два элемента на равенство по индексам");
        System.out.println("5)Выход");
    }

    /**
     * Function for checking int from buffer
     * @param input Global scanner
     * @param field String for explaining what user should input
     * @return int
     */
    static int checkForIntegerInput(Scanner input, String field){
        System.out.println("Введите " + field);

        while(!input.hasNextInt()){
            System.out.println("Повторите попытку");
            input.nextLine();
        }
        return input.nextInt();
    }

    /**
     * Function for checking double from buffer
     * @param input Global scanner
     * @param field String for explaining what user should input
     * @return int
     */
    static double checkForDoubleInput(Scanner input, String field){
        System.out.println("Введите " + field);

        while(!input.hasNextDouble() || (input.nextDouble() < 0)){
            System.out.println("Повторите попытку");
            input.nextLine();
        }
        return input.nextDouble();
    }

    /**
     * Function for inputting data
     * @param input Global scanner
     * @param field String for explaining what user should input
     * @return int
     */
    static String inputData(Scanner input, String field){
        System.out.println("Введите " + field);
        return input.next();
    }

    /**
     * Function for adding person to Person class list
     * @param input Global scanner
     */
    static void addPerson(Scanner input){
        int functionFirst, functionSecond;
        boolean flag = true;

        while(flag){
            System.out.println("""
                Выберите кого хотите добавить в список
                1)Человек
                2)Студент
                3)Преподаватель
                4)Сотрудник""");
            functionFirst = checkForIntegerInput(input, "число");
            if (functionFirst < FIRST_KEY || functionFirst > FOURTH_KEY){
                System.out.println("Такого пункта нет. Повторите попытку.");
                return;
            }

            System.out.println("""
                Выберите способ добавления
                1)Пустой
                2)C параметрами""");
            functionSecond = checkForIntegerInput(input, "число");
            if (functionSecond < FIRST_KEY || functionSecond > SECOND_KEY){
                System.out.println("Такого пункта нет. Повторите попытку.");
                return;
            }

            switch (functionFirst){
                case FIRST_KEY -> {
                    switch (functionSecond){
                        case FIRST_KEY -> {
                            Person.personList.add(new Person());
                            flag = false;
                        }
                        case SECOND_KEY -> {
                            Person.personList.add(new Person(inputData(input, "имя"),
                                    inputData(input, "фамилию"), checkForIntegerInput(input, "возраст")));
                            flag = false;
                        }
                    }
                }
                case SECOND_KEY -> {
                    switch (functionSecond){
                        case FIRST_KEY -> {
                            Person.personList.add(new Student());
                            flag = false;
                        }
                        case SECOND_KEY -> {
                            Person.personList.add(new Student(inputData(input, "имя"),
                                    inputData(input, "фамилию"), checkForIntegerInput(input,
                                    "возраст"), inputData(input, "номер зачетки"),
                                    checkForDoubleInput(input, "стипендию")));
                            flag = false;
                        }
                    }
                }
                case THIRD_KEY -> {
                    switch (functionSecond){
                        case FIRST_KEY -> {
                            Person.personList.add(new Teacher());
                            flag = false;
                        }
                        case SECOND_KEY -> {
                            Person.personList.add(new Teacher(inputData(input, "имя"),
                                    inputData(input, "фамилию"), checkForIntegerInput(input,
                                    "возраст(male или female)"), inputData(input,
                                    "предмет"), checkForDoubleInput(input, "зарплату"), inputData(input,
                                    "должность")));
                            flag = false;
                        }
                    }
                }
                case FOURTH_KEY -> {
                    switch (functionSecond){
                        case FIRST_KEY -> {
                            Person.personList.add(new Employee());
                            flag = false;
                        }
                        case SECOND_KEY -> {
                            Person.personList.add(new Employee(inputData(input, "имя"),
                                    inputData(input, "фамилию"), checkForIntegerInput(input,
                                    "возраст"), checkForDoubleInput(input, "зарплату"), inputData(input,
                                    "должность")));
                            flag = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Function for deleting person from Person class list
     * @param input Global scanner
     */
    static void deletePerson(Scanner input){
        int index;

        index = checkForIntegerInput(input, "индекс человека в списке");

        if(index >= Person.personList.size() || index < 0){
            System.out.println("Неверный ввод. Повторите попытку.");
        }else{
            Person.personList.remove(index);
        }
    }

    /**
     * Function for showing Person class list
     */
    static void showPersonListInformation(){
        if (Person.personList.size() == 0){
            System.out.println("Список людей пуст");
        }
        for(Person person: Person.personList){
            System.out.println(person.toString());
        }
    }

    /**
     * Function to equal two objects
     * @param input Global scanner
     */
    static void equalTwoObjects(Scanner input){
        int firstIndex, secondIndex;

        firstIndex = checkForIntegerInput(input, "индекс первого человека для сравнения");

        secondIndex = checkForIntegerInput(input, "индекс второго человека для сравнения");

        if((firstIndex >= Person.personList.size() || firstIndex < 0) ||
                (secondIndex >= Person.personList.size() || secondIndex < 0)){
            System.out.println("Неверный ввод. Повторите попытку.");
        }else{
            if(Person.personList.get(firstIndex).equals(Person.personList.get(secondIndex))) {
                System.out.println("Это один и тот же человек");
            }else{
                System.out.println("Это разные люди");
            }
        }
    }

    /**
     * Main function
     * @param args Standard argument
     */
    public static void main(String[] args) {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int function;

        while (flag){
            printMenu();
            function = checkForIntegerInput(input, "число");

            switch (function){
                case FIRST_KEY -> addPerson(input);
                case SECOND_KEY -> deletePerson(input);
                case THIRD_KEY -> showPersonListInformation();
                case FOURTH_KEY -> equalTwoObjects(input);
                case FIFTH_KEY -> flag = false;
                default -> System.out.println("Такого пункта нет. Повторите попытку.");
            }
        }
    }
}
