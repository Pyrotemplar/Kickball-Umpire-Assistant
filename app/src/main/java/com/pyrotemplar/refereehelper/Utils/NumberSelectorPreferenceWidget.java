package com.pyrotemplar.refereehelper.Utils;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;
import com.pyrotemplar.refereehelper.R;


/**
 * Created by Manuel Montes de Oca on 5/17/2017.
 */

public class NumberSelectorPreferenceWidget extends Preference {

    private ScrollableNumberPicker scrollableNumberPicker;

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        scrollableNumberPicker = (ScrollableNumberPicker) holder.findViewById(R.id.number_picker);
        scrollableNumberPicker.setValue(NumberSelectorPreferenceWidget.this.getPersistedInt(0));
        scrollableNumberPicker.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                setMax();
                NumberSelectorPreferenceWidget.this.persistInt(value);
            }
        });
    }

    @Override
    protected void onClick() {
        super.onClick();
        scrollableNumberPicker.setValue(scrollableNumberPicker.getValue() + 1);
    }

    void setMax() {
        if (NumberSelectorPreferenceWidget.this.getKey() == getContext().getString(R.string.SP_STARTING_BALL_COUNT_KEY)) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_BALLS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey() == getContext().getString(R.string.SP_STARTING_STRIKE_COUNT_KEY)) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_STRIKE) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey() == getContext().getString(R.string.SP_STARTING_FOUL_COUNT_KEY)) {
            if (getSharedPreferences().getBoolean(getContext().getString(R.string.SP_THREE_FOULS_SETTINGS_KEY), false))
                scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_FOULS) - 2);
            else
                scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_FOULS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey() == getContext().getString(R.string.SP_STARTING_OUT_COUNT_KEY)) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_OUTS) - 1);
        }
        if (NumberSelectorPreferenceWidget.this.getKey() == getContext().getString(R.string.SP_MAX_NUMBER_INNING_KEY)) {
            scrollableNumberPicker.setMaxValue(getContext().getResources().getInteger(R.integer.DEFAULT_MAX_INNINGS));
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