package com.wessam.footballleagueapplication.data.db.leagues;

import java.util.List;

public class Leagues {

    private int count;
    private List<Competitions> competitions;

    public Leagues() {
    }

    public Leagues(List<Competitions> competitions, int count) {
        this.competitions = competitions;
        this.count = count;
    }

    public List<Competitions> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competitions> competitions) {
        this.competitions = competitions;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
