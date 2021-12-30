package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.MainController;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.utils.TaskIO;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.MainView;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.View;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		AbstractTaskList taskList = new ArrayTaskList();

		File fileForTasks = new File("test.txt");
		if (fileForTasks.length() != 0) {
			try (FileReader reader = new FileReader(fileForTasks)) {
				TaskIO.read(taskList, reader);
			} catch (IOException e) {
				logger.error(e.getStackTrace());
			}
		}

		View menuView = new MainView();
		Controller mainController = new MainController(taskList, menuView);
		mainController.process(taskList);

		try {
			Files.deleteIfExists(Path.of(String.valueOf(fileForTasks)));
		} catch (IOException e) {
			e.getStackTrace();
		}
		TaskIO.writeText(taskList, new File(String.valueOf(fileForTasks)));
		System.out.println("Task Manager closed");
		try {
			System.in.close();
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		System.exit(0);


	}
}
