package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Injection;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ListTeamAdapter;
import com.example.myapplication.main.MainContract;
import com.example.myapplication.main.MainPresenter;
import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MainContract.View {
    private ProgressBar progressBar;
    private ListTeamAdapter adapter;
    private RecyclerView rvTeam;
    private ArrayList<Team> listTeam = new ArrayList<>();

    private final MainPresenter mainPresenter = new MainPresenter(Injection.provideRepository(), this);


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rvTeam = root.findViewById(R.id.rv_team);
        progressBar = root.findViewById(R.id.progresbar);
        rvTeam.setHasFixedSize(true);

        adapter = new ListTeamAdapter(getActivity(),listTeam);
        rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTeam.setHasFixedSize(true);
        rvTeam.setAdapter(adapter);

        mainPresenter.getDataListTeams(getActivity());
        return root;
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}