package com.AstronautDailyScheduler.Model;

import java.time.LocalTime;

public class TeamTask implements Task, Comparable<TeamTask> {

    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private Astronaut owner;
    private boolean isCompleted = false;

    public TeamTask(String description, LocalTime startTime, LocalTime endTime, Priority priority, Astronaut owner, boolean isCompleted) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.owner = owner;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }


    @Override
    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public boolean isTeamTask() {
        return true;
    }

    @Override
    public Astronaut getOwner() {
        return owner;
    }

    @Override
    public int compareTo(TeamTask other) {
        return this.startTime.compareTo(other.startTime);
    }

}