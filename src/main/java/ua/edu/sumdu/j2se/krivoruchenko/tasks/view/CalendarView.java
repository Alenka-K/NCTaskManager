package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.Tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;


public class CalendarView implements View{
     Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        System.out.print("Enter the start time of the period (uuuu-MM-dd HH:mm):");
        String startTime = MainView.checkFormatOfData(in);

        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.print("Enter the end time of the period (yyyy-MM-dd HH:mm):");
        String endTime = MainView.checkFormatOfData(in);

        LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        SortedMap<LocalDateTime, Set<Task>> calendarTaskView = Tasks.calendar(taskList, start, end);
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : calendarTaskView.entrySet()) {
            System.out.println(entry.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ": " +
                    Arrays.toString(entry.getValue().stream().map(Task::getTitle).toArray(String[]::new)));
        }
        System.out.println("If you want to back in main menu - enter any number or letter (аor example '1' or 'a' )");
        in.nextLine();

        return typeAction.MAIN_MENU_ACTION;
    }
}
