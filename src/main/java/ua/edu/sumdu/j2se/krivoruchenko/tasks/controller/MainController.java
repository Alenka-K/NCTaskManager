package ua.edu.sumdu.j2se.krivoruchenko.tasks.controller;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.*;
import java.util.ArrayList;
import java.util.List;


public class MainController extends Controller {
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();



    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, typeAction.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new AddController(new AddTaskView(), typeAction.ADD_TASK_ACTION));
        controllers.add(new DeleteController(new DeleteTaskView(), typeAction.DELETE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), typeAction.CALENDAR_ACTION));
        controllers.add(new EditController(new EditTaskView(), typeAction.EDIT_TASK_ACTION));

    }
    public typeAction process (AbstractTaskList taskList){
        typeAction action = view.printInfo(taskList);
        for(;;){
            for(Controller controller : controllers) {
                if (controller.canProcess(action)){
                    action = controller.process(this.taskList);
                }
            }
            if (action == typeAction.EXIT_ACTION) {

                break;
            }
        }
        return typeAction.EXIT_ACTION;
    }
}
