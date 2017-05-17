package com.pyrotemplar.refereehelper.Utils;

import android.content.Context;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;


import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.pyrotemplar.refereehelper.R;


/**
 * Created by Manuel Montes de Oca on 5/17/2017.
 */

public class PreferenceWidget extends Preference {

    public ScrollableNumberPicker scrollableNumberPicker;
    private int mTime;


    public PreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PreferenceWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreferenceWidget(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        scrollableNumberPicker = (ScrollableNumberPicker) holder.findViewById(R.id.number_picker);
        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }

    public int getValue(){
       return scrollableNumberPicker.getValue();
    }



}