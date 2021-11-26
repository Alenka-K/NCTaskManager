package ua.edu.sumdu.j2se.krivoruchenko.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable{
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeat;

    /* конструктор для неактивної задачі,
     яка виконується у заданий час без повторення
     */
    public Task(String title, LocalDateTime time) throws IllegalArgumentException {

        if (time == null) {
            throw new IllegalArgumentException("Час не може бути null!");
        }
        this.title = title;
        this.time = time;
    }

    /* конструктор для неактивної задачі,
    яка виконується у заданщму проміжку часу (початок і кінець)
    із заданим інтервалом
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        this.title = title;
        if ((start == null)||(end == null)) {
            throw new IllegalArgumentException("Час (початок, кінець) не може бути null!");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("Iнтервал задачі не може бути від’ємним числом!");
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
    public LocalDateTime getTime() {
        return (!repeat) ? time : start;
    }

    /* змінює час виконання задачі,
    або перетворює її на таку, що не повторюється, у разі якщо задача повторюється.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
        if (repeat) {
            this.start = null;
            this.end = null;
            this.interval = 0;
            this.repeat = false;
        }
    }


    // Методи для зчитування та зміни часу виконання для задач, що повторюються:

    /* повертає час початку повторення,
    або час виконання задачі у разі якщо задача не повторюється
     */
    public LocalDateTime getStartTime() {
        return (repeat) ? start : time;
    }

    /* повертає час кінця виконання повторень,
    або час виконання задачі у разі якщо задача не повторюється
     */
    public LocalDateTime getEndTime() {
        return (repeat) ? end : time;
    }

    // повертає інтервал, або 0 - у разі якщо задача не повторюється.
    public int getRepeatInterval() {
        return (repeat) ? interval : 0;
    }

    /* Метод, що змінює час початку, кінця та інтервалу задачі,
    а у разі якщо задача не повторюється, перетворює її на таку що повторюється
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
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
    якщо після вказанного часу задача не виконується, то метод повертає null
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!active) {
            return  null;
        } else {
            if (!repeat) {
                return (current.isBefore(time)) ? time : null;
            } else {

                //цикл пошуку наступного виконання задачі.
                for (LocalDateTime i = start; i.compareTo(end) <= 0; i = i.plusSeconds(interval)) {
                    if (current.isBefore(i)) {
                        return i;
                    }
                }
                return null;
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
