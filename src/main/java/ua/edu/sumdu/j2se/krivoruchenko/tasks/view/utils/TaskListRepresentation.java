package ua.edu.sumdu.j2se.krivoruchenko.tasks.view.utils;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;



public class TaskListRepresentation {

    public static void representation(AbstractTaskList taskList){
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.print(i + ". ");
            System.out.println(taskList.getTask(i - 1));
        }
    }
}

