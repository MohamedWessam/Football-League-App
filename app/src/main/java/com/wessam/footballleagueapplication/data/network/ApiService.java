package com.wessam.footballleagueapplication.data.network;

import com.wessam.footballleagueapplication.data.db.leagues.Leagues;
import com.wessam.footballleagueapplication.data.db.players.TeamPlayers;
import com.wessam.footballleagueapplication.data.db.teams.LeagueTeams;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("competitions")
    Call<Leagues> getAllLeagues();

    @GET("competitions/{league_id}/teams")
    Call<LeagueTeams> getLeagueTeams(@Path("league_id") int leagueId);

    @GET("teams/{team_id}")
    Call<TeamPlayers> getPlayers(@Path("team_id") int teamId);

}