package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.pyrotemplar.refereehelper.R;

/**
 * Created by Manuel Montes de Oca on 5/23/2017.
 */

public class ConfirmationDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String POSITIVE_BUTTON = "positiveButton";
    public static final String CANCEL = "Cancel";
    public static final String TEAM_TO_DELETE = "teamToDelete";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();

        String title = mArgs.getString(TITLE);
        String message = mArgs.getString(MESSAGE);
        String positiveButton = mArgs.getString(POSITIVE_BUTTON);
        final String teamToDelete = mArgs.getString(TEAM_TO_DELETE);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       if(teamToDelete != null){
                           getActivity().getIntent().putExtra(TEAM_TO_DELETE, teamToDelete);
                       }
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
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return alertDialog;
    }
}
