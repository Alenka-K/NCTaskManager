package ua.edu.sumdu.j2se.krivoruchenko.tasks.controller;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.View;



public abstract class Controller {

    protected View view;
    protected typeAction actionToShow;

    public Controller(View view, typeAction actionToShow) {
        this.view = view;
        this.actionToShow = actionToShow;
    }

    public boolean canProcess(typeAction action) {
        return action == actionToShow;
    }

    public typeAction process (AbstractTaskList taskList) {
        return view.printInfo(taskList);
    }

}
