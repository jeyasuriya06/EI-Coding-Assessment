package com.AstronautDailyScheduler.UI;

import java.time.LocalTime;
import java.util.Scanner;

import com.AstronautDailyScheduler.Model.Astronaut;
import com.AstronautDailyScheduler.Model.Priority;
import com.AstronautDailyScheduler.Model.Task;
import com.AstronautDailyScheduler.Service.AstronautManager;
import com.AstronautDailyScheduler.Service.LoggerObserver;
import com.AstronautDailyScheduler.Service.TaskFactory;
import com.AstronautDailyScheduler.Service.TaskObserver;
import com.AstronautDailyScheduler.Service.TaskScheduler;

public class ConsoleUI implements TaskObserver {
    private final Scanner input = new Scanner(System.in);
    private final AstronautManager manager = AstronautManager.getInstance();
    private final TaskScheduler scheduler = TaskScheduler.getInstance();

    public ConsoleUI() {
        scheduler.addObserver(this);
        scheduler.addObserver(new LoggerObserver());
    }

    public void start() {
        boolean running = true;
        System.out.println("Astronaut Daily Scheduler");
        while (running) {
            System.out.println();
            System.out.println("1) Add Astronaut");
            System.out.println("2) Create Task");
            System.out.println("3) View Astronaut tasks");
            System.out.println("4) Remove Task");
            System.out.println("5) Edit Task");
            System.out.println("6) Mark as completed");
            System.out.println("0) Exit");

            System.out.print("Enter your Choice: ");
        
            int choice = Integer.parseInt(input.nextLine());
            System.out.println();

            switch (choice) {
                case 1:
                    addAstronaut();
                    break;
                case 2:
                    createTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    editTask();
                    break;
                case 6:
                    markCompleted();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }

        System.out.println("See you again !");
    }


    private void markCompleted() {
        System.out.println();
        System.out.println("Enter astronaut ID: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Enter task name: ");
        String desc = input.nextLine();
        System.out.println("Enter start time (HH:MM): ");
        LocalTime start = getTimeInput();
        scheduler.markCompleted(id, desc, start);
        
        System.out.println();
    }

    private LocalTime getTimeInput() {
        LocalTime time = null;
        while (true) {
            try {
                time = LocalTime.parse(input.nextLine());
                return time;
            } catch (Exception e) {
                System.out.println("Invalid time input. Try again.");
                LoggerObserver.logError("invalid time input.");
            }
        }
    }

    private Priority getPriorityInput() {
        Priority priority = null;
        while (true) {
            try {
                priority = Priority.valueOf(input.nextLine().trim().toUpperCase());
                return priority;
            } catch (Exception e) {
                System.out.println("Invalid Priority. Try again");
                LoggerObserver.logError("invalid priority input");
            }
        }
    }

    private void editTask() {
        System.out.println();
        System.out.print("Enter astronaut ID: ");
        int astronautId = input.nextInt();
        input.nextLine();

        System.out.print("Enter old task description: ");
        String oldDescription = input.nextLine();
        
        System.out.print("Enter old task start time (HH:MM): ");
        LocalTime oldStart = getTimeInput();

        System.out.print("Enter new description: ");
        String newDescription = input.nextLine();

        System.out.print("Enter task new start time (HH:MM): ");
        LocalTime newStart = getTimeInput();

        System.out.print("Enter task new end time (HH:MM): ");
        LocalTime newEnd = getTimeInput();

        System.out.print("Enter new priority (LOW, MID, HIGH): ");
        Priority newPriority = getPriorityInput();

        
        boolean edited = scheduler.editTask(
            astronautId, oldDescription, oldStart, newDescription, newStart, newEnd, newPriority
        );

        if (edited) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Failed to update task (either not found, overlap, or you are not the owner of a team task).");
        }

        System.out.println();
    }

