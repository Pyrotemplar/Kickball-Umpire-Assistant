package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;
import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.LeagueFragment.LeagueView;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.RulesFragment.RuleBookView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manny on 8/16/2017.
 */

public class AddNewTeamDialogFragment extends DialogFragment {
    @BindView(R.id.addNewTeamNameEditBox)
    EditText addNewTeamNameEditBox;
    @BindView(R.id.addNewTeamCaptainNameEditBox)
    EditText addNewTeamCaptainNameEditBox;
    @BindView(R.id.addNewTeamCaptainEmailEditBoxEditBox)
    EditText addNewTeamCaptainEmailEditBoxEditBox;
    @BindView(R.id.addNewTeamCaptainPhoneNumberEditBoxEditBox)
    EditText addNewTeamCaptainPhoneNumberEditBoxEditBox;
    @BindView(R.id.addNewTeamColorPicker)
    ColorPicker colorPicker;
    @BindView(R.id.addNewTeamColorPickerSVbar)
    SVBar SVbar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.add_new_team_prompt_layout, null);
        ButterKnife.bind(this, view);

        Bundle mArgs = getArguments();

        String message;

        colorPicker.setShowOldCenterColor(false);
        colorPicker.addSVBar(SVbar);

        if (mArgs != null) {
            message = "Edit Team Info";
            addNewTeamNameEditBox.append(mArgs.getString(LeagueView.TEAM_NAME));
            addNewTeamCaptainNameEditBox.append(mArgs.getString(LeagueView.TEAM_CAPTAIN_NAME));
            addNewTeamCaptainEmailEditBoxEditBox.append(mArgs.getString(LeagueView.TEAM_CAPTAIN_EMAIL));
            addNewTeamCaptainPhoneNumberEditBoxEditBox.append(Integer.toString(mArgs.getInt(LeagueView.TEAM_CAPTAIN_NUMBER)));
            int color = mArgs.getInt(LeagueView.TEAM_COLOR);
            if (color != 0)
                colorPicker.setColor(color);
        } else {
            message  = "Add New Team";
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("TEAM").setMessage(message).setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getActivity().getIntent().putExtra(LeagueView.TEAM_NAME, addNewTeamNameEditBox.getText().toString());
                        getActivity().getIntent().putExtra(LeagueView.TEAM_CAPTAIN_NAME, addNewTeamCaptainNameEditBox.getText().toString());
                        getActivity().getIntent().putExtra(LeagueView.TEAM_CAPTAIN_EMAIL, addNewTeamCaptainEmailEditBoxEditBox.getText().toString());
                        getActivity().getIntent().putExtra(LeagueView.TEAM_CAPTAIN_NUMBER, addNewTeamCaptainPhoneNumberEditBoxEditBox.getText().toString());
                        getActivity().getIntent().putExtra(LeagueView.TEAM_COLOR, colorPicker.getColor());

                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });


        // Create the AlertDialog object and return it

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return alertDialog;
    }


}
