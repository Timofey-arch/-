package TVWireHouse;

import TVWireHouse.Entities.TV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static String ALL_TVS_URL = "http://localhost:8080/admin/tvs";
    public static String FIND_BY_ID_URL = "http://localhost:8080/admin/tvs/find/";
    public static String ADD_NEW_TV_URL = "http://localhost:8080/admin/tvs/new";
    public static String DELETE_TV_URL = "http://localhost:8080/admin/tvs/delete/";
    public static String EDIT_TV_URL = "http://localhost:8080/admin/tvs/edit/";

    static final int FIRST_KEY = 1, SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5,
    SIXTH_KEY = 6;

    public static void menu(){
        System.out.println("#####################");
        System.out.println("# 1)Get all tvs     #");
        System.out.println("# 2)Get tv by id    #");
        System.out.println("# 3)Add tv          #");
        System.out.println("# 4)Delete tv by id #");
        System.out.println("# 5)Edit tv         #");
        System.out.println("# 6)Exit            #");
        System.out.println("#####################");
    }

    static int checkForIntegerInput(Scanner input, String field){
        System.out.println("Input " + field);

        while(!input.hasNextInt()){
            System.out.println("This is not a number.Try again.");
            input.nextLine();
        }
        return input.nextInt();
    }

    public static String inputData(Scanner input, String field){
        System.out.println("Input " + field);
        return input.next();
    }

    public static void addTV(TV tv, RestTemplate restTemplate){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TV> requestEntity = new HttpEntity<>(tv, headers);
        restTemplate.postForEntity(ADD_NEW_TV_URL, requestEntity, TV.class);
    }

    public static void main(String[] args){
        boolean flag = true;
        int function;
        Scanner input = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("Admin", "12345"));

        while (flag){
            menu();
            function = checkForIntegerInput(input, "number of function");
            switch (function){
                case FIRST_KEY -> {
                    List tvs = restTemplate.getForEntity(ALL_TVS_URL, List.class).getBody();
                    System.out.println("Result:");
                    for (Object tv: tvs) {
                        System.out.println("#" + tv);
                    }
                }
                case SECOND_KEY -> {
                    try {
                        int id = checkForIntegerInput(input, "id");
                        System.out.println("Result: " + restTemplate.getForEntity(FIND_BY_ID_URL + id, TV.class)
                                .getBody());
                    }catch (HttpClientErrorException httpClientErrorException){
                        System.out.println("### Get error. Object with this id is not exist. ###");
                    }
                }
                case THIRD_KEY -> {
                    try {
                        addTV(new TV(
                                inputData(input, "company"),
                                inputData(input, "model"),
                                inputData(input, "screen type"),
                                inputData(input, "resolution"),
                                checkForIntegerInput(input, "cost"),
                                checkForIntegerInput(input, "diagonal")), restTemplate);
                    }catch (HttpServerErrorException httpServerErrorException){
                        System.out.println("### Add error. Please make sure that cost is 100$ or above and diagonal " +
                                "is 10 and above. ###");
                    }
                }
                case FOURTH_KEY -> {
                    try {
                        int id = checkForIntegerInput(input, "id");
                        restTemplate.delete(DELETE_TV_URL + id);
                    }catch (HttpClientErrorException httpClientErrorException){
                        System.out.println("### Delete error. Object with this id is not exist. ####");
                    }
                }
                case FIFTH_KEY -> {
                    System.out.println("Input id of the tv to change his data");
                    int id = checkForIntegerInput(input, "id");

                    try {
                        restTemplate.getForEntity(FIND_BY_ID_URL + id, TV.class);
                    }catch (HttpClientErrorException httpClientErrorException){
                        System.out.println("### Edit error. Object you are trying to change with this" +
                                " id is not exist. ###");
                        break;
                    }

                    System.out.println("Input new data for tv");
                    TV tv = new TV(
                            inputData(input, "company"),
                            inputData(input, "model"),
                            inputData(input, "screen type"),
                            inputData(input, "resolution"),
                            checkForIntegerInput(input, "cost"),
                            checkForIntegerInput(input, "diagonal"));
                    tv.setId(id);
                    try {
                        restTemplate.put(EDIT_TV_URL + tv.getId(), tv);
                    }catch (HttpServerErrorException httpServerErrorException){
                        System.out.println("### Edit error. Please make sure that cost is 100$ or above and diagonal " +
                                "is 10 and above. ###");
                    }
                }
                case SIXTH_KEY -> flag = false;
                default -> System.out.println("Try again.");
            }
        }
    }
}
