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


}
