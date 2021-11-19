package com.example.myapplication.ui.favorite;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Injection;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ListTeamAdapter;
import com.example.myapplication.adapter.ListTeamFavorite;
import com.example.myapplication.main.FavoritePresenter;
import com.example.myapplication.main.MainContract;
import com.example.myapplication.main.MainPresenter;
import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements MainContract.View {

    private ProgressBar progressBar;
    private ListTeamFavorite adapter;
    private RecyclerView rvTeam;
    private List<Team> listTeam = new ArrayList<>();

    private final static String CHANNEL_ID_DEL = "102";
    private final static int NOTIF_ID_DEL = 11;
    private final static String CHANNEL_NAME_DEL = "notifdel";

    private final FavoritePresenter favoritePresenter = new FavoritePresenter(Injection.provideLocalRepository(), this);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvTeam = root.findViewById(R.id.fav_rv_team);
        progressBar = root.findViewById(R.id.fav_progresbar);
        rvTeam.setHasFixedSize(true);

        adapter = new ListTeamFavorite(getActivity(),listTeam);
        rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTeam.setHasFixedSize(true);
        rvTeam.setAdapter(adapter);

        favoritePresenter.getDataListTeams(getActivity());

        adapter.setItemClickCallback(new ListTeamFavorite.ItemClickCallback() {
            @Override
            public void onItemClick(Team team) {
                showNotifDelete(getView());
            }
        });

        return root;
    }

    public void showNotifDelete(View view) {
        NotificationManager notifonManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builderNotification = new NotificationCompat.Builder(getActivity(), CHANNEL_ID_DEL)
                .setSmallIcon(R.drawable.ic_baseline_delete_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_delete_24))
                .setContentTitle("Data Berhasil di Hapus dari Favorite")
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_DEL,CHANNEL_NAME_DEL, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_NAME_DEL.toString());

            builderNotification.setChannelId(CHANNEL_ID_DEL);
            if (notifonManager != null) {
                notifonManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builderNotification.build();

        if (notifonManager != null) {
            notifonManager.notify(NOTIF_ID_DEL, notification);
        }
    }




    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDataList(List<Team> teamList) {
        listTeam.addAll(teamList);

    }

    @Override
    public void showFailureMessage(String msg) {

    }
}