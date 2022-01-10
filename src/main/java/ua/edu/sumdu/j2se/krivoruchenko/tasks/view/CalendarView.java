package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.Tasks;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils.ViewTime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;


public class CalendarView implements View{
     Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        System.out.print("Enter the start time of the period (uuuu-MM-dd HH:mm): ");
        String startTime = MainView.checkFormatOfData(in);

        LocalDateTime start = ViewTime.parseTime(startTime);

        System.out.print("Enter the end time of the period (yyyy-MM-dd HH:mm): ");
        String endTime = MainView.checkFormatOfData(in);
        System.out.println("==========CALENDAR==============");
        LocalDateTime end = ViewTime.parseTime(endTime);

        SortedMap<LocalDateTime, Set<Task>> calendarTaskView = Tasks.calendar(taskList, start, end);
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : calendarTaskView.entrySet()) {
            System.out.println(entry.getKey().format(ViewTime.formatter) + ": " +
                    Arrays.toString(entry.getValue().stream().map(Task::getTitle).toArray(String[]::new)));
        }
        System.out.print("If you want to back in main menu - enter any number or letter (example '1'): ");
        in.nextLine();

        return typeAction.MAIN_MENU_ACTION;
    }
}
