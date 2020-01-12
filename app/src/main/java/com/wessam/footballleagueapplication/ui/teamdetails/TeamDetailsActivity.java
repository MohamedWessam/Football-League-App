package com.wessam.footballleagueapplication.ui.teamdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.room.FavouriteDatabase;
import com.wessam.footballleagueapplication.data.db.room.FavouriteList;
import com.wessam.footballleagueapplication.data.db.players.Squad;
import com.wessam.footballleagueapplication.data.db.players.TeamPlayers;
import com.wessam.footballleagueapplication.data.network.ApiService;
import com.wessam.footballleagueapplication.data.network.RetrofitClient;
import com.wessam.footballleagueapplication.databinding.ActivityTeamDetailsBinding;
import com.wessam.footballleagueapplication.ui.teamdetails.adapter.PlayersAdapter;
import com.wessam.footballleagueapplication.utils.Constants;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailsActivity extends AppCompatActivity {

    private ActivityTeamDetailsBinding binding;
    private ApiService apiService;
    private int id;
    private String name, shortName, imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_details);

        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra(Constants.TEAM_ID_KEY)));

        binding.playersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getPlayersAndTeamDetails();

        setFavouriteButtonIcon();

        FavouriteList favouriteList = new FavouriteList();

        binding.favouriteButton.setOnClickListener(view -> {
            favouriteList.setId(id);
            favouriteList.setCrestUrl(imageUrl);
            favouriteList.setName(name);
            favouriteList.setShortName(shortName);

            if (FavouriteDatabase.getDatabase(this).favoriteDao().isFavorite(id) != 1) {
                Snackbar.make(view, getResources().getString(R.string.added_to_favourite), Snackbar.LENGTH_SHORT).show();
                binding.favouriteButton.setImageResource(R.drawable.ic_favorite);
                FavouriteDatabase.getDatabase(this).favoriteDao().insertTeam(favouriteList);
            } else {
                Snackbar.make(view, getResources().getString(R.string.removed_from_favourite), Snackbar.LENGTH_SHORT).show();
                binding.favouriteButton.setImageResource(R.drawable.ic_favorite_border);
                FavouriteDatabase.getDatabase(this).favoriteDao().delete(favouriteList);
            }
        });

    }

    private void setFavouriteButtonIcon() {
        if (FavouriteDatabase.getDatabase(this).favoriteDao().isFavorite(id) != 1) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favorite_border);
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favorite);
        }
    }

    private void prepareTeamDetails(TeamPlayers teamPlayers) {
        Picasso.get().load(teamPlayers.getCrestUrl()).placeholder(R.drawable.no_image).into(binding.teamDetailsImage);
        binding.nameText.setText(teamPlayers.getName());
        prepareStringDetails(teamPlayers.getAddress(), binding.addressText, binding.addressLayout);
        prepareStringDetails(teamPlayers.getPhone(), binding.phoneText, binding.phoneLayout);
        prepareStringDetails(teamPlayers.getWebsite(), binding.websiteText, binding.websiteLayout);
        prepareStringDetails(teamPlayers.getEmail(), binding.emailText, binding.emailLayout);
        prepareStringDetails(String.valueOf(teamPlayers.getFounded()), binding.foundedText, binding.foundedLayout);
        prepareStringDetails(teamPlayers.getClubColors(), binding.clubColorsText, binding.clubColorsLayout);
        prepareStringDetails(teamPlayers.getVenue(), binding.venueText, binding.venueLayout);
    }

    private void prepareStringDetails(String stringResponse, AppCompatTextView textView, LinearLayoutCompat layout) {
        if (stringResponse != null && stringResponse.length() > 0) {
            textView.setText(stringResponse);
            layout.setVisibility(View.VISIBLE);
        }
    }

    private void preparePlayersListDetails(TeamPlayers playersResponse) {
        List<Squad> playersList = playersResponse.getSquad();
        if (playersList != null && playersList.size() > 0) {
            PlayersAdapter adapter = new PlayersAdapter(TeamDetailsActivity.this, playersList);
            binding.playersRecyclerView.setAdapter(adapter);
        }
    }

    private void getPlayersAndTeamDetails() {
        Call<TeamPlayers> playersCall = apiService.getPlayers(id);

        playersCall.enqueue(new Callback<TeamPlayers>() {
            @Override
            public void onResponse(@NonNull Call<TeamPlayers> call, @NonNull Response<TeamPlayers> response) {
                TeamPlayers teamPlayers = response.body();
                if (teamPlayers != null) {
                    preparePlayersListDetails(teamPlayers);
                    prepareTeamDetails(teamPlayers);

                    name = teamPlayers.getName();
                    shortName = teamPlayers.getShortName();
                    imageUrl = teamPlayers.getCrestUrl();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TeamPlayers> call, @NonNull Throwable t) {

            }
        });
    }
}
