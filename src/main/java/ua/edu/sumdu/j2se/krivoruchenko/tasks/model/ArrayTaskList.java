package ua.edu.sumdu.j2se.krivoruchenko.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable{

    private Task[] array = new Task[10];

    // метод, що повертає тип об’єкта
    @Override
    public ListTypes.types getType() {
        return ListTypes.types.ARRAY;
    }

    // метод, що додає до списку вказану задачу
    @Override
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
    @Override
    public boolean remove (Task task) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(task)) {
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
    @Override
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
    @Override
    public Task getTask (int index) throws IndexOutOfBoundsException{
        if (index > array.length - 1) {
            throw new IndexOutOfBoundsException("The index is out of range");
        }else {
            return array[index];
        }
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;

        ArrayTaskList list = (ArrayTaskList) o;

        return Arrays.equals(array, list.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return "ArrayTaskList\n" +
                 Arrays.toString(array);
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int cursor = 0;
            private int last = -1;

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public Task next() {
                if (!hasNext())throw new NoSuchElementException("The element being requested does not exist!");
                int i = cursor;
                Task next = getTask(i);
                last = i;
                cursor = i + 1;
                return next;
            }

            @Override
            public void remove() {
                if (last < 0) throw new IllegalStateException("Method 'next' is not called!");
                ArrayTaskList.this.remove(getTask(last));
                if (last < cursor) {
                    cursor--;
                    last = -1;
                }
            }
        };
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList clone;
        clone = (ArrayTaskList) super.clone();
        clone.array = this.array.clone();
        return clone;
    }
}
