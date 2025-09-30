# Astronaut Daily Scheduler

## Features
- Add, remove, edit, and view tasks
- Mark tasks as completed
- Prevent overlapping tasks
- View tasks sorted by start time
- Filter tasks by priority
- Logging via Observer pattern

## Design Pattern Used
- **Singleton** → TaskScheduler, AstronautManager
- **Factory** → TaskFactory
- **Observer** → LoggerObserver, ConsoleUI

## How to run

- **Compilation**

    **Windows:**

    javac -d out src\main\java\com\AstronautDailyScheduler\*.java src\main\java\com\AstronautDailyScheduler\Model\*.java src\main\java\com\AstronautDailyScheduler\Service\*.java src\main\java\com\AstronautDailyScheduler\UI\*.java


    **Linux/macOS:**

    javac -d out $(find src/main/java -name "*.java")


- **Running the program**
    
    java -cp out com.AstronautDailyScheduler.Main
    
