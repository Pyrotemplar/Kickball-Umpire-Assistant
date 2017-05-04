package com.pyrotemplar.refereehelper.ClickerFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.R;

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

    private ClickerContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clicker_layout_option_2, null);


        //   FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);
        // View rightHandLayoutView = inflater.inflate(R.layout.left_hand_click_layout, null);
        // frameLayout.addView(rightHandLayoutView);
        ButterKnife.bind(this, rootView);

        new ClickerPresenter(this);

        return rootView;
    }

    @Override
    public void setPresenter(ClickerPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick(R.id.ballLayout)
    @Override
    public void ballButtonClicked() {
        mPresenter.incrementBall();
    }

    @OnClick(R.id.strikeLayout)
    @Override
    public void strikeButtonClicked() {
        mPresenter.incrementStrike();
    }

    @OnClick(R.id.foulLayout)
    @Override
    public void foulButtonClicked() {
        mPresenter.incrementFoul();
    }

    @OnClick(R.id.outLayout)
    @Override
    public void outButtonClicked() {
        mPresenter.incrementOut();
    }

    @OnClick(R.id.undoLayout)
    @Override
    public void undoButtonClicked() {
        mPresenter.undo();
    }

    @OnClick(R.id.redoLayout)
    @Override
    public void redoButtonClicked() {
        mPresenter.redo();
    }

    @OnClick(R.id.kickerIsSafeButton)
    @Override
    public void kickerIsSafeButtonClicked() {
        mPresenter.resetCount();
    }

    @OnClick(R.id.runnerScoredButton)
    @Override
    public void runnerScoredButtonClicked() {
        mPresenter.incrementRun();
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
        } else{
            undoLayout.setAlpha(1);
            undoLayout.setClickable(true);
        }

    }

    @Override
    public void updateRedoLayoutVisibility(boolean isNotClickable) {
        if (isNotClickable) {
            redoLayout.setAlpha(.5f);
            redoLayout.setClickable(false);
        } else{
            redoLayout.setAlpha(1);
            redoLayout.setClickable(true);
        }
    }

    @Override
    public void updateInningArrowImageView() {
        inningArrowImageView.setRotation(inningArrowImageView.getRotation() + 180);
    }

    @Override
    public void updateAwayArrowImageView(boolean isVisible) {
        if (isVisible)
            awayArrowImageView.setVisibility(View.VISIBLE);
        else
            awayArrowImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateHomeArrowImageView(boolean isVisible) {
        if (isVisible)
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

}