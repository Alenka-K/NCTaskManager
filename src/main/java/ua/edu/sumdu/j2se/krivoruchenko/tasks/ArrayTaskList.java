package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] array = new Task[10];

    // метод, що додає до списку вказану задачу
    public void add (Task task) {
        if (!Arrays.asList(array).contains(null)) {
            array = Arrays.copyOf(array, array.length + 10);
        }
        array[Arrays.asList(array).indexOf(null)] = task;
    }


    /* метод, що видаляє задачу зі списку і повертає істину, якщо
     така задача була у списку. Якщо у списку було декілька таких задач, необхідно видалити одну
     будь-яку
    */
    public boolean remove (Task task) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(task)) {
                if (i != array.length - 1) { // якщо задача яку потрібно видалити не в кінці списку
                    System.arraycopy(array, i + 1, array, i, (array.length - 1) - i);
                    array[array.length-1] = null;
                }else {
                    array[i] = null; // якщо в кінці, замінюємо на null
                }
                return true;
            }
        }
        return false;
    }

    // метод, що повертає кількість задач у списку
    public int size () {
        int size = 0;
        for (Task task : array) {
            if (task != null) {
                size += 1;
            }
        }
        return size;
    }

    /* метод, що повертає задачу, яка знаходиться на вказаному місці у
    списку, перша задача має індекс 0.
     */
    public Task getTask (int index) throws IndexOutOfBoundsException{
        if (index > array.length - 1) {
            throw new IndexOutOfBoundsException("Індекс виходить за допустимі межі");
        }else {
            return array[index];
        }
    }

    //метод, що повертає підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to.

    public ArrayTaskList incoming (int from, int to) throws IllegalArgumentException {
        if ((from < 0)||(to < 0)) {
            throw new IllegalArgumentException("Аргументи 'from' та 'to' не можуть бути від’ємним числом!");
        }
        ArrayTaskList taskList = new ArrayTaskList();
        for (Task task : array) {
            if (task != null && (task.nextTimeAfter(from) < to) && (task.nextTimeAfter(from) != -1)) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
