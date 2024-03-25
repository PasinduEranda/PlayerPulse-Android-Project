package com.dev.myapp01;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class TeamFragment extends Fragment {

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_team, container, false);


        View sriLankaTeamView = view.findViewById(R.id.sl);
        View newZealandTeamView = view.findViewById(R.id.nz);
        View indiaTeamView = view.findViewById(R.id.ind);


        sriLankaTeamView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPlayerList(SLTeam.class);
            }
        });
        newZealandTeamView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPlayerList(NZTeam.class);
            }
        });
        indiaTeamView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPlayerList(INDTeam.class);
            }
        });

        return view;
    }

    private void navigateToPlayerList(Class<?> destinationClass) {
        // Create an Intent to start the PlayerListActivity for the specified team
        Intent intent = new Intent(getActivity(), destinationClass);
        startActivity(intent);
    }

}
