package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.MainController;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.notifications.Notificator;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.MainView;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.view.View;



public class Main {


	public static void main(String[] args) {

		AbstractTaskList taskList = new ArrayTaskList();
		TaskDate taskDate = new TaskDate();
		taskDate.loadingTasks(taskList); // зчитування taskList з файлу (якщо такий є)
		Notificator notificator = new Notificator(taskList);
		notificator.setDaemon(true);
		notificator.start();
		View menuView = new MainView();
		Controller mainController = new MainController(taskList, menuView);
		mainController.process(taskList);
		taskDate.unloadingTasks(taskList); // запис у файл taskList по завершенню роботи додатка
		System.exit(0);


	}
}
