package com.AstronautDailyScheduler.Model;

import java.time.LocalTime;

public class IndividualTask implements Task, Comparable<IndividualTask> {
    
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private Astronaut owner;
    private boolean isCompleted = false;

    public IndividualTask(String description, LocalTime startTime, LocalTime endTime, Priority priority, Astronaut owner, boolean isCompleted){
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = isCompleted;
        this.owner = owner;
        this.priority = priority;
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
    public Astronaut getOwner() {
        return owner;
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public boolean isTeamTask() {
        return false;
    }

    @Override
    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public int compareTo(IndividualTask other) {
        return this.startTime.compareTo(other.startTime);
    }

    
}
