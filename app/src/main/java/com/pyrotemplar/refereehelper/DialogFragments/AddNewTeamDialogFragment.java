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

import com.pyrotemplar.refereehelper.R;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.add_new_team_prompt_layout, null);
        ButterKnife.bind(this, view);

        Bundle mArgs = getArguments();

        String message = "Add New Team";


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Team").setMessage(message).setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                         getActivity().getIntent().putExtra("name", addNewTeamNameEditBox.getText().toString());


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
