package com.wessam.footballleagueapplication.data.db.leagues;

public class Competitions {

    private int id;
    private int numberOfAvailableSeasons;
    private String code;
    private String name;

    public Competitions() {
    }

    public Competitions(int numberOfAvailableSeasons, String code, String name, int id) {
        this.numberOfAvailableSeasons = numberOfAvailableSeasons;
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public int getNumberOfAvailableSeasons() {
        return numberOfAvailableSeasons;
    }

    public void setNumberOfAvailableSeasons(int numberOfAvailableSeasons) {
        this.numberOfAvailableSeasons = numberOfAvailableSeasons;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
