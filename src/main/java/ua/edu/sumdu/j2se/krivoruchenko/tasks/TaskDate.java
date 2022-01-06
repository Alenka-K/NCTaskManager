package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.TaskIO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


//клас для зберігання інформації по задачам
public class TaskDate {

    private static final Logger logger = Logger.getLogger(TaskDate.class);
    private File fileForTasks = new File("data.json");


    // метод, що зчитує задачі з файла
    public void loadingTasks (AbstractTaskList taskList) {

        if (fileForTasks.length() != 0) {
            try (FileReader reader = new FileReader(fileForTasks)) {
                TaskIO.read(taskList, reader);
            } catch (IOException e) {
                logger.error(e.getStackTrace());
            }
        }
    }

    // метод, який записує задачі у файл по завершенню роботи додатка
    public void unloadingTasks (AbstractTaskList taskList) {

        try {
            Files.deleteIfExists(Path.of(String.valueOf(fileForTasks)));
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
        TaskIO.writeText(taskList, new File(String.valueOf(fileForTasks)));
        System.out.println("Task Manager closed");
        try {
            System.in.close();
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }
    }

}
