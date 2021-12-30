package ua.edu.sumdu.j2se.krivoruchenko.tasks.controller;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.View;

public class EditController extends Controller{
    public EditController(View view, typeAction actionToShow) {

        super(view, actionToShow);
    }

    @Override
    public typeAction process(AbstractTaskList taskList) {
        return super.process(taskList);
    }
}
