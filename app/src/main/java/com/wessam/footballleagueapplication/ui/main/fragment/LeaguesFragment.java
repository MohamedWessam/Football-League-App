package com.wessam.footballleagueapplication.ui.main.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.leagues.Competitions;
import com.wessam.footballleagueapplication.data.db.leagues.Leagues;
import com.wessam.footballleagueapplication.data.network.ApiService;
import com.wessam.footballleagueapplication.data.network.RetrofitClient;
import com.wessam.footballleagueapplication.databinding.FragmentLeaguesBinding;
import com.wessam.footballleagueapplication.ui.main.adapter.LeaguesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaguesFragment extends Fragment {

    private FragmentLeaguesBinding binding;
    private ApiService apiService;

    public LeaguesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_leagues, container, false);

        binding.leaguesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        getLeagueDetails();

        return binding.getRoot();
    }


    private void prepareLeaguesListDetails(Leagues leaguesResponse) {
        List<Competitions> competitionsList = leaguesResponse.getCompetitions();
        if (competitionsList != null && competitionsList.size() > 0) {
            LeaguesAdapter adapter = new LeaguesAdapter(getActivity(), competitionsList);
            binding.leaguesRecyclerView.setAdapter(adapter);
        }
    }

    private void getLeagueDetails() {
        Call<Leagues> leaguesCall = apiService.getAllLeagues();

        leaguesCall.enqueue(new Callback<Leagues>() {
            @Override
            public void onResponse(@NonNull Call<Leagues> call, @NonNull Response<Leagues> response) {
                Leagues leagues = response.body();
                if (leagues != null) {
                    prepareLeaguesListDetails(leagues);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Leagues> call, @NonNull Throwable t) {
            }
        });
    }

}