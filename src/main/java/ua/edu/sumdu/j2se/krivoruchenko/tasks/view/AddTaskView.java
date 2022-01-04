package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;


import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class AddTaskView implements View{
    Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        System.out.print("Enter title: " );// введення назви задачі
        String taskTitle = in.nextLine();

        boolean taskActive = false;// введеня активності задачі
        System.out.print("Task is active? yes - enter '1', no - enter '0': ");
        if(MainView.checkForNumber(0,1, in) == 1){
            taskActive = true;
        }

        System.out.print("Task is repeated? yes - enter '1', no - enter '0': ");// введення повторюванності задачі
        int taskRepeated = MainView.checkForNumber(0,1, in);
        in.nextLine();

        // введеня часу в залежності від того повторюється задача чи ні
        if (taskRepeated == 1) {
            System.out.print("Enter start time (uuuu-MM-dd HH:mm): ");
            String taskStartTime = MainView.checkFormatOfData(in);
            System.out.print("Enter end time (uuuu-MM-dd HH:mm): ");
            String taskEndTime = MainView.checkFormatOfData(in);
            System.out.print("Enter interval: " );
            int taskInterval = MainView.checkForNumber(1, Integer.MAX_VALUE, in);
            Task temp = new Task(taskTitle,
                    LocalDateTime.parse(taskStartTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(taskEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    taskInterval);
            temp.setActive(taskActive);
            taskList.add(temp);
            System.out.println("New task was successfully added! - " + temp + "\n");
        }else {
            System.out.print("Enter time (uuuu-MM-dd HH:mm): ");
            String taskTime = MainView.checkFormatOfData(in);
            Task temp = new Task(taskTitle,
                    LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            temp.setActive(taskActive);
            taskList.add(temp);
            System.out.println("New task was successfully added! - " + temp + "\n");
        }

        return typeAction.MAIN_MENU_ACTION;
    }

}
