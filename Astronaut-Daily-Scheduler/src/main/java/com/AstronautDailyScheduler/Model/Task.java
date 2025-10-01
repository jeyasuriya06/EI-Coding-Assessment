package com.AstronautDailyScheduler.Model;

import java.time.LocalTime;

public interface Task{
    String getDescription();
    LocalTime getStartTime();
    LocalTime getEndTime();
    Priority getPriority();
    Astronaut getOwner();
    boolean isCompleted();
    boolean isTeamTask();
    void markCompleted();
}
