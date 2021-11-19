package com.example.myapplication.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;

public class DescriptionFragment extends Fragment implements DetailActivity.OnDataReceivedListener {
    private TextView textDescription;
    private DetailModelView detailModelView;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailActivity mActivity = (DetailActivity) getActivity();
        mActivity.setAboutDataListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_description, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textDescription = view.findViewById(R.id.detail_description);

        detailModelView = new ViewModelProvider(requireActivity()).get(DetailModelView.class);
        detailModelView.getTeam().observe(requireActivity(), team -> {
            if (team != null) {
                textDescription.setText(team.getDescription());
            }
        });

    }

    @Override
    public void onDataReceived(DetailModelView team) {

    }
}