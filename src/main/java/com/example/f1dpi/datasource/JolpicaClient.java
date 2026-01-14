package com.example.f1dpi.datasource;

import com.example.f1dpi.Driver;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class JolpicaClient {

    private static final String URL =
            "https://api.jolpi.ca/ergast/f1/2024/driverStandings.json";

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")
    public List<Driver> fetchDriversFromApi() {

        Map<String, Object> response =
                restTemplate.getForObject(URL, Map.class);

        Map<String, Object> mrData =
                (Map<String, Object>) response.get("MRData");

        Map<String, Object> standingsTable =
                (Map<String, Object>) mrData.get("StandingsTable");

        List<Map<String, Object>> standingsLists =
                (List<Map<String, Object>>) standingsTable.get("StandingsLists");

        Map<String, Object> firstList = standingsLists.get(0);

        List<Map<String, Object>> driverStandings =
                (List<Map<String, Object>>) firstList.get("DriverStandings");

        List<Driver> drivers = new ArrayList<>();

        for (Map<String, Object> standing : driverStandings) {

            int points = Integer.parseInt((String) standing.get("points"));
            int position = Integer.parseInt((String) standing.get("position"));

            Map<String, Object> driverInfo =
                    (Map<String, Object>) standing.get("Driver");

            String name =
                    driverInfo.get("givenName") + " " + driverInfo.get("familyName");

            List<Map<String, Object>> constructors =
                    (List<Map<String, Object>>) standing.get("Constructors");

            String team = (String) constructors.get(0).get("name");

            // Approximate avg finish using final position



            int totalRaces = 22; // 2024 season
            int racesFinished = totalRaces; // TEMP (assume perfect reliability for now)
            drivers.add(
                    new Driver(name, team, points, position, racesFinished, totalRaces)
            );

        }

        return drivers;
    }
    @SuppressWarnings("unchecked")
    public Map<String, Integer> fetchRacesFinishedByDriver() {

        String url =
                "https://api.jolpi.ca/ergast/f1/2024/results.json?limit=500";

        Map<String, Object> response =
                restTemplate.getForObject(url, Map.class);

        Map<String, Object> mrData =
                (Map<String, Object>) response.get("MRData");

        Map<String, Object> raceTable =
                (Map<String, Object>) mrData.get("RaceTable");

        List<Map<String, Object>> races =
                (List<Map<String, Object>>) raceTable.get("Races");

        Map<String, Integer> finishCount = new HashMap<>();

        for (Map<String, Object> race : races) {

            List<Map<String, Object>> results =
                    (List<Map<String, Object>>) race.get("Results");

            for (Map<String, Object> result : results) {

                Map<String, Object> driver =
                        (Map<String, Object>) result.get("Driver");

                String name =
                        driver.get("givenName") + " " +
                                driver.get("familyName");

                finishCount.put(
                        name,
                        finishCount.getOrDefault(name, 0) + 1
                );
            }
        }

        return finishCount;
    }

}
