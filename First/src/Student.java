import java.util.Objects;

public class Student extends Person{
    private String numberStudentCard;
    private double scholarship;

    public Student(String name, String surname, int age, String numberStudentCard, double scholarship){
        super(name, surname, age);
        this.numberStudentCard = setNumberStudentCard(numberStudentCard);
        setScholarship(scholarship);
    }

    public Student(){
        System.out.println("Создан студент без данных.");
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getSurname(), getAge(), getNumberStudentCard(), getScholarship());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numberStudentCard.equals(student.numberStudentCard);
    }

    @Override
    public String toString() {
        return "Студент {"
                + "Имя - " + getName()
                + " ,фамилия - " + getSurname()
                + " ,номер зачетки - " + numberStudentCard
                + " ,стипендия - " + scholarship
                + '}';
    }

    public String setNumberStudentCard(String numberStudentCard){
        return this.numberStudentCard = numberStudentCard;
    }
    public void setScholarship(double scholarship){
        if(scholarship > 0){
            this.scholarship = scholarship;
        }else{
            System.out.println("Стипендия не может быть отрицательной или нулевой. Установлено стандартное значение");
            this.scholarship = 3500;
        }
    }

    public String getNumberStudentCard(){
        return numberStudentCard;
    }
    public double getScholarship() {
        return scholarship;
    }
}
