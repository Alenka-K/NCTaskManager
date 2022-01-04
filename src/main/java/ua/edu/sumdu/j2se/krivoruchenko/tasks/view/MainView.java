package ua.edu.sumdu.j2se.krivoruchenko.tasks.view;

import ua.edu.sumdu.j2se.krivoruchenko.tasks.controller.typeAction;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.krivoruchenko.tasks.model.Task;
import java.util.Scanner;



public class MainView implements View{
    Scanner in = new Scanner(System.in);


    @Override
    public typeAction printInfo(AbstractTaskList taskList) {

        System.out.println("_________Task Manager________");
        for (Task task: taskList) {
            System.out.println(task);
        }
        System.out.println("_____________________________");
        System.out.println("Choose the action: ");
        System.out.println("1. View calendar for time period ");
        System.out.println("2. Add new task to your task list ");
        System.out.println("3. Delete task ");
        System.out.println("4. Edit task ");
        System.out.println("5. Exit ");
        System.out.print("Enter your choice: " );
        int choose = checkForNumber(1, 5, in);
        return switch (choose) {
            case 1 -> typeAction.CALENDAR_ACTION;
            case 2 -> typeAction.ADD_TASK_ACTION;
            case 3 -> typeAction.DELETE_TASK_ACTION;
            case 4 -> typeAction.EDIT_TASK_ACTION;
            case 5 -> typeAction.EXIT_ACTION;
            default -> typeAction.EXIT_ACTION;
        };
    }

    // Метод, який перевіряє введене значення на відповідність числу у заданому діапазоні
    public static int checkForNumber(int min, int max, Scanner scanner){

        while(!scanner.hasNextInt()) {
            System.out.print("Your input is incorrect! Enter number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        while(!(number >= min && number <= max)) {
            System.out.println("Number must be from "+ min + " to " + max + ".");
            System.out.print("Please, enter the correct number: ");
            number = scanner.nextInt();
        }

        return number;
    }
    //Метод, який перевіряє відповідність дати яка введена користувачем до формату дати додатку
    public static String checkFormatOfData(Scanner scanner){

        String data = scanner.nextLine();
        while(!(data.matches("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s(0?[1-9]|1[0-9]|2[0-4]):(0{2}|0?[1-9]|[12345][0-9])$"))) {
            System.out.print("Incorrect format. Please, enter the correct date: ");
            data = scanner.nextLine();
        }

        return data;
    }

}
