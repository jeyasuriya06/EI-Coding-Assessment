package com.AstronautDailyScheduler.Service;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.AstronautDailyScheduler.Model.Astronaut;
import com.AstronautDailyScheduler.Model.Priority;
import com.AstronautDailyScheduler.Model.Task;


public class TaskScheduler implements TaskSubject {
    private static TaskScheduler scheduler; //singleton instance;
    private List<TaskObserver> observers;
    
    
    private TaskScheduler() {
        this.observers = new ArrayList<>();
        
    }

    public static TaskScheduler getInstance() {
        if (scheduler == null) {
            synchronized (TaskScheduler.class) {
                if (scheduler == null) {
                    scheduler = new TaskScheduler();
                }
            }
        }

        return scheduler;
    }

    @Override
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int id, String type, Task task) {
        for (TaskObserver observer: observers) {
            observer.update(id, type, task.getDescription());
        }
    }

    public boolean addTask(Task task) {
        if (hasOverlap(task)) {
            LoggerObserver.logError("Task overlapping");
            return false;
        }

        if (task.isTeamTask()) {
            if (hasOverlapWithTeam(task)) return false;
            Collection<Astronaut> owners = AstronautManager.getInstance().getAllAstronauts();

            for (Astronaut astronaut: owners) {
                astronaut.addTask(task);
                System.out.print("Notification for Astronaut with ID " + astronaut.getID() + ": ");
                notifyObservers(task.getOwner().getID(), "added", task);
            }
        } else {
            Astronaut owner = task.getOwner();
            owner.addTask(task);
            notifyObservers(owner.getID(), "added", task);
        }
        
        return true;
    }

    private boolean hasOverlapWithTeam(Task task) {
        Collection<Astronaut> astronauts = AstronautManager.getInstance().getAllAstronauts();

        for (Astronaut astronaut: astronauts) {
            if (astronaut.getID() != task.getOwner().getID()) {
                for (Task newTask : astronaut.getTasks()) {
                    if (!(task.getEndTime().isBefore(newTask.getStartTime()) || newTask.getEndTime().isBefore(task.getStartTime()))) {
                        System.out.println("Overlaps for Astronaut " + astronaut.getID());
                        System.out.println(newTask.getDescription() + " (" + task.getStartTime() + "-" + task.getEndTime() + ") overlaps with " + task.getDescription() + " (" + newTask.getStartTime() + "-" + newTask.getEndTime() + ")");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean hasOverlap(Task newTask) {
        Astronaut owner = newTask.getOwner();

        for (Task task: owner.getTasks()) {
            if (!(task.getEndTime().isBefore(newTask.getStartTime()) || newTask.getEndTime().isBefore(task.getStartTime()))) {
                LoggerObserver.logError(newTask.getDescription() + " (" + task.getStartTime() + "-" + task.getEndTime() + ") overlaps with " + task.getDescription() + " (" + newTask.getStartTime() + "-" + newTask.getEndTime() + ")");
                System.out.println(newTask.getDescription() + " (" + task.getStartTime() + "-" + task.getEndTime() + ") overlaps with " + task.getDescription() + " (" + newTask.getStartTime() + "-" + newTask.getEndTime() + ")");
                return true;
            }
        }

        return false;
    }

    public boolean removeTask(int astronautID, String description, LocalTime startTime) {
        Astronaut astronaut = AstronautManager.getInstance().getAstronaut(astronautID);

        List<Task> tasks = astronaut.getTasks();

        for (Task task: tasks) {
            if (task.getDescription().equalsIgnoreCase(description) && task.getStartTime().equals(startTime)) {
                astronaut.removeTask(task);
                notifyObservers(astronautID, "removed", task);
                return true;
            }
        }

        return false;
    }


    public boolean editTask(int astronautID, String oldDescription, LocalTime oldStart, String newDescription,
            LocalTime newStart, LocalTime newEnd, Priority newPriority) {

        Astronaut astronaut = AstronautManager.getInstance().getAstronaut(astronautID);
        if (astronaut == null) {
            LoggerObserver.logError("Astronaut Not Found | Task: Editing");
            return false;
        }

        Task taskToEdit = null;
        for (Task task : astronaut.getTasks()) {
            if (task.getDescription().equalsIgnoreCase(oldDescription)
                    && task.getStartTime().equals(oldStart)) {
                taskToEdit = task;
                break;
            }
        }

        if (taskToEdit == null) {
            return false;
        }

        if (taskToEdit.isTeamTask() && taskToEdit.getOwner().getID() != astronautID) {
            return false;
        }

        if (taskToEdit.isTeamTask()) {
            for (Astronaut a : AstronautManager.getInstance().getAllAstronauts()) {
                a.removeTask(taskToEdit);
            }
        } else {
            astronaut.removeTask(taskToEdit);
        }


        Task editedTask = TaskFactory.createTask(
                newDescription,
                newStart,
                newEnd,
                newPriority,
                taskToEdit.getOwner(),
                taskToEdit.isCompleted(),
                taskToEdit.isTeamTask()
        );

        if (editedTask.isTeamTask()) {
            if (hasOverlapWithTeam(editedTask)) {
                for (Astronaut a : AstronautManager.getInstance().getAllAstronauts()) {
                    a.addTask(taskToEdit);
                }
                LoggerObserver.logError("Task not edited | Found overlap");
                return false;
            }
        } else {
            if (hasOverlap(editedTask)) {
                astronaut.addTask(taskToEdit);
                LoggerObserver.logError("Task not edited | Found overlap");
                return false;
            }
        }

        

        if (editedTask.isTeamTask()) {
            for (Astronaut a : AstronautManager.getInstance().getAllAstronauts()) {
                a.addTask(editedTask);
            }
        } else {
            astronaut.addTask(editedTask);
        }
        notifyObservers(astronautID, "edited", editedTask);
        return true;
    }

    public void markCompleted(int astronautID, String desc, LocalTime startTime) {
        Astronaut astronaut = AstronautManager.getInstance().getAstronaut(astronautID);
        if (astronaut == null) {
            LoggerObserver.logError("Astronaut Not Found | Task: marking complete");
            return;
        }

        for (Task task: astronaut.getTasks()) {
            if (task.getDescription().equalsIgnoreCase(desc) && task.getStartTime().equals(startTime)) {
                astronaut.removeTask(task);
                notifyObservers(astronautID, "completed", task);
            }
        }
    }
}
