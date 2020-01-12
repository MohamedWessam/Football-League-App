package com.wessam.footballleagueapplication.data.db.teams;

import java.util.List;

public class LeagueTeams {

    private int count;
    private List<Teams> teams;

    public LeagueTeams() {
    }

    public LeagueTeams(int count, List<Teams> teams) {
        this.count = count;
        this.teams = teams;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }
}
