package ua.edu.sumdu.j2se.krivoruchenko.tasks.controller;


import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.View;



public class CalendarController extends Controller{

    public CalendarController(View view, typeAction actionToShow) {

        super(view, actionToShow);
    }

    @Override
    public typeAction process(AbstractTaskList taskList) {
        return super.process(taskList);
    }
}
