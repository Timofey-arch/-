import java.util.Objects;

public class Employee extends Person{
    private double wage;
    private String post;

    public Employee(String name, String surname, int age, double wage, String post){
        super(name, surname, age);
        setWage(wage);
        setPost(post);
    }

    public Employee(){
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getSurname(), getAge(), getWage(), getPost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.wage, wage) == 0 && post.equals(employee.post) &&
        getName().equals(employee.getName()) && getSurname().equals(employee.getSurname());
    }

    @Override
    public String toString() {
        return "Сотрудник {"
                + "Имя - " + getName()
                + " ,фамилия - " + getSurname()
                + " ,заработная плата - " + wage
                + " ,должность - " + post
                + '}';
    }

    public void setWage(double wage){
        if(wage > 0){
            this.wage = wage;
        }else{
            this.wage = 19000;
        }
    }
    public void setPost(String post){
        this.post = post;
    }

    public double getWage(){
        return wage;
    }
    public String getPost(){
        return post;
    }
}

