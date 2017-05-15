package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manny on 5/12/2017.
 */

public class GameClockDialogFragment extends DialogFragment {

    @BindView(R.id.timeEditTextView)
    EditText timeEditTextView;
  //  @BindView(R.id.countUpSwitch)
   // Switch countUpSwitch;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.game_clock_prompt_layout, null);
        ButterKnife.bind(this, view);


        Bundle mArgs = getArguments();
        final String buttonPressed = mArgs.getString(ClickerView.GAME_CLOCK_BUTTONS_PRESSED);
        timeEditTextView.append(mArgs.getString("gameClockTime"));

     //   countUpSwitch.setChecked(mArgs.getBoolean("isCountUpEnabled"));


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Game Clock").setMessage("Adjust Game Clock").setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getIntent().putExtra("caller", buttonPressed);
                        getActivity().getIntent().putExtra("newTime", timeEditTextView.getText().toString());
                   //     getActivity().getIntent().putExtra("isCountUpEnabled", countUpSwitch.isChecked());

                        getTargetFragment().onActivityResult(2, Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        return alertDialog;
    }
}
