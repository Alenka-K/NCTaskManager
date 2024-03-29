package ua.edu.sumdu.j2se.krivoruchenko.tasks.model;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable{

    private int size = 0;
    private Node head;


    /* вкладений клас Node для формування вузлів зв'язного списку LinkedTaskList
    (кожен вузол має значення Task та посилання на наступний вузол списку)
     */
    private static class Node {
        private final Task element;
        private Node next;

        public Node(Task element) {
            this.element = element;
        }
    }

    // метод, що повертає тип об’єкта
    @Override
    public ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }

    // метод, що додає до списку вказану задачу
    @Override
    public void add(Task task) {
        if (head == null) {
            head = new Node(task);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(task);
        }
        size++;
    }


    /* метод, що видаляє задачу зі списку і повертає істину, якщо
     така задача була у списку. Якщо у списку було декілька таких задач, необхідно видалити одну
     будь-яку
    */
    @Override
    public boolean remove(Task task) {
        Node current = head;
        Node prev = null;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(task)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return false;
    }

    // метод, що повертає кількість задач у списку
    @Override
    public int size() {
        return size;
    }

    /* метод, що повертає задачу, яка знаходиться на вказаному місці у
    списку, перша задача має індекс 0.
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("The index is out of range");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public Stream<Task> getStream() {
        Stream.Builder<Task> builder = Stream.builder();
        for(int i = 0; i < size; i++) {
            builder.add(getTask(i));
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;

        LinkedTaskList list = (LinkedTaskList) o;

        if (size != list.size) return false;
        for (int i = 0; i < size; i++) {
            if (!this.head.element.equals(list.head.element)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * size;
        for (int i = 0; i < size; i++) {
            result = result + head.element.hashCode();
            head = head.next;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("LinkedTaskList {");
        if (head != null) {
            Node temp = head;
            String sep = "\n";
            while (temp != null) {
                text.append(sep).append(temp.element);
                temp = temp.next;
            }
        }
        return text.append("}\n").toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private Node current = head;
            private Node prev;
            private Node prevDelete = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() {
                if (!hasNext()) throw new NoSuchElementException("The element being requested does not exist");
                prevDelete = prev;
                prev = current;
                current = current.next;
                return prev.element;
            }

            @Override
            public void remove() {
                if (prev == null) {
                    throw new IllegalStateException("Method 'next' is not called!");
                }
                if(prev == head) {
                    head = head.next;
                    prev = null;
                }else {
                    prev = prevDelete;
                    prevDelete.next = current;
                    size--;
                }
            }

        };
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        return (LinkedTaskList) super.clone();
    }
}
