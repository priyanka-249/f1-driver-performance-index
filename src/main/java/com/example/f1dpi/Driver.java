package com.example.f1dpi;

public class Driver {

    private String name;
    private String team;
    private int points;
    private int position;
    private int racesFinished;
    private int totalRaces;

    public Driver(String name, String team, int points,
                  int position, int racesFinished, int totalRaces) {
        this.name = name;
        this.team = team;
        this.points = points;
        this.position = position;
        this.racesFinished = racesFinished;
        this.totalRaces = totalRaces;
    }

    public double getDpi() {
        double performanceScore = points;

        double consistencyScore = (21 - position) * 5;

        double reliabilityScore =
                ((double) racesFinished / totalRaces) * 100;

        return performanceScore + consistencyScore + reliabilityScore;
    }
    public void setRacesFinished(int racesFinished) {
        this.racesFinished = racesFinished;
    }
    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public int getRacesFinished() {
        return racesFinished;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

}
