import java.util.Objects;

public class Teacher extends Employee{
    private String subject;

    public Teacher(String name, String surname, int age, String subject, double wage, String post){
        super(name, surname, age, wage, post);
        setSubject(subject);
    }

    public Teacher(){
        System.out.println("Создан преподаватель без данных.");
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getSurname(), getAge(), getWage(), getPost(), getSubject());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return subject.equals(teacher.subject) && getName().equals(teacher.getName()) &&
                getSurname().equals(teacher.getSurname());
    }

    @Override
    public String toString() {
        return "Преподаватель {" + "Имя - " + getName() +
                " ,фамилия - " + getSurname() +
                " ,предмет - " + getSubject() +
                "}";
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }
}
