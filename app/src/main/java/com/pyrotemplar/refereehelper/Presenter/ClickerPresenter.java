package com.pyrotemplar.refereehelper.Presenter;

import android.view.View;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.View.ClickerFragmentView;

import butterknife.BindView;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter  implements ClickerFragmentView{



    private ClickerFragmentView view;

    public ClickerPresenter(ClickerFragmentView view) {
        this.view = view;
    }

    @Override
    public void ballButtonClicked() {
        updateBallCountTextView(5);
    }

    @Override
    public void strikeButtonClicked() {

    }

    @Override
    public void foulButtonClicked() {

    }

    @Override
    public void outButtonClicked() {

    }

    @Override
    public void kickerIsSafeButtonClicked() {

    }

    @Override
    public void runnerScoredButtonClicked() {

    }

    @Override
    public void updateBallCountTextView(int ballCount) {
        view.updateBallCountTextView(ballCount);
    }

    @Override
    public void updateStrikeCountTextView(int StrikeCount) {

    }

    @Override
    public void updateFoulCountTextView(int foulCount) {

    }

    @Override
    public void updateOutCountTextView(int outCount) {

    }

    @Override
    public void updateHomeScoreTextView(int homeScore) {

    }

    @Override
    public void updateAwayScoreTextView(int awayScore) {

    }

    @Override
    public void updateInningTextView(String inning) {

    }

    @Override
    public void updateGameClockTextView(String gameClock) {

    }

    @Override
    public void updatePlayViewTextView(String playString) {

    }

}
