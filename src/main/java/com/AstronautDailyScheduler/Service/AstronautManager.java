package com.AstronautDailyScheduler.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.AstronautDailyScheduler.Model.Astronaut;

public class AstronautManager {
    private static AstronautManager instance;
    private Map<Integer, Astronaut> astronauts;

    private AstronautManager() {
        astronauts = new HashMap<>();
    }

    public static AstronautManager getInstance() {
        if (instance == null) {
            synchronized (AstronautManager.class) {
                if (instance == null) {
                    instance = new AstronautManager();
                }
            }
        }

        return instance;
    }

    public void addAstronaut(Astronaut astronaut) {
        astronauts.putIfAbsent(astronaut.getID(), astronaut);
    }

    public Astronaut getAstronaut(int id) {
        if (astronauts.containsKey(id)) {
            return astronauts.get(id);
        } else {
            System.out.println("Astronaut doesn't exist !");
            return null;
        }
        
    }

    public void removeAstronaut(int id) {
        if (astronauts.containsKey(id)) {
            astronauts.remove(id);
        } else {
            System.out.println("Astronaut doesn't exist !");
        }
    }

    public Collection<Astronaut> getAllAstronauts() {
        return astronauts.values();
    }
}
