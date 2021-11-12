package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import java.util.Objects;

public class Task implements Cloneable{
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeat;

    /* конструктор для неактивної задачі,
     яка виконується у заданий час без повторення
     */
    public Task(String title, int time) throws IllegalArgumentException {

        if (time < 0) {
            throw new IllegalArgumentException("Час не може бути від’ємним числом!");
        }
        this.title = title;
        this.time = time;
    }

    /* конструктор для неактивної задачі,
    яка виконується у заданщму проміжку часу (початок і кінець)
    із заданим інтервалом
     */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        this.title = title;
        if ((start < 0)||(end < 0)||(interval <= 0)) {
            throw new IllegalArgumentException("Час (початок, кінець або інтервал задачі) не може бути від’ємним числом!");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeat = true;
    }

    // метод для зчитування назви задачі
    public String getTitle() {
        return title;
    }

    // метод для встановлення назви задачі
    public void setTitle(String title) {
        this.title = title;
    }

    // метод для зчитування стану задачі (активна-не активна)
    public boolean isActive() {
        return active;
    }

    // метод для всановлення стану задачі (активна-не активна)
    public void setActive(boolean active) {
        this.active = active;
    }


    // Методи для зчитування та зміни часу виконання для задач, що не повторюються:

    /* повертає час виконання задачі,
     або повертає час початку повторення у разі, якщо задача повторюється
     */
    public int getTime() {
        return (!repeat) ? time : start;
    }

    /* змінює час виконання задачі,
    або перетворює її на таку, що не повторюється, у разі якщо задача повторюється.
     */
    public void setTime(int time) {
        this.time = time;
        if (repeat) {
            this.start = 0;
            this.end = 0;
            this.interval = 0;
            this.repeat = false;
        }
    }


    // Методи для зчитування та зміни часу виконання для задач, що повторюються:

    /* повертає час початку повторення,
    або час виконання задачі у разі якщо задача не повторюється
     */
    public int getStartTime() {
        return (repeat) ? start : time;
    }

    /* повертає час кінця виконання повторень,
    або час виконання задачі у разі якщо задача не повторюється
     */
    public int getEndTime() {
        return (repeat) ? end : time;
    }

    // повертає інтервал, або 0 - у разі якщо задача не повторюється.
    public int getRepeatInterval() {
        return (repeat) ? interval : 0;
    }

    /* Метод, що змінює час початку, кінця та інтервалу задачі,
    а у разі якщо задача не повторюється, перетворює її на таку що повторюється
     */
    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (!repeat) {
            this.repeat = true;
        }
    }

    // для перевірки повторюванності задачі
    public boolean isRepeated() {
        return repeat;
    }

    /* метод, що повертає час наступного виконання задачі після вказанного часу,
    якщо після вказанного часу задача не виконується, то метод повертає -1
     */
    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        } else {
            if (!repeat) {
                return (current >= time) ? -1 : time;
            } else {
                //вводимо змінну, що початково вважає, що після вказанного часу задача не виконується
                int nextTime = -1;

                //цикл пошуку наступного виконання задачі. Якщо таке значення є, змінюємо nextTime на це значення
                for (int i = start; i < end; i = i + interval) {
                    if (current < i) {
                        nextTime = i;
                        return nextTime;
                    }
                }
                return nextTime;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTime() == task.getTime()
                && start == task.start && end == task.end
                && interval == task.interval && isActive() == task.isActive()
                && repeat == task.repeat && Objects.equals(getTitle(), task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTime(), start, end, interval, isActive(), repeat);
    }

    @Override
    public String toString() {
        if (this.isRepeated()) {
            return "(" +
                    "title='" + title + '\'' +
                    ", time=" + time +
                    ", start=" + start +
                    ", end=" + end +
                    ", interval=" + interval +
                    ", active=" + active +
                    ')';
        }else {
            return "(" +
                    "title='" + title + '\'' +
                    ", time=" + time +
                    ", active=" + active +
                    ')';
        }
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}
