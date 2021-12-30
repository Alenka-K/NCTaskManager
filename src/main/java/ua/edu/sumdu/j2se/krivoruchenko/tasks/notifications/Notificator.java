package ua.edu.sumdu.j2se.krivoruchenko.tasks.notifications;


import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.Tasks;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Notificator extends Thread{
    AbstractTaskList taskList;

    public Notificator(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SortedMap<LocalDateTime, Set<Task>> temp = Tasks.calendar(taskList, LocalDateTime.now(),LocalDateTime.now());
            for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : temp.entrySet()) {
                System.out.println("Tasks: " + entry.getValue() + "must be completed");
            }

        }
    }
}
