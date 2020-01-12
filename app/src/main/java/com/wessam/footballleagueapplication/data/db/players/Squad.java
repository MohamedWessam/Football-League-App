package com.wessam.footballleagueapplication.data.db.players;

public class Squad {

    private String nationality;
    private String position;
    private String name;
    private int id;

    public Squad() {
    }

    public Squad(String nationality, String position, String name, int id) {
        this.nationality = nationality;
        this.position = position;
        this.name = name;
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
