package com.AstronautDailyScheduler.Service;

import java.time.LocalTime;

import com.AstronautDailyScheduler.Model.Astronaut;
import com.AstronautDailyScheduler.Model.IndividualTask;
import com.AstronautDailyScheduler.Model.Priority;
import com.AstronautDailyScheduler.Model.Task;
import com.AstronautDailyScheduler.Model.TeamTask;

public class TaskFactory {

    public static Task createTask(String description, LocalTime startTime, LocalTime endTime, Priority priority,
    Astronaut owner, boolean isCompleted, boolean isTeamTask) {
        if (isTeamTask) {
            return new TeamTask(description, startTime, endTime, priority, owner, isCompleted);
        } else {
            return new IndividualTask(description, startTime, endTime, priority, owner, isCompleted);
        }
    }
}
