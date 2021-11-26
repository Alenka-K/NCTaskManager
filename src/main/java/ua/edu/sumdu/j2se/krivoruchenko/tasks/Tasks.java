package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {


    // метод що повертає підмножину задач, які заплановані на виконання хоча б раз після часу start і не пізніше ніж end
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException{

        if ((start == null)||(end == null) || tasks == null) {
            throw new IllegalArgumentException("Аргументи не можуть бути null!");
        }else {
            return StreamSupport.stream(tasks.spliterator(), false)
                    .filter(task -> task != null && task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0).collect(Collectors.toList());
        }
    }

    // метод, який будує календар задач на заданий період – таблицю, де кожній даті
    //відповідає множина задач, що мають бути виконані в цей час.
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {

        if ((start == null)||(end == null) || tasks == null) {
            throw new IllegalArgumentException("Аргументи не можуть бути null!");
        }else {

            SortedMap<LocalDateTime, Set<Task>> calendarTask = new TreeMap<>();

            for (Task task : incoming(tasks, start, end)) {
                    LocalDateTime nextDateInCalendar;
                    for (nextDateInCalendar = task.nextTimeAfter(start); nextDateInCalendar.compareTo(end) <= 0; nextDateInCalendar = nextDateInCalendar.plusSeconds(task.getRepeatInterval())) {
                        calendarTask.computeIfAbsent(nextDateInCalendar, k -> new HashSet<>()).add(task);
                    }
                }
            return calendarTask;
        }

    }

}

