package ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ViewTime {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // метод, який перетворює введену строку в обєкт LocalDateTime згідно заданого формату
    public static LocalDateTime parseTime(String time){
        return LocalDateTime.parse(time, formatter);
    }

}
