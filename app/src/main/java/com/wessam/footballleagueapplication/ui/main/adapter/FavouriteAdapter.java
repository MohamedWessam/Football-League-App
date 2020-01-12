package com.wessam.footballleagueapplication.ui.main.adapter;

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
import com.wessam.footballleagueapplication.data.db.room.FavouriteList;
import com.wessam.footballleagueapplication.ui.teamdetails.TeamDetailsActivity;
import com.wessam.footballleagueapplication.utils.Constants;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private Activity activity;
    private List<FavouriteList> favouriteList;

    public FavouriteAdapter(Activity activity, List<FavouriteList> favouriteList) {
        this.activity = activity;
        this.favouriteList = favouriteList;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.teams_layout_item, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        FavouriteList list = favouriteList.get(position);
        String name = list.getName();
        String shortName = list.getShortName();
        String imageUrl = list.getCrestUrl();

        holder.teamName.setText(name);
        holder.teamShortName.setText(shortName);
        holder.setTeamImage(imageUrl);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, TeamDetailsActivity.class);
            intent.putExtra(Constants.TEAM_ID_KEY, String.valueOf(list.getId()));
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    class FavouriteViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView teamName, teamShortName;
        private AppCompatImageView teamImage;

        FavouriteViewHolder(@NonNull View itemView) {
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
