package com.pyrotemplar.refereehelper.DialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.DataObjects.RuleBook;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.RulesFragment.RuleBookView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manny on 7/9/2017.
 */

public class AddNewRulesBookLinkDialogFragment extends DialogFragment {

    public static final String RULE_BOOK = "Rule Book";
    public static final String SAVE = "Save";
    public static final String CANCEL = "Cancel";
    private static final String ADD = "Add" ;
    public static final String EDIT_RULE_BOOK = "Edit Rule book";
    public static final String ADD_NEW_RULE_BOOK = "Add New Rule book";
    @BindView(R.id.ruleTitleEditTextView)
    EditText ruleTitleEditTextView;
    @BindView(R.id.ruleBookURLEditTextView)
    EditText ruleBookURLEditTextView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.add_new_rulebook_link_prompt_layout, null);
        ButterKnife.bind(this, view);

        Bundle mArgs = getArguments();
        final int position;
        String message;
        String positiveButton;
        if (mArgs != null) {
            message = EDIT_RULE_BOOK;
            positiveButton = SAVE;
            position = mArgs.getInt(RuleBookView.RULE_BOOK_POSITION);
            ruleTitleEditTextView.setText(mArgs.getString(RuleBookView.RULE_BOOK_TITLE));
            ruleBookURLEditTextView.append(mArgs.getString(RuleBookView.RULE_BOOK_URL));

        } else {
            message = ADD_NEW_RULE_BOOK;
            positiveButton = ADD;
            position = -1;
        }
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(RULE_BOOK).setMessage(message).setView(view)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                            getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_POSITION, position);
                            getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_TITLE, ruleTitleEditTextView.getText().toString());
                            getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_URL, ruleBookURLEditTextView.getText().toString());

                            // getActivity().getIntent().put
                            getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());
                       // } else {
                      //      ruleBookURLEditTextView.setError("URL must end in .PDF or .pdf");
                      //  }
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

  /*  @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {


            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (ruleBookURLEditTextView.getText().toString().endsWith(".pdf") || ruleBookURLEditTextView.getText().toString().endsWith(".PDF")) {
                      //  getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_POSITION, position);
                        getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_TITLE, ruleTitleEditTextView.getText().toString());
                        getActivity().getIntent().putExtra(RuleBookView.RULE_BOOK_URL, ruleBookURLEditTextView.getText().toString());

                        // getActivity().getIntent().put
                        getTargetFragment().onActivityResult(1, Activity.RESULT_OK, getActivity().getIntent());

                        d.dismiss();
                    } else
                        ruleBookURLEditTextView.setError("URL must end in .PDF or .pdf");

                }
            });
        }
    }*/
}




