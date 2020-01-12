package com.wessam.footballleagueapplication.ui.teamdetails.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wessam.footballleagueapplication.R;
import com.wessam.footballleagueapplication.data.db.players.Squad;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {

    private Activity activity;
    private List<Squad> playersList;

    public PlayersAdapter(Activity activity, List<Squad> playersList) {
        this.activity = activity;
        this.playersList = playersList;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.player_layout_item, parent, false);
        return new PlayersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        Squad players = playersList.get(position);

        holder.name.setText(players.getName());
        holder.position.setText(players.getPosition());
        holder.nationality.setText(players.getNationality());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    class PlayersViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView name, position, nationality;

        PlayersViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name_text);
            position = itemView.findViewById(R.id.player_position_text);
            nationality = itemView.findViewById(R.id.player_nationality_text);
        }
    }
}
