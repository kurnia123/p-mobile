package com.example.myapplication.adapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.local.TeamDatabase;
import com.example.myapplication.model.Team;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ListTeamFavorite extends RecyclerView.Adapter<ListTeamFavorite.ListViewFavoriteHolder> {

    public static final String DETAIL_TEAM = "TEAM";
    private TeamDatabase teamDatabase;

    private List<Team> listTeamFavorite;
    private Context context;

    private ItemClickCallback itemClickCallback;

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public ListTeamFavorite(Context context, List<Team> list) {
        this.listTeamFavorite = list;
        this.context = context;
        teamDatabase = TeamDatabase.getTeamDatabase(context);
    }

    @NonNull
    @Override
    public ListViewFavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite, parent, false);
        return new ListViewFavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewFavoriteHolder holder, int position) {
        Team team = listTeamFavorite.get(position);

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

        holder.btnDeleetFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamDatabase.teamsDao().delete(team);
                itemClickCallback.onItemClick(listTeamFavorite.get(holder.getAdapterPosition()));
            }
        });
    }



    @Override
    public int getItemCount() {
        return listTeamFavorite.size();
    }

    public class ListViewFavoriteHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView teamName;
        CardView cardView;
        Button btnDeleetFavorite;

        public ListViewFavoriteHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_logo_team_favorite);
            teamName = itemView.findViewById(R.id.team_favorite_name);
            cardView = itemView.findViewById(R.id.card_item_favorite);
            btnDeleetFavorite = itemView.findViewById(R.id.btn_favorite_delete);
        }
    }

    public interface ItemClickCallback {
        void onItemClick(Team team);
    }
}
