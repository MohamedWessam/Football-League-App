package com.wessam.footballleagueapplication.ui.teams.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.teams.Teams;
import com.wessam.footballleagueapplication.ui.teamdetails.TeamDetailsActivity;
import com.wessam.footballleagueapplication.utils.Constants;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private Activity activity;
    private List<Teams> teamsList;

    public TeamsAdapter(Activity activity, List<Teams> teamsList) {
        this.activity = activity;
        this.teamsList = teamsList;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.teams_layout_item, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Teams teams = teamsList.get(position);
        String name = teams.getName();
        String shortName = teams.getShortName();
        String imageUrl = teams.getCrestUrl();

        holder.teamName.setText(name);
        holder.teamShortName.setText(shortName);
        holder.setTeamImage(imageUrl);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, TeamDetailsActivity.class);
            intent.putExtra(Constants.TEAM_ID_KEY, String.valueOf(teams.getId()));
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    class TeamsViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView teamName, teamShortName;
        private AppCompatImageView teamImage;

        TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamShortName = itemView.findViewById(R.id.team_short_name);
            teamImage = itemView.findViewById(R.id.team_image_view);
        }

        void setTeamImage(String url) {
            Picasso.get().load(url).placeholder(R.drawable.no_image).into(teamImage);
        }
    }
}
