package Second;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AppleMusicPlayer implements MusicPlayer{
    private final String model;
    private final Integer memorySize;
    private final Integer timeOfWork;
    private final SDcard sdcard;

    private final ArrayList<String> musicList = new ArrayList<>();
    private String playingMusic = "None";
    private boolean musicPlayerState = false;
    private boolean playingMusicState = false;
    private boolean musicPlayerMenu = false;

    static final int FIRST_KEY = 1, SECOND_KEY = 2, THIRD_KEY = 3, FOURTH_KEY = 4, FIFTH_KEY = 5, SIXTH_KEY = 6,
            SEVENTH_KEY = 7;

    public AppleMusicPlayer(String model, Integer memorySize, Integer timeOfWork, SDcard sdcard){
        this.model = model;
        this.memorySize = memorySize;
        this.timeOfWork = timeOfWork;
        this.sdcard = sdcard;
    }

    // Реализация методов интерфейса
    public void on(){
        System.out.println("Плеер запущен! Вас приветствует компания Apple!");
        this.musicPlayerState = true;
        this.musicPlayerButtons();
    }

    public void off(){
        this.musicPlayerState = false;
        System.out.println("Плеер Apple выключен!");
    }

    // Функционал плеера
    public void pause(){
        if (!Objects.equals(this.playingMusic, "None")) {
            if(this.playingMusicState){
                this.playingMusicState = false;
                System.out.println(this.playingMusic + " приостановлена.");
            }else{
                this.playingMusicState = true;
                System.out.println(this.playingMusic + " запущена.");
            }
        }else{
            System.out.println("Нет запущенной музыки. Сначала выберите музыку для запуска.");
        }
    }

    public void next(){
        if (Objects.equals(this.playingMusic, "None")) {
            System.out.println("Для начала выберите музыку");
        } else {
            int musicIndex = this.musicList.indexOf(this.playingMusic);

            if(musicIndex == (this.musicList.size() - 1)){
                this.playingMusic = this.musicList.get(0);
                System.out.println("Сейчас играет: " + this.playingMusic);
            }else{
                this.playingMusic = this.musicList.get(musicIndex + 1);
                System.out.println("Сейчас играет: " + this.playingMusic);
            }
        }
    }

    public void previous(){
        if (Objects.equals(this.playingMusic, "None")) {
            System.out.println("Для начала выберите музыку");
        } else {
            int musicIndex = this.musicList.indexOf(this.playingMusic);

            if(musicIndex == 0){
                this.playingMusic = this.musicList.get(this.musicList.size() - 1);
                System.out.println("Сейчас играет: " + this.playingMusic);
            }else{
                this.playingMusic = this.musicList.get(musicIndex - 1);
                System.out.println("Сейчас играет: " + this.playingMusic);
            }
        }
    }

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

    public void showMusicList(){
        System.out.println("--------------------------------");
        for (String music: this.musicList) {
            System.out.println("| " + this.musicList.indexOf(music) + ") " + music);
        }
        System.out.println("--------------------------------");
    }

    public void addToMusicList(String music){
        this.musicList.add(music);
    }

    public void deleteFromMusicList(String musicToDelete){
        if(this.musicList.size() == 0){
            System.out.println("Список музыки пуст. Удалять нечего.");
        }else{
            if(!this.musicList.contains(musicToDelete)){
                System.out.println("Такой музыки нет, введите существующую");
            }else{
                if(Objects.equals(this.playingMusic, musicToDelete)){
                    this.playingMusic = "None";
                }
                this.musicList.remove(musicToDelete);
            }
        }
    }

    public void musicPlayerButtons(){
        this.musicPlayerState = true;
        Scanner input = new Scanner(System.in);
        int function;

        while (this.musicPlayerState){
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
                            this.playingMusic = music;
                            this.playingMusicState = true;
                            System.out.println("Сейчас играет: " + this.playingMusic);
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

    static int checkForIntegerInput(Scanner input){
        System.out.println("Введите число");

        while(!input.hasNextInt()){
            System.out.println("Вы ввели не число. Введите число.");
            input.nextLine();
        }
        return input.nextInt();
    }
}
