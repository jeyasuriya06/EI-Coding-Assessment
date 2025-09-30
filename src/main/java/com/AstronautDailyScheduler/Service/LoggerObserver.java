package com.AstronautDailyScheduler.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LoggerObserver implements TaskObserver{
    
    private final static String logFile = "app.log";

    @Override
    public void update(int id, String type, String task) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            out.println(LocalDateTime.now() + "Astronaut: " + id + " Task: " + type + " | " + task);

        } catch (IOException e) {
            System.out.println("Failed to log: " + e.getMessage());
        }
    }

    public static void logError(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            out.println(LocalDateTime.now() + " [ERROR] " + message);

        } catch (IOException e) {
            System.out.println("Failed to log: " + e.getMessage());
        }
    }

}
