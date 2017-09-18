package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.pyrotemplar.refereehelper.Adapters.TeamSelectionSpinnerAdapter;
import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Manuel Montes de Oca on 8/29/2017.
 */

public class TeamSelectionDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    public static final String TEAM_NAME = "teamName";
    public static final String TEAM_COLOR = "teamColor";
    public static final String DONE = "Done";
    public static final String CANCEL = "cancel";
    public static final String TEAM = "team";
    public static final String SELECT_HOME_TEAM = "Select Home Team";
    public static final String SELECT_AWAY_TEAM = "Select Away Team";
    @BindView(R.id.teamSelectionDropDownSpinner)
    Spinner teamSelectionDropDownSpinner;

    int teamColor;
    String teamName;

    List<Team> teamList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.team_selection_layout, null);
        ButterKnife.bind(this, view);

        Bundle mArgs = getArguments();
        final int teamPressed = mArgs.getInt(TEAM);
        final String selectTeamTitle;
        if (teamPressed == 1) {
            selectTeamTitle = SELECT_HOME_TEAM;
        } else
            selectTeamTitle = SELECT_AWAY_TEAM;

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Team> results = realm.where(Team.class).findAllSorted(TEAM_NAME);
        teamList = realm.copyFromRealm(results);

        teamSelectionDropDownSpinner.setOnItemSelectedListener(this);

        TeamSelectionSpinnerAdapter teamSelectionSpinnerAdapter = new TeamSelectionSpinnerAdapter(getContext(), teamList);

        teamSelectionDropDownSpinner.setAdapter(teamSelectionSpinnerAdapter);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(selectTeamTitle).setView(view)
                .setPositiveButton(DONE, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getActivity().getIntent().putExtra(TEAM_NAME, teamName);
                        getActivity().getIntent().putExtra(TEAM_COLOR, teamColor);

                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        return alertDialog;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Team team = teamList.get(position);
        teamName = team.getTeamName();
        teamColor = team.getTeamColor();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
