package TVWireHouse;

import TVWireHouse.Entities.TV;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static String ALL_TVS_URL = "http://localhost:8080/admin/tvs";
    public static String FIND_BY_ID_URL = "http://localhost:8080/admin/tvs/find/";
    public static String ADD_NEW_TV_URL = "http://localhost:8080/admin/tvs/new";
    public static String DELETE_TV_URL = "http://localhost:8080/admin/tvs/delete/";
    public static String EDIT_TV_URL = "http://localhost:8080/admin/tvs/edit";

    static final int FIRST_KEY = 1, SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5,
    SIXTH_KEY = 6;
    public static void menu(){
        System.out.println("1)Get all tvs");
        System.out.println("2)Get tv by id");
        System.out.println("3)Add tv");
        System.out.println("4)Delete tv by id");
        System.out.println("5)Edit tv");
        System.out.println("6)Exit");
    }

    static int checkForIntegerInput(Scanner input){
        System.out.println("-> ");

        while(!input.hasNextInt()){
            System.out.println("This is not a number.Try again.");
            input.nextLine();
        }
        return input.nextInt();
    }

    public static ResponseEntity<List> allTVS(RestTemplate restTemplate){
        return restTemplate.getForEntity(ALL_TVS_URL, List.class);
    }

    public static ResponseEntity<TV> getTVById(int id, RestTemplate restTemplate){
        return restTemplate.getForEntity(FIND_BY_ID_URL + id, TV.class);
    }

    public static ResponseEntity<TV> addTV(TV tv, RestTemplate restTemplate){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TV> requestEntity = new HttpEntity<>(tv, headers);

        return restTemplate.postForEntity(ADD_NEW_TV_URL, requestEntity, TV.class);
    }

    public static void deleteTV(int id, RestTemplate restTemplate){
        restTemplate.delete(DELETE_TV_URL + id);
    }

    public static void editTV(TV tv, RestTemplate restTemplate){
        restTemplate.put(EDIT_TV_URL, tv);
    }

    public static void main(String[] args){
        boolean flag = true;
        int function;
        Scanner input = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("Admin", "12345"));

        while (flag){
            menu();
            function = checkForIntegerInput(input);
            switch (function){
                case FIRST_KEY -> {
                    List tvs = allTVS(restTemplate).getBody();
                    for (Object tv: tvs) {
                        System.out.println(tv);
                    }
                }
                case SECOND_KEY -> {
                    System.out.println(getTVById(1000, restTemplate));
                }
                case THIRD_KEY -> {
                    addTV(new TV("1","2","3", "4", 5000, 87867), restTemplate);
                }
                case FOURTH_KEY -> {
                    deleteTV(2, restTemplate);
                }
                case FIFTH_KEY -> {

                }
                case SIXTH_KEY -> flag = false;
                default -> System.out.println("Try again.");
            }
        }
    }
}
