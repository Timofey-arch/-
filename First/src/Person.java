import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private int age;
    public static final ArrayList<Person> personList = new ArrayList<>();

    public Person(String name, String surname, int age){
        setName(name);
        setSurname(surname);
        setAge(age);
    }

    public Person(){

    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getSurname(), getAge());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name) && surname.equals(person.surname);
    }

    @Override
    public String toString(){
        return "Человек {" +
                "Имя - " + name +
                " ,фамилия - " + surname +
                " ,возраст - " + age + '}';
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setAge(int age){
        if(age > 0){
            this.age = age;
        }else{
            System.out.println("Возраст не может быть отрицательным или нулевым. Установлено стандартное значение");
            this.age = 20;
        }
    }


    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getAge(){
        return age;
    }
}

