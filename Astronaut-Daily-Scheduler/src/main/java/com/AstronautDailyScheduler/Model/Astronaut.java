package com.AstronautDailyScheduler.Model;
import java.util.ArrayList;
import java.util.List;

public class Astronaut{
    private int id;
    private List<Task> tasks;

    public Astronaut(int id) {
        this.id = id;
        this.tasks = new ArrayList<>();
    }

    public int getID() {
        return id;
    }


    public void addTask(Task task) {
        tasks.add(task);
        tasks.sort((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // returns a copy of the original tasks of the Astronaut
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}