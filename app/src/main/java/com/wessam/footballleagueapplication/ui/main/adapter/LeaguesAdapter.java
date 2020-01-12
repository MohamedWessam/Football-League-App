package com.wessam.footballleagueapplication.ui.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.leagues.Competitions;
import com.wessam.footballleagueapplication.ui.teams.TeamsActivity;
import com.wessam.footballleagueapplication.utils.Constants;


import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {

    private Activity activity;
    private List<Competitions> competitionList;

    public LeaguesAdapter(Activity activity, List<Competitions> competitionList) {
        this.activity = activity;
        this.competitionList = competitionList;
    }

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.league_layout_item, parent, false);
        return new LeaguesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        Competitions competition = competitionList.get(position);
        String name = competition.getName();
        String shortName = competition.getCode();
        String gamesNumber = String.valueOf(competition.getNumberOfAvailableSeasons());
        holder.leagueName.setText(name);
        if (shortName != null){
            holder.shortName.setText(shortName);
        }else {
            holder.shortName.setVisibility(View.GONE);
        }
        holder.gamesNumber.setText(gamesNumber);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, TeamsActivity.class);
            intent.putExtra(Constants.COMPETITION_ID_KEY, String.valueOf(competition.getId()));
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    class LeaguesViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView leagueName, shortName, gamesNumber;

        LeaguesViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.league_name_text);
            shortName = itemView.findViewById(R.id.league_short_name_text);
            gamesNumber = itemView.findViewById(R.id.games_number_text);
        }

    }
}
