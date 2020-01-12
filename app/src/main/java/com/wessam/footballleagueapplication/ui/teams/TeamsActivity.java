package com.wessam.footballleagueapplication.ui.teams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.teams.LeagueTeams;
import com.wessam.footballleagueapplication.data.db.teams.Teams;
import com.wessam.footballleagueapplication.data.network.ApiService;
import com.wessam.footballleagueapplication.data.network.RetrofitClient;
import com.wessam.footballleagueapplication.databinding.ActivityTeamsBinding;
import com.wessam.footballleagueapplication.ui.teams.adapter.TeamsAdapter;
import com.wessam.footballleagueapplication.utils.Constants;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsActivity extends AppCompatActivity {

    private ActivityTeamsBinding binding;
    private ApiService apiService;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teams);

        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra(Constants.COMPETITION_ID_KEY)));

        binding.teamsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getTeams();
    }

    private void prepareTeamsListDetails(LeagueTeams leagueTeamsResponse) {
        if (leagueTeamsResponse != null) {
            List<Teams> teamsList = leagueTeamsResponse.getTeams();
            if (teamsList != null && teamsList.size() > 0) {
                TeamsAdapter adapter = new TeamsAdapter(TeamsActivity.this, teamsList);
                binding.teamsRecyclerView.setAdapter(adapter);
            }
        }
    }

    private void getTeams() {
        Call<LeagueTeams> leagueTeamsCall = apiService.getLeagueTeams(id);

        leagueTeamsCall.enqueue(new Callback<LeagueTeams>() {
            @Override
            public void onResponse(@NonNull Call<LeagueTeams> call, @NonNull Response<LeagueTeams> response) {
                if (response.code() == 403) {
                    Toast.makeText(TeamsActivity.this, "These details require (Premier API Account)!", Toast.LENGTH_LONG).show();
                }
                LeagueTeams leagueTeams = response.body();
                prepareTeamsListDetails(leagueTeams);
            }

            @Override
            public void onFailure(@NonNull Call<LeagueTeams> call, @NonNull Throwable t) {
            }
        });
    }

}
