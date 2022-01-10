package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils.ViewTime;

import java.util.Scanner;



public class EditTaskView implements View{
    Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        MainView.representation(taskList);
        System.out.print("What task will you want to edit? Enter index task: ");
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
                System.out.print("Enter new title:");
                String newTitle = in.nextLine();
                taskList.getTask(index - 1).setTitle(newTitle);
            }
            case 2 -> {
                System.out.print("Task is active - enter 1, Task is inactive - enter 0: ");
                int taskActive = MainView.checkForNumber(0, 1, in);
                if (taskActive == 1) taskList.getTask(index - 1).setActive(true);
                if (taskActive == 0) taskList.getTask(index - 1).setActive(false);
            }
            case 3 -> {
                boolean taskRepeated = taskList.getTask(index - 1).isRepeated();
                if (taskRepeated) {
                    System.out.print("Enter new start time (yyyy-MM-dd HH:mm): ");
                    String newStartTime = MainView.checkFormatOfData(in);
                    System.out.print("Enter new end time (yyyy-MM-dd HH:mm): ");
                    String newEndTime = MainView.checkFormatOfData(in);
                    System.out.print("Enter new interval: ");
                    int newInterval = MainView.checkForNumber(1, Integer.MAX_VALUE, in);
                    taskList.getTask(index - 1).setTime(
                            ViewTime.parseTime(newStartTime),
                            ViewTime.parseTime(newEndTime), newInterval);
                } else {
                    System.out.print("Enter new time (yyyy-MM-dd HH:mm): ");
                    String newTime = MainView.checkFormatOfData(in);
                    taskList.getTask(index - 1).setTime(
                            ViewTime.parseTime(newTime));
                }
            }
        }
        System.out.println("Task " + index + " was successfully changed! " + taskList.getTask(index-1) + "\n");

        return typeAction.MAIN_MENU_ACTION;
    }

}
