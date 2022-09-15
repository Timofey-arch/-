package First;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationWithAnnotationClasses {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        AppleMusicPlayer theMostExpensiveMusicPlayer = context.getBean("theMostExpensiveMusicPlayer",
                AppleMusicPlayer.class);

        theMostExpensiveMusicPlayer.on();
        context.close();
    }
}
