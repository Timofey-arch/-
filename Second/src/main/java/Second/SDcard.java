package Second;


// Класс, созданный для реализации внедрения зависимости
public class SDcard {
    private String name;
    private Integer memorySize;

    public SDcard(){

    }

    public SDcard(String name, Integer memorySize){
        this.name = name;
        this.memorySize = memorySize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }
}
