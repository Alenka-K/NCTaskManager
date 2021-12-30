package ua.edu.sumdu.j2se.krivoruchenko.tasks.model;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.ListTypes;

public class TaskListFactory {

    /* метод, що створює об’єкт ArrayTaskList і LinkedTaskList в залежності від параметра, що в нього
    передається
    */

    public static AbstractTaskList createTaskList(ListTypes.types type) {

        if (type == null) return null;
        switch (type) {
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
                return new LinkedTaskList();
        }
        return null;
    }
}
