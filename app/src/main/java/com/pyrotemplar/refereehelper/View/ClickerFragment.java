package com.pyrotemplar.refereehelper.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Presenter.ClickerPresenter;
import com.pyrotemplar.refereehelper.R;


import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class ClickerFragment extends Fragment implements ClickerFragmentView {

    @BindView(R.id.awayTeamNameTextView)
    TextView getHomeTeamNameTextView;
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

    private ClickerPresenter clickerPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clicker_layout, null);


        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);
        View rightHandLayoutView = inflater.inflate(R.layout.right_hand_clicker_layout, null);
        frameLayout.addView(rightHandLayoutView);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        clickerPresenter = new ClickerPresenter(this);
    }

    @OnClick(R.id.ballButton)
    @Override
    public void ballButtonClicked() {
        clickerPresenter.ballButtonClicked();
    }

    @OnClick(R.id.strikeButton)
    @Override
    public void strikeButtonClicked() {
        clickerPresenter.strikeButtonClicked();
    }

    @OnClick(R.id.foulButton)
    @Override
    public void foulButtonClicked() {
        clickerPresenter.foulButtonClicked();
    }

    @OnClick(R.id.outButton)
    @Override
    public void outButtonClicked() {
        clickerPresenter.outButtonClicked();
    }

    @OnClick(R.id.kickerIsSafeButton)
    @Override
    public void kickerIsSafeButtonClicked() {
        clickerPresenter.kickerIsSafeButtonClicked();
    }

    @OnClick(R.id.runnerScoredButton)
    @Override
    public void runnerScoredButtonClicked() {
        clickerPresenter.runnerScoredButtonClicked();
    }

    @Override
    public void updateBallCountTextView(int ballCount) {
        ballCountTextView.setText(Integer.toString(ballCount));
    }

    @Override
    public void updateStrikeCountTextView(int strikeCount) {
        strikeCountTextView.setText(strikeCount);
    }

    @Override
    public void updateFoulCountTextView(int foulCount) {
        foulCountTextView.setText(foulCount);
    }

    @Override
    public void updateOutCountTextView(int outCount) {
        outCountTextView.setText(outCount);
    }

    @Override
    public void updateHomeScoreTextView(int homeScore) {
        homeTeamScoreTextView.setText(homeScore);
    }

    @Override
    public void updateAwayScoreTextView(int awayScore) {
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