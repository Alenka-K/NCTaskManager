package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils.TaskListRepresentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class EditTaskView implements View{
    Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        TaskListRepresentation.representation(taskList);
        System.out.println("What task will you want to edit? Enter index task:");
        int index = MainView.checkForNumber(1, taskList.size(), in);
        in.nextLine();

        System.out.println("What will you want to edit?:\n" +
                "1.Title\n" +
                "2.Activity\n" +
                "3.Time");
        int  elementToEdit = MainView.checkForNumber(1, 3, in);
        in.nextLine();

        switch (elementToEdit) {
            case 1 -> {
                System.out.println("Enter new title:");
                String newTitle = in.nextLine();
                taskList.getTask(index - 1).setTitle(newTitle);
            }
            case 2 -> {
                System.out.println("Task is active - enter 1, Task is inactive - enter 0");
                int taskActive = MainView.checkForNumber(0, 1, in);
                if (taskActive == 1) taskList.getTask(index - 1).setActive(true);
                if (taskActive == 0) taskList.getTask(index - 1).setActive(false);
            }
            case 3 -> {
                boolean taskRepeated = taskList.getTask(index - 1).isRepeated();
                if (taskRepeated) {
                    System.out.println("Enter new start time (yyyy-MM-dd HH:mm): ");
                    String newStartTime = MainView.checkFormatOfData(in);
                    System.out.println("Enter new end time (yyyy-MM-dd HH:mm): ");
                    String newEndTime = MainView.checkFormatOfData(in);
                    System.out.println("Enter new interval:");
                    int newInterval = MainView.checkForNumber(1, Integer.MAX_VALUE, in);
                    taskList.getTask(index - 1).setTime(
                            LocalDateTime.parse(newStartTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            LocalDateTime.parse(newEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), newInterval);
                } else {
                    System.out.println("Enter new time (yyyy-MM-dd HH:mm): ");
                    String newTime = MainView.checkFormatOfData(in);
                    taskList.getTask(index - 1).setTime(
                            LocalDateTime.parse(newTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        }
        System.out.println("Task " + index + " was successfully changed! " + taskList.getTask(index-1));

        return typeAction.MAIN_MENU_ACTION;
    }

}
