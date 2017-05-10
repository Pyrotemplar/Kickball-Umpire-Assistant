package com.pyrotemplar.refereehelper.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.pyrotemplar.refereehelper.R;

/**
 * Created by Manuel Montes de Oca on 5/10/2017.
 */

public class ColorPickerDialogFragment extends DialogFragment{

    public static ColorPickerDialogFragment newInstance(int num){
        //todo Change num to something more discriptive
        ColorPickerDialogFragment dialogFragment = new ColorPickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        dialogFragment.setArguments(bundle);

        return dialogFragment;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Team Name and Color").setMessage("Change Team Name and Color").setView(R.layout.color_picker_promp_layout)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        return alertDialog;
    }

}
