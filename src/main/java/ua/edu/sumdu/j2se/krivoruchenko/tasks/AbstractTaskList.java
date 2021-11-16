package ua.edu.sumdu.j2se.krivoruchenko.tasks;


import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {

    // абстрактні методи
    public abstract ListTypes.types getType();

    public abstract void add (Task task);

    public abstract boolean remove (Task task);

    public abstract int size ();

    public abstract Task getTask (int index);

    public abstract Stream<Task> getStream();

    // метод що повертає підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
    public final AbstractTaskList incoming (int from, int to) throws IllegalArgumentException {
        if ((from < 0)||(to < 0)) {
            throw new IllegalArgumentException("Аргументи 'from' та 'to' не можуть бути від’ємним числом!");
        }

        // створюється новий об’єкт (для зберігання підмножини задач) ArrayTaskList або LinkedTaskList в залежності від типу об’єкту
        AbstractTaskList taskList = TaskListFactory.createTaskList(getType());

        this.getStream()
                .filter(s -> s != null && s.nextTimeAfter(from) < to && s.nextTimeAfter(from) != -1)
                .forEach(taskList::add);

        return taskList;
    }

}
