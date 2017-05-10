package com.pyrotemplar.refereehelper.ClickerFragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.Utils.ColorPickerDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class ClickerView extends Fragment implements ClickerContract.View {

    @BindView(R.id.awayTeamScoreTextView)
    TextView awayTeamScoreTextView;
    @BindView(R.id.homeTeamScoreTextView)
    TextView homeTeamScoreTextView;
    @BindView(R.id.inningCountTextView)
    TextView inningCountTextView;
    @BindView(R.id.ballCountTextView)
    TextView ballCountTextView;
    @BindView(R.id.strikeCountTextView)
    TextView strikeCountTextView;
    @BindView(R.id.foulCountTextView)
    TextView foulCountTextView;
    @BindView(R.id.outCountTextView)
    TextView outCountTextView;
    @BindView(R.id.gameClockTextView)
    TextView gameClockTextView;
    @BindView(R.id.inningArrowImageView)
    ImageView inningArrowImageView;
    @BindView(R.id.awayArrowImageView)
    ImageView awayArrowImageView;
    @BindView(R.id.homeArrowImageView)
    ImageView homeArrowImageView;
    @BindView(R.id.undoLayout)
    LinearLayout undoLayout;
    @BindView(R.id.redoLayout)
    LinearLayout redoLayout;

    private boolean isHapticFeedbackEnabled;
    private SharedPreferences sharedPreferences;
    private ColorPickerDialogFragment colorPickerDialogFragment;

    private ClickerContract.Presenter mPresenter;
    private boolean isViewShown;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clicker_layout_option_2, null);
        ButterKnife.bind(this, rootView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        colorPickerDialogFragment = new ColorPickerDialogFragment();
        colorPickerDialogFragment.setTargetFragment(this, 1);


        new ClickerPresenter(this);
        if (!isViewShown) {
            //  updateSharePreferences() contains logic to update Share preference when page is selected
            updateSharePreferences();
        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //todo: update the teams name and color from intent.
            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenter(ClickerPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick(R.id.awayTeamBannerLayout)
    @Override
    public void AwayTeamButtonClicked(View view) {
        colorPickerDialogFragment.show(getFragmentManager(), "testName");
        mPresenter.updateAwayTeamBanner();
    }

    @OnClick(R.id.homeTeamBannerLayout)
    @Override
    public void homeTeamButtonClicked(View view) {
        colorPickerDialogFragment.show(getFragmentManager(), "testName");
        mPresenter.updateHomeTeamBanner();
    }

    @OnClick(R.id.ballLayout)
    @Override
    public void ballButtonClicked(View view) {

        mPresenter.incrementBall();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);

    }

    @OnClick(R.id.strikeLayout)
    @Override
    public void strikeButtonClicked(View view) {
        mPresenter.incrementStrike();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.foulLayout)
    @Override
    public void foulButtonClicked(View view) {
        mPresenter.incrementFoul();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.outLayout)
    @Override
    public void outButtonClicked(View view) {
        mPresenter.incrementOut();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.undoLayout)
    @Override
    public void undoButtonClicked(View view) {
        mPresenter.undo();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.redoLayout)
    @Override
    public void redoButtonClicked(View view) {
        mPresenter.redo();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.kickerIsSafeButton)
    @Override
    public void kickerIsSafeButtonClicked(View view) {
        mPresenter.resetCount();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @OnClick(R.id.runnerScoredButton)
    @Override
    public void runnerScoredButtonClicked(View view) {
        mPresenter.incrementRun();
        if (isHapticFeedbackEnabled)
            hapticFeedback(view);
    }

    @Override
    public void updateBallCountTextView(String ballCount) {
        ballCountTextView.setText(ballCount);
    }

    @Override
    public void updateStrikeCountTextView(String strikeCount) {
        strikeCountTextView.setText(strikeCount);
    }

    @Override
    public void updateFoulCountTextView(String foulCount) {
        foulCountTextView.setText(foulCount);
    }

    @Override
    public void updateOutCountTextView(String outCount) {
        outCountTextView.setText(outCount);
    }

    @Override
    public void updateHomeScoreTextView(String homeScore) {
        homeTeamScoreTextView.setText(homeScore);
    }

    @Override
    public void updateAwayScoreTextView(String awayScore) {
        awayTeamScoreTextView.setText(awayScore);
    }

    @Override
    public void updateInningTextView(String inning) {
        inningCountTextView.setText(inning);
    }

    @Override
    public void updateUndoLayoutVisibility(boolean isNotClickable) {

        if (isNotClickable) {
            undoLayout.setAlpha(.5f);
            undoLayout.setClickable(false);
        } else {
            undoLayout.setAlpha(1);
            undoLayout.setClickable(true);
        }
    }

    @Override
    public void updateRedoLayoutVisibility(boolean isNotClickable) {

        if (isNotClickable) {
            redoLayout.setAlpha(.5f);
            redoLayout.setClickable(false);
        } else {
            redoLayout.setAlpha(1);
            redoLayout.setClickable(true);
        }
    }

    @Override
    public void updateInningArrowImageView(boolean isBottomOfInning) {

        if (isBottomOfInning)
            inningArrowImageView.setRotation(180);
        else
            inningArrowImageView.setRotation(0);
    }

    @Override
    public void updateAwayArrowImageView(boolean isBottomOfInning) {
        if (isBottomOfInning)
            awayArrowImageView.setVisibility(View.INVISIBLE);
        else
            awayArrowImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void updateHomeArrowImageView(boolean isBottomOfInning) {
        if (isBottomOfInning)
            homeArrowImageView.setVisibility(View.VISIBLE);
        else
            homeArrowImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateGameClockTextView(String gameClock) {
        gameClockTextView.setText(gameClock);
    }

    @Override
    public void updatePlayViewTextView(String playString) {
        //   playUpdateTextView.setText(playString);
    }

    private void hapticFeedback(View view) {

        if (view.isHapticFeedbackEnabled()) {
            view.setHapticFeedbackEnabled(true);
        }
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
    }

    @Override
    public void updateSharePreferences() {

        isHapticFeedbackEnabled = sharedPreferences.getBoolean(getResources().getString(R.string.SP_HAPTIC_FEEDBACK_SETTINGS_KEY), false);
        mPresenter.setThreeFoulOption(sharedPreferences.getBoolean(getResources().getString(R.string.SP_THREE_FOULS_SETTINGS_KEY), false));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getView() != null) {
            isViewShown = true;
            //  updateSharePreferences() contains logic to update Share preference when page is selected
            updateSharePreferences();
        } else {
            isViewShown = false;
        }
    }
}