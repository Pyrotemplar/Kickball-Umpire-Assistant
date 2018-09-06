package com.pyrotemplar.refereehelper.Settings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerPresenter;
import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.DataObjects.dataHelper;
import com.pyrotemplar.refereehelper.DialogFragments.ConfirmationDialogFragment;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.TabAcivity.TabActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Manuel Montes de Oca on 5/4/2017.
 */

public class SettingsView extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, SettingsContract.View {

    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String POSITIVE_BUTTON = "positiveButton";
    public static final int REQUEST_CODE_RESET = 40;
    public static final String CLICKER_DATA_RESET = "Clicker Data reset";
    public static final String MARKET_DETAILS_ID = "market://details?id=";
    public static final String UNABLE_TO_FIND_MARKET_APP = " unable to find market app";
    public static final String RESET_CLICKER = "Reset Clicker";
    public static final String START_A_NEW_GAME = "Start a New Game?";
    public static final String RESET = "Reset";
    public static final String TAG = "TAG";

    SharedPreferences sharedPreferences;
    private SettingsContract.Presenter mPresenter;
    private ClickerPresenter mClickerPresenter;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preference);
        Preference preference =  findPreference(getResources().getString(R.string.SP_DEFAULT_EMAIL_SETTINGS_KEY));
        preference.setSummary(sharedPreferences.getString(getResources().getString(R.string.SP_DEFAULT_EMAIL_SETTINGS_KEY), "Default Email to send game score"));




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //rootView.setBackgroundResource(R.drawable.main_background);

        new SettingsPresenter(this);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ClickerView.resetClickerData();
             Toast.makeText(getContext(), CLICKER_DATA_RESET, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenter(SettingsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.setting_share_preference_key));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(getResources().getString(R.string.SP_RESET_CLICKER_DATA_SETTINGS_KEY)))
            resetClicker();
        if (preference.getKey().equals(getResources().getString(R.string.SP_RATE_APP_KEY)))
            rateApp();
        if (preference.getKey().equals(getResources().getString(R.string.SP_SHARE_SCORE_SETTINGS_KEY)))
            shareScore();
        if (preference.getKey().equals(getResources().getString(R.string.SP_DEFAULT_EMAIL_SETTINGS_KEY)))
            setDefaultEmail(preference);

        return super.onPreferenceTreeClick(preference);
    }

    private void shareScore() {

        String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
        Intent shareScoreIntent = new Intent(Intent.ACTION_SEND);
        shareScoreIntent.setType("text/plain");
        shareScoreIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {sharedPreferences.getString(getResources().getString(R.string.SP_DEFAULT_EMAIL_SETTINGS_KEY),"")});
        shareScoreIntent.putExtra(Intent.EXTRA_SUBJECT, "Final Score between " + ClickerPresenter.awayTeamName + " vs. " + ClickerPresenter.homeTeamName + " on " + date);
        shareScoreIntent.putExtra(Intent.EXTRA_TEXT, "Final Score between " + ClickerPresenter.awayTeamName + " vs. " + ClickerPresenter.homeTeamName + " on " + date + "\n" + ClickerPresenter.awayTeamName +
                " - " + ClickerPresenter.awayTeamScore + "\n" + ClickerPresenter.homeTeamName + " - " + ClickerPresenter.homeTeamScore);
        startActivity(Intent.createChooser(shareScoreIntent, "Share Game Score:"));

    }

    private void rateApp() {

        Uri uri = Uri.parse(MARKET_DETAILS_ID + getActivity().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), UNABLE_TO_FIND_MARKET_APP, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        TabActivity.isPreferenceUpdated = true;

        Preference preference = findPreference(key);

        /* update summary */
        if (key.equals("defaultEmailSettingKey")) {
            preference.setSummary(sharedPreferences.getString(key, "Default Email to send game score"));

        }
    }

    private void resetClicker() {

        Bundle mArgs = new Bundle();
        mArgs.putString(TITLE, RESET_CLICKER);
        mArgs.putString(MESSAGE, START_A_NEW_GAME);
        mArgs.putString(POSITIVE_BUTTON, RESET);

        ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
        confirmationDialogFragment.setArguments(mArgs);
        confirmationDialogFragment.setTargetFragment(this, REQUEST_CODE_RESET);
        confirmationDialogFragment.show(getFragmentManager(), TAG);
    }

    private void setDefaultEmail(Preference preference){
        if (preference instanceof EditTextPreference){
        //
            if (preference instanceof EditTextPreference){
                EditTextPreference editTextPreference =  (EditTextPreference)preference;
                preference.setSummary(editTextPreference.getText());
            }else{
                preference.setSummary("Default Email to send game score");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //register the preferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {

        //unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity activity = getActivity();
            if (activity != null)
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }
    }

}
