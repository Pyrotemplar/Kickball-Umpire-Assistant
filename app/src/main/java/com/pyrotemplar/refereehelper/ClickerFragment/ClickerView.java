package com.pyrotemplar.refereehelper.ClickerFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.pyrotemplar.refereehelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class ClickerView extends Fragment implements ClickerContract.View {

    @BindView(R.id.awayTeamNameTextView)
    TextView awayTeamNameTextView;
    @BindView(R.id.homeTeamNameTextView)
    TextView homeTeamNameTextView;
    @BindView(R.id.awayTeamScoreTextView)
    TextView awayTeamScoreTextView;
    @BindView(R.id.homeTeamScoreTextView)
    TextView homeTeamScoreTextView;
    @BindView(R.id.inningTextViewCount)
    TextView inningTextViewCount;
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
    @BindView(R.id.playUpdateView)
    TextView playUpdateTextView;

    private ClickerContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clicker_layout, null);


        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);
        View rightHandLayoutView = inflater.inflate(R.layout.right_hand_clicker_layout, null);
        frameLayout.addView(rightHandLayoutView);
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

    @OnClick(R.id.ballButton)
    @Override
    public void ballButtonClicked() {
        mPresenter.incrementBall();
    }

    @OnClick(R.id.strikeButton)
    @Override
    public void strikeButtonClicked() {
        mPresenter.incrementStrike();
    }

    @OnClick(R.id.foulButton)
    @Override
    public void foulButtonClicked() {
        mPresenter.incrementFoul();
    }

    @OnClick(R.id.outButton)
    @Override
    public void outButtonClicked() {
        mPresenter.incrementOut();
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
        inningTextViewCount.setText(inning);
    }

    @Override
    public void updateGameClockTextView(String gameClock) {
        gameClockTextView.setText(gameClock);
    }

    @Override
    public void updatePlayViewTextView(String playString) {
        playUpdateTextView.setText(playString);
    }

}