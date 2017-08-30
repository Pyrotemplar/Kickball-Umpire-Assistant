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
import android.widget.TextView;

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
    @BindView(R.id.teamSelectionDropDownSpinner)
    Spinner teamSelectionDropDownSpinner;
    @BindView(R.id.teamSelectionNameTextView)
    TextView teamSelectionNameTextView;

    int teamColor;
    String teamName;


    List<Team> teamList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.team_selection_layout, null);
        ButterKnife.bind(this, view);

       Realm realm = Realm.getDefaultInstance();
        RealmResults<Team> results = realm.where(Team.class).findAllAsync();
        teamList = realm.copyFromRealm(results);

        teamSelectionDropDownSpinner.setOnItemSelectedListener(this);

        TeamSelectionSpinnerAdapter teamSelectionSpinnerAdapter = new TeamSelectionSpinnerAdapter(getContext(), teamList);
        teamSelectionDropDownSpinner.setAdapter(teamSelectionSpinnerAdapter);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Team Name and Color").setMessage("Change Team Name and Color").setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                         getActivity().getIntent().putExtra(TEAM_NAME, teamName );
                          getActivity().getIntent().putExtra(TEAM_COLOR, teamColor);

                       getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
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
        teamSelectionNameTextView.setText(teamName);
        teamSelectionNameTextView.setTextColor(teamColor);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
