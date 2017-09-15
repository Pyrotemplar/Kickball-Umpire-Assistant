package com.pyrotemplar.kickballUmpireAssistant.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;
import com.pyrotemplar.kickballUmpireAssistant.R;


/**
 * Created by Manuel Montes de Oca on 5/17/2017.
 */

class NumberSelectorPreferenceWidget extends Preference {

    private ScrollableNumberPicker scrollableNumberPicker;
    SharedPreferences sharedPreferences;

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        scrollableNumberPicker = (ScrollableNumberPicker) holder.findViewById(R.id.number_picker);
        scrollableNumberPicker.setValue(NumberSelectorPreferenceWidget.this.getPersistedInt(0));
        initDefault();
        scrollableNumberPicker.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                initValues();
                NumberSelectorPreferenceWidget.this.persistInt(value);
            }
        });
    }

    @Override
    protected void onClick() {
        super.onClick();
        scrollableNumberPicker.setValue(scrollableNumberPicker.getValue() + 1);
    }

    private void initValues() {
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_BALL_COUNT_KEY))) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_BALLS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_STRIKE_COUNT_KEY))) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_STRIKE) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_FOUL_COUNT_KEY))) {
            if (getSharedPreferences().getBoolean(getContext().getString(R.string.SP_THREE_FOULS_SETTINGS_KEY), false))
                scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_FOULS) - 2);
            else
                scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_FOULS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_OUT_COUNT_KEY))) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_OUTS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_MAX_NUMBER_INNING_KEY))) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_INNINGS));
            scrollableNumberPicker.setMinValue(1);
        }

    }

    private void initDefault() {
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_BALL_COUNT_KEY))) {
            scrollableNumberPicker.setValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_BALLS));
            setDefaultValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_BALLS));
            scrollableNumberPicker.setValue(sharedPreferences.getInt(getContext().getResources().getString(R.string.SP_STARTING_BALL_COUNT_KEY), 0));

        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_STRIKE_COUNT_KEY))) {
            scrollableNumberPicker.setValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_STRIKE));
            setDefaultValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_STRIKE));
            scrollableNumberPicker.setValue(sharedPreferences.getInt(getContext().getResources().getString(R.string.SP_STARTING_STRIKE_COUNT_KEY), 0));

        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_FOUL_COUNT_KEY))) {
            scrollableNumberPicker.setValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_FOULS));
            setDefaultValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_FOULS));
            scrollableNumberPicker.setValue(  sharedPreferences.getInt(getContext().getResources().getString(R.string.SP_STARTING_FOUL_COUNT_KEY), 0));

        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_STARTING_OUT_COUNT_KEY))) {
            scrollableNumberPicker.setValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_OUTS));
            setDefaultValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_OUTS));
            scrollableNumberPicker.setValue( sharedPreferences.getInt(getContext().getResources().getString(R.string.SP_STARTING_OUT_COUNT_KEY), 0));
        }
        if (NumberSelectorPreferenceWidget.this.getKey().equals(getContext().getString(R.string.SP_MAX_NUMBER_INNING_KEY))) {
            scrollableNumberPicker.setValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_INNING));
            setDefaultValue(getContext().getResources().getInteger(R.integer.DEFAULT_STARTING_INNING));
            scrollableNumberPicker.setValue( sharedPreferences.getInt(getContext().getResources().getString(R.string.SP_MAX_NUMBER_INNING_KEY), 5));
        }

    }

    public NumberSelectorPreferenceWidget(Context context) {
        super(context);
    }

    public NumberSelectorPreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NumberSelectorPreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public NumberSelectorPreferenceWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}