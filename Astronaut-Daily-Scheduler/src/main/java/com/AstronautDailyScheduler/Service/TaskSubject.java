package com.AstronautDailyScheduler.Service;

import com.AstronautDailyScheduler.Model.Task;

public interface TaskSubject {
    void addObserver(TaskObserver observer);
    void removeObserver(TaskObserver observer);
    void notifyObservers(int id, String taskType, Task task);
}