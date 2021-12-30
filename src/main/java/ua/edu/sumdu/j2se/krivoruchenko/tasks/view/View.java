package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;


import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;


public interface View {

     typeAction printInfo(AbstractTaskList taskList);
}