    private void removeTask() {
        System.out.println();
        System.out.println("Astronaut ID: ");
        int id = Integer.parseInt(input.nextLine());
        Astronaut astronaut = manager.getAstronaut(id);

        if (astronaut == null) {
            LoggerObserver.logError("No astronaut found !");
            System.out.println("No astronaut found ! - Task: Deleting");
            return;
        }

        System.out.println("Enter task name: ");
        String desc = input.nextLine();

        System.out.print("Start time of task (HH:mm): ");
        LocalTime start = getTimeInput();

        boolean removed = scheduler.removeTask(id, desc, start);
        
        if (!removed) {
            LoggerObserver.logError("No matching Task found !");
            System.out.println("No matching Task found !");
        }
        System.out.println();
    }

    private void viewTasks() {
        System.out.println();
        System.out.println("Do you want to view task sorted by Start Time(time) or Priority(priority): ");
        String type = input.nextLine();
        if (type.equalsIgnoreCase("time")) {
            System.out.println("Astronaut ID: ");
            int id = Integer.parseInt(input.nextLine());
            Astronaut astronaut = manager.getAstronaut(id);

            if (astronaut == null){
                LoggerObserver.logError("Astronaut Not Found !");
                System.out.println("Astronaut Not Found ! Task - Viewing");
                return;
            }

            if (astronaut.getTasks().isEmpty()) {
                System.out.println("No tasks created");
            } else {
                System.out.println("Tasks for Astronaut " + id + ":");
                for (Task t: astronaut.getTasks()) {
                    System.out.println("- " + t.getDescription() + " (" + t.getStartTime() + " -> " + t.getEndTime() + ") " + "priority: " + t.getPriority());
                }
            }
            System.out.println();
        } else if (type.equalsIgnoreCase("priority")) {
            System.out.println("Astronaut ID: ");
            int id = Integer.parseInt(input.nextLine());
            Astronaut astronaut = manager.getAstronaut(id);

            if (astronaut == null){
                LoggerObserver.logError("Astronaut Not Found !");
                System.out.println("Astronaut Not Found ! Task - Viewing");
                return;
            }

            System.out.println("Enter priority (HIGH/MID/LOW): ");
            Priority priority = getPriorityInput();

            for (Task t: AstronautManager.getInstance().getAstronaut(id).getTasks()) {
                if (t.getPriority() == priority) {
                    System.out.println("- " + t.getDescription() + " (" + t.getStartTime() + " -> " + t.getEndTime() + ") " + "priority: " + t.getPriority());
                }
            }
        }

    }

    private void createTask() {
        System.out.println();
        System.out.print("Astronaut ID: ");
        int id = Integer.parseInt(input.nextLine());
        Astronaut astronaut = manager.getAstronaut(id);

        if (astronaut == null) {
            System.out.println("Astronaut not found");
            LoggerObserver.logError("Astronaut Not Found !");
            return;
        }

        System.out.println("Task description: ");
        String desc = input.nextLine();

        System.out.println("Start time (HH:MM): ");
        LocalTime start = getTimeInput();

        System.out.println("End Time (HH:MM): ");
        LocalTime end = getTimeInput();

        System.out.println("Enter priority: (LOW / MID / HIGH): ");
        Priority priority = getPriorityInput();


        System.out.println("Team (team) or Individual Task (individual): ");

        String type = input.nextLine();

        Task task;

        if (type.equalsIgnoreCase("team")) {
            task = TaskFactory.createTask(desc, start, end, priority, astronaut, false, true);
        } else if (type.equalsIgnoreCase("individual")) {
            task = TaskFactory.createTask(desc, start, end, priority, astronaut, false, false);
        } else {
            LoggerObserver.logError("Unknown task type !");
            System.out.println("Unknown task type !");
            return;
        }

        scheduler.addTask(task);
        System.out.println();
        
        
    }
    private void addAstronaut() {
        System.out.println();
        System.out.println("Enter astronaut ID: ");
        int id = Integer.parseInt(input.nextLine());
        Astronaut astronaut = new Astronaut(id);
        manager.addAstronaut(astronaut);

        System.out.println("Astronaut added with ID: " + id);
        System.out.println();
    }

    public void update(int id, String type, String task) {
        System.out.println();
        System.out.println("Astronaut " + id + " has " + type + " " + task);
        System.out.println();
    }

    

}
