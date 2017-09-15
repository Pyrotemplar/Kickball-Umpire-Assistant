package com.pyrotemplar.kickballUmpireAssistant.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;
import com.pyrotemplar.kickballUmpireAssistant.ClickerFragment.ClickerView;
import com.pyrotemplar.kickballUmpireAssistant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manuel Montes de Oca on 5/10/2017.
 */

public class NameAndColorPickerDialogFragment extends DialogFragment{

    public static final String TEAM_NAME = "teamName";
    public static final String TEAM_COLOR = "teamColor";
    @BindView(R.id.dialogNameEditView)
    EditText nameView;
    @BindView(R.id.colorPicker)
    ColorPicker colorPicker;
    @BindView(R.id.SVbar)
    SVBar SVbar;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.color_picker_promp_layout, null);
        ButterKnife.bind(this, view);

        Bundle mArgs = getArguments();
        final String buttonPressed = mArgs.getString(ClickerView.SCORE_BOARD_BUTTONS_PRESSED);
        nameView.append(mArgs.getString(TEAM_NAME));
        int color = mArgs.getInt(TEAM_COLOR);
        colorPicker.setShowOldCenterColor(false);
        colorPicker.addSVBar(SVbar);
        if(color != 0)
        colorPicker.setColor(color);
       // colorPicker.setOldCenterColor(colorPicker.getColor());

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Team Name and Color").setMessage("Change Team Name and Color").setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getIntent().putExtra("caller", buttonPressed);
                        getActivity().getIntent().putExtra("teamName", nameView.getText().toString());
                        getActivity().getIntent().putExtra("teamColor", colorPicker.getColor());

                       // getActivity().getIntent().put
                        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
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

}
