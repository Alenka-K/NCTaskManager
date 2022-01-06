package ua.edu.sumdu.j2se.krivoruchenko.tasks.notifications;


import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.Tasks;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;



public class Notificator extends Thread{
    private static final Logger logger = Logger.getLogger(Notificator.class);
    private AbstractTaskList taskList;

    public Notificator(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                sleep(50000);
                LocalDateTime now = LocalDateTime.now();
                SortedMap<LocalDateTime, Set<Task>> temp = Tasks.calendar(taskList, now.plusMinutes(5), now.plusMinutes(6));
                for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : temp.entrySet()) {
                    System.out.println("Tasks: " + entry.getValue().stream().map(Task::getTitle).collect(Collectors.toList()) + " should be completed in 5 minutes");
                }
            } catch (InterruptedException e) {
                logger.error(e.getStackTrace());
            }
        }
    }
}
