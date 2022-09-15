package First;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Класс, созданный для реализации внедрения зависимости
// Аннотация для создания bean и помещения его в контекст приложения
@Component("theMostExpensiveSDCard")
public class SDcard {
    // Внедрение зависимостей через аннотацию @Value
    @Value("${theMostExpensiveSDCard.name}")
    private String name;
    @Value("${theMostExpensiveSDCard.memorySize}")
    private Integer memorySize;

    public SDcard(){

    }

    // Инит и дестрой методы
    @PostConstruct
    public void init(){
        System.out.println("SD CARD INIT");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("SD CARD DESTROY");
    }

    public SDcard(String name, Integer memorySize){
        this.name = name;
        this.memorySize = memorySize;
    }

    public String getName() {
        return name;
    }

    public Integer getMemorySize() {
        return memorySize;
    }
}
