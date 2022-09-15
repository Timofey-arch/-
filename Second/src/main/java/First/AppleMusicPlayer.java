package First;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// Аннотация для создания bean и помещения его в контекст приложения
//@Component("theMostExpensiveMusicPlayer")
public class AppleMusicPlayer implements MusicPlayer {
    // Внедрение зависимостей через аннотацию @Value
    @Value("${theMostExpensiveMusicPlayer.model}")
    private String model;
    @Value("${theMostExpensiveMusicPlayer.memorySize}")
    private Integer memorySize = 32;
    @Value("${theMostExpensiveMusicPlayer.timeOfWork}")
    private Integer timeOfWork = 60;
    private SDcard sdcard;

    private final ArrayList<String> musicList = new ArrayList<>();
    private String currentMusic = "None";
    private boolean power = false;
    private boolean playingMusicState = false;
    private boolean musicPlayerMenu = false;

    static final int FIRST_KEY = 1, SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5, SIXTH_KEY = 6,
    SEVENTH_KEY = 7;

    private AppleMusicPlayer(){
    }

    // Фабричный метод
    public static AppleMusicPlayer createAppleMusicPlayer(){
        return new AppleMusicPlayer();
    }

    // Инит и дестрой методы
    @PostConstruct
    public void init(){
        System.out.println("MUSIC PLAYER INIT");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MUSIC PLAYER DESTROY");
    }

    // Реализация методов интерфейса
    public void on(){
        System.out.println("Плеер запущен! Вас приветствует компания Apple!");
        this.power = true;
        this.musicPlayerButtons();
    }

    public void off(){
        this.power = false;
        System.out.println("Плеер Apple выключен!");
    }

    // Внедрение зависимости через сеттер и аннотацию @Autowired
    @Autowired
    public void setSdcard(SDcard sdcard) {
        this.sdcard = sdcard;
    }

    // Функционал плеера
    /**
     * Function for pause or playing music
     */
    public void pause(){
        if (!Objects.equals(this.currentMusic, "None")) {
            if(this.playingMusicState){
                this.playingMusicState = false;
                System.out.println(this.currentMusic + " приостановлена.");
            }else{
                this.playingMusicState = true;
                System.out.println(this.currentMusic + " запущена.");
            }
        }else{
            System.out.println("Нет запущенной музыки. Сначала выберите музыку для запуска.");
        }
    }

    /**
     * Function for switching to the next music in music list
     */
    public void next(){
        if (Objects.equals(this.currentMusic, "None")) {
            System.out.println("Для начала выберите музыку");
        } else {
            int musicIndex = this.musicList.indexOf(this.currentMusic);

            if(musicIndex == (this.musicList.size() - 1)){
                this.currentMusic = this.musicList.get(0);
                System.out.println("Сейчас играет: " + this.currentMusic);
            }else{
                this.currentMusic = this.musicList.get(musicIndex + 1);
                System.out.println("Сейчас играет: " + this.currentMusic);
            }
        }
    }

    /**
     * Function for switching to the previous music in music list
     */
    public void previous(){
        if (Objects.equals(this.currentMusic, "None")) {
            System.out.println("Для начала выберите музыку");
        } else {
            int musicIndex = this.musicList.indexOf(this.currentMusic);

            if(musicIndex == 0){
                this.currentMusic = this.musicList.get(this.musicList.size() - 1);
                System.out.println("Сейчас играет: " + this.currentMusic);
            }else{
                this.currentMusic = this.musicList.get(musicIndex - 1);
                System.out.println("Сейчас играет: " + this.currentMusic);
            }
        }
    }

