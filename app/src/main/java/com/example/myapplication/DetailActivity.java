package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.adapter.ListTeamAdapter;
import com.example.myapplication.adapter.SectionPagerAdapter;
import com.example.myapplication.data.local.TeamDatabase;
import com.example.myapplication.model.Team;
import com.example.myapplication.ui.detail.DetailModelView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    DetailModelView detailModelView;
    ImageView imageLogoTeam;
    TextView nameTeam;
    TextView yearTeam;
    Team team;

    private final static String CHANNEL_ID = "101";
    private final static int NOTIF_ID = 10;
    private final static String CHANNEL_NAME = "notifadd";

    private TeamDatabase teamDatabase;

    private OnDataReceivedListener mAboutDataListener;

    public interface OnDataReceivedListener {
        void onDataReceived(DetailModelView detailModelView);
    }

    public void setAboutDataListener(OnDataReceivedListener listener) {
        this.mAboutDataListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        teamDatabase = TeamDatabase.getTeamDatabase(this);

        team = getIntent().getExtras().getParcelable(ListTeamAdapter.DETAIL_TEAM);
        imageLogoTeam = findViewById(R.id.img_logo_team);
        nameTeam = findViewById(R.id.name_team);
        yearTeam = findViewById(R.id.year_team);

//        SET DATA DARI INTENT
        Glide.with(this)
                .load(team.getPhoto())
                .apply(new RequestOptions().override(120,120))
                .into(imageLogoTeam);
        nameTeam.setText(team.getName());
        yearTeam.setText(team.getYear());

        SectionPagerAdapter sectionsPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Insert Data ke Room
                teamDatabase.teamsDao().insert(team);
                showNotif(view);
//            Menampilkan Notifikasi Data Berhasil disimpan
                Snackbar.make(view, "Data disimpan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        detailModelView = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailModelView.class);
        detailModelView.setTeam(team);
    }

    public void showNotif(View view) {
        NotificationManager notifonManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builderNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_add_circle_24))
                .setContentTitle("Data Berhasil di tambahkan di Favorite")
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_NAME.toString());

            builderNotification.setChannelId(CHANNEL_ID);
            if (notifonManager != null) {
                notifonManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builderNotification.build();

        if (notifonManager != null) {
            notifonManager.notify(NOTIF_ID, notification);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DESCRIPTION", team.getDescription());
    }
}