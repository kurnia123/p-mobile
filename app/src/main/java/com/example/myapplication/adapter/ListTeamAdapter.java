package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Team;

import java.util.List;

public class ListTeamAdapter extends RecyclerView.Adapter<ListTeamAdapter.ListViewHolder> {

    public static final String DETAIL_TEAM = "TEAM";

    private List<Team> listTeam;
    private Context context;

    public ListTeamAdapter(Context context, List<Team> list) {
        this.listTeam = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListTeamAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_team, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTeamAdapter.ListViewHolder holder, int position) {
        Team team = listTeam.get(position);

        Glide.with(holder.itemView.getContext())
                .load(team.getPhoto())
                .apply(new RequestOptions().override(100,100))
                .into(holder.imgPhoto);
        holder.teamName.setText(team.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DETAIL_TEAM, team);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView teamName;
        CardView cardView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_logo_team);
            teamName = itemView.findViewById(R.id.team_name);
            cardView = itemView.findViewById(R.id.card_item);
        }
    }
}
