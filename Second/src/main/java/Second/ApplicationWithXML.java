package Second;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationWithXML {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContextForXMLApp.xml");

        AppleMusicPlayer theMostExpensivePlayer = context.getBean("theMostExpensiveMusicPlayer",
                AppleMusicPlayer.class);

        theMostExpensivePlayer.on();

        context.close();
    }
}
