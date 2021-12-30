package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils.TaskListRepresentation;
import java.util.Scanner;


public class DeleteTaskView implements View{
    Scanner in = new Scanner(System.in);

    @Override
    public typeAction printInfo(AbstractTaskList taskList) {
        TaskListRepresentation.representation(taskList);

        System.out.println("What task will you want to delete? Enter index task:");

        int index = MainView.checkForNumber(1, taskList.size(),in);

        taskList.remove(taskList.getTask(index-1));
        System.out.println("Task with index " + (index)  + " deleted!");

        return typeAction.MAIN_MENU_ACTION;
    }
}
