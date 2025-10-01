package com.AstronautDailyScheduler.Service;

public interface TaskObserver {
    void update(int id, String type, String task);
}
