package ua.edu.sumdu.j2se.krivoruchenko.tasks;

public class LinkedTaskList {
    private int size = 0;
    private Node head;


    /* вкладений клас Node для формування вузлів зв'язного списку LinkedTaskList
    (кожен вузол має значення Task та посилання на наступний вузол списку)
     */
    private static class Node {
        private final Task element;
        private Node next;

        public Node (Task element) {
            this.element = element;
        }
    }


    // метод, що додає до списку вказану задачу
    public void add (Task task) {
        if (head == null){
            head = new Node(task);
        }else {
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
    public boolean remove (Task task) {
        Node current = head;
        Node prev = null;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(task)) {
                if (prev == null){
                    head = current.next;
                }else {
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
    public int size () {
        return size;
    }

    /* метод, що повертає задачу, яка знаходиться на вказаному місці у
    списку, перша задача має індекс 0.
     */
    public Task getTask (int index) throws IndexOutOfBoundsException {
        if ((index < 0)||(index > size)){
            throw new IndexOutOfBoundsException("Індекс виходить за допустимі межі");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    //метод, що повертає підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to.

    public LinkedTaskList incoming (int from, int to) throws IllegalArgumentException {
        if ((from < 0)||(to < 0)) {
            throw new IllegalArgumentException("Аргументи 'from' та 'to' не можуть бути від’ємним числом!");
        }
        LinkedTaskList taskList = new LinkedTaskList();
        Node current = head;
        while (current != null) {
            if ((current.element.nextTimeAfter(from) < to)&&(current.element.nextTimeAfter(from) != -1)) {
                taskList.add(current.element);
            }
            current = current.next;
        }
        return taskList;
    }
}
