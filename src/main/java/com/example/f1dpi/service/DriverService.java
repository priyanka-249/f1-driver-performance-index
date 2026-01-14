package com.example.f1dpi.service;

import com.example.f1dpi.Driver;
import com.example.f1dpi.datasource.JolpicaClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DriverService {

    private final JolpicaClient client = new JolpicaClient();
    private static final int TOTAL_RACES = 22;

    public List<Driver> getRankedDrivers() {

        List<Driver> drivers = client.fetchDriversFromApi();
        Map<String, Integer> racesFinished =
                client.fetchRacesFinishedByDriver();

        for (Driver d : drivers) {
            int finished =
                    racesFinished.getOrDefault(d.getName(), 0);
            d.setRacesFinished(finished);
            d.setTotalRaces(TOTAL_RACES);
        }


        drivers.sort(
                Comparator.comparingDouble(Driver::getDpi).reversed()
        );

        return drivers;
    }
}