    // Геттеры
    public String getModel() {
        return model;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public Integer getTimeOfWork() {
        return timeOfWork;
    }

    public SDcard getSdcard() {
        return sdcard;
    }

    @Override
    public String toString() {
        System.out.println("--------------------------------");
        return "| Характеристики устройства AppleMusicPlayer \n" +
                "| Модель: " + this.getModel() + "\n" +
                "| Объем встроенной памяти: " + this.getMemorySize() + "\n" +
                "| Время автономной работы: " + this.getTimeOfWork() + "\n" +
                "| SD накопитель: " + this.getSdcard().getName() + "\n" +
                "| | Имя карты: " + this.getSdcard().getName() + "\n" +
                "| | Объем карты: " + this.getSdcard().getMemorySize();
    }

    /**
     * Function for showing music list
     */
    public void showMusicList(){
        System.out.println("--------------------------------");
        for (String music: this.musicList) {
            System.out.println("| " + this.musicList.indexOf(music) + ") " + music);
        }
        System.out.println("--------------------------------");
    }

    /**
     * Function for adding music in music list
     * @param music - Music name
     */
    public void addToMusicList(String music){
        this.musicList.add(music);
    }

    /**
     * Function for deleting music from music list
     * @param musicToDelete - Music name
     */
    public void deleteFromMusicList(String musicToDelete){
        if(this.musicList.size() == 0){
            System.out.println("Список музыки пуст. Удалять нечего.");
        }else{
            if(!this.musicList.contains(musicToDelete)){
                System.out.println("Такой музыки нет, введите существующую");
            }else{
                if(Objects.equals(this.currentMusic, musicToDelete)){
                    this.currentMusic = "None";
                }
                this.musicList.remove(musicToDelete);
            }
        }
    }

    /**
     * Function for presenting buttons on music player
     */
    public void musicPlayerButtons(){
        this.power = true;
        Scanner input = new Scanner(System.in);
        int function;

        while (this.power){
            System.out.println("--------------------------------");
            System.out.println("| 1)Меню плеера                |");
            System.out.println("| 2)Выключить плеер            |");
            System.out.println("| 3)Показать характеристики    |");
            System.out.println("--------------------------------");

            function = checkForIntegerInput(input);

            switch (function){
                case FIRST_KEY -> {
                    this.musicPlayerMenu = true;

                    while (this.musicPlayerMenu){
                        this.musicPlayerMenu();
                    }
                }
                case SECOND_KEY -> this.off();
                case THIRD_KEY -> System.out.println(this);
                default -> System.out.println("Такого пункта нет. Повторите попытку.");
            }
        }
    }

    /**
     * Function for presenting digital menu of music player
     */
    public void musicPlayerMenu(){
        Scanner input = new Scanner(System.in);
        int function;

        System.out.println("--------------------------------");
        System.out.println("| 1)Выбрать музыку             |");
        System.out.println("| 2)Добавить музыку            |");
        System.out.println("| 3)Удалить музыку             |");
        System.out.println("| 4)Вернуться к кнопкам плеера |");
        System.out.println("--------------------------------");
        System.out.println("| 5)Предыдущая музыка          |");
        System.out.println("| 6)Пауза                      |");
        System.out.println("| 7)Следущая музыка            |");
        System.out.println("--------------------------------");
        function = checkForIntegerInput(input);
        switch (function){
            case FIRST_KEY -> {
                if(this.musicList.size() != 0){
                    this.showMusicList();
                    int musicNumber;

                    System.out.println("Выберите номер музыки");
                    musicNumber = checkForIntegerInput(input);

                    try {
                        String music = this.musicList.get(musicNumber);

                        // Пытаемся установить музыку из списка существующих на проигрывание
                        if(!this.musicList.contains(music)){
                            System.out.println("Такой музыки нет. Повторите попытку.");
                        }else{
                            this.currentMusic = music;
                            this.playingMusicState = true;
                            System.out.println("Сейчас играет: " + this.currentMusic);
                        }
                    } catch (IndexOutOfBoundsException exception){
                        System.out.println("Введите существующий номер музыки.");
                    }
                } else {
                    System.out.println("Список музыки пуст. Добавьте музыку.");
                }
            }
            case SECOND_KEY -> {
                System.out.println("Введите название музыки для добавления в список");
                String music = input.next();
                this.addToMusicList(music);
            }
            case THIRD_KEY -> {
                if(this.musicList.size() != 0){
                    this.showMusicList();
                    int musicNumber;

                    System.out.println("Выберите номер музыки");
                    musicNumber = checkForIntegerInput(input);

                    try {
                        String music = this.musicList.get(musicNumber);

                        if(!this.musicList.contains(music)){
                            System.out.println("Такой музыки нет. Повторите попытку.");
                        }else{
                            this.deleteFromMusicList(music);
                        }
                    } catch (IndexOutOfBoundsException exception){
                        System.out.println("Введите существующий номер музыки.");
                    }
                } else {
                    System.out.println("Список музыки пуст. Удалять нечего.");
                }
            }
            case FOURTH_KEY -> this.musicPlayerMenu = false;
            case FIFTH_KEY -> this.previous();
            case SIXTH_KEY -> this.pause();
            case SEVENTH_KEY -> this.next();
            default -> System.out.println("Такого пункта нет. Повторите попытку.");
        }
    }

    /**
     * Function for checking int from buffer
     * @param input Global scanner
     * @return int
     */
    static int checkForIntegerInput(Scanner input){
        System.out.println("Введите число");

        while(!input.hasNextInt()){
            System.out.println("Вы ввели не число. Введите число.");
            input.nextLine();
        }
        return input.nextInt();
    }
}
