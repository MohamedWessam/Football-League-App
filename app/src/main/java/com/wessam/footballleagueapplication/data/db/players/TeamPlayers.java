package com.wessam.footballleagueapplication.data.db.players;

import java.util.List;

public class TeamPlayers {

    private List<Squad> squad;
    private String venue;
    private String clubColors;
    private int founded;
    private String email;
    private String website;
    private String phone;
    private String address;
    private String crestUrl;
    private String tla;
    private String shortName;
    private String name;
    private int id;

    public TeamPlayers() {
    }

    public TeamPlayers(List<Squad> squad, String venue, String clubColors, int founded, String email, String website, String phone, String address, String crestUrl, String tla, String shortName, String name, int id) {
        this.squad = squad;
        this.venue = venue;
        this.clubColors = clubColors;
        this.founded = founded;
        this.email = email;
        this.website = website;
        this.phone = phone;
        this.address = address;
        this.crestUrl = crestUrl;
        this.tla = tla;
        this.shortName = shortName;
        this.name = name;
        this.id = id;
    }

    public List<Squad> getSquad() {
        return squad;
    }

    public void setSquad(List<Squad> squad) {
        this.squad = squad;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
