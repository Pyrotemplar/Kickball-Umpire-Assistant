package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    TextInputEditText timeEditTextView;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    //  @BindView(R.id.countUpSwitch)
    // Switch countUpSwitch;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.game_clock_prompt_layout, null);
        ButterKnife.bind(this, view);


        //   countUpSwitch.setChecked(mArgs.getBoolean("isCountUpEnabled"));


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Game Clock").setMessage("Adjust Game Clock").setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {

            Bundle mArgs = getArguments();
            final String buttonPressed = mArgs.getString(ClickerView.GAME_CLOCK_BUTTONS_PRESSED);
            timeEditTextView.append(mArgs.getString("gameClockTime"));

            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (!timeEditTextView.getText().toString().isEmpty() && Integer.parseInt(timeEditTextView.getText().toString()) > 0
                            && Integer.parseInt(timeEditTextView.getText().toString()) <= 180
                            && timeEditTextView.getText().toString().matches("[0-9]+")) {

                        getActivity().getIntent().putExtra("caller", buttonPressed);
                        getActivity().getIntent().putExtra("newTime", timeEditTextView.getText().toString());
                        //     getActivity().getIntent().putExtra("isCountUpEnabled", countUpSwitch.isChecked());

                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                        d.dismiss();
                    } else
                        textInputLayout.setError(getResources().getString(R.string.MS_ERROR_WARNING_EDIT_GAME_CLOCK));
                }
            });
        }
    }
}
