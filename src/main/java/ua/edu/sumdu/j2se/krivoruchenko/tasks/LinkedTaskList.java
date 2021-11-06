package ua.edu.sumdu.j2se.krivoruchenko.tasks;

public class LinkedTaskList extends AbstractTaskList{

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

    // метод, що повертає тип об’єкта
    @Override
    ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }

    // метод, що додає до списку вказану задачу
    @Override
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
    @Override
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
    @Override
    public int size () {
        return size;
    }

    /* метод, що повертає задачу, яка знаходиться на вказаному місці у
    списку, перша задача має індекс 0.
     */
    @Override
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
}
