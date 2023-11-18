package com.example.geektrust.Config;

import com.example.geektrust.Entities.CCave;
import com.example.geektrust.Entities.DTower;
import com.example.geektrust.Entities.GMansion;
import com.example.geektrust.Services.BookingService;
import com.example.geektrust.Services.IService;
import com.example.geektrust.Services.VacancyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private static final Config config = new Config();
    private final Map<String, IService> registry = new HashMap<>();
    //Initialising Entities
    private final CCave cCave = new CCave();
    private final DTower dTower = new DTower();
    private final GMansion gMansion = new GMansion();
    //Initialising service
    private final IService bookingService = new BookingService(cCave, dTower, gMansion);
    private final IService vacancyService = new VacancyService(cCave, dTower, gMansion);

    private Config() {
    }

    public static Config getConfigInstance() {
        return config;
    }

    public void register() {
        registry.put("BOOK", bookingService);
        registry.put("VACANCY", vacancyService);
    }

    public void invoker(List<String> tokens) {

        try {
            String output = registry.get(tokens.get(0)).invoke(tokens);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
