package com.pyrotemplar.refereehelper.Presenter;

import android.support.annotation.NonNull;

import com.pyrotemplar.refereehelper.Utils.GameCountState;
import com.pyrotemplar.refereehelper.View.ClickerFragmentContract;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter implements ClickerFragmentContract.Presenter {

    static int awayTeamScore;
    static int homeTeamScore;
    static int strikeCount;
    static int ballCount = 0;
    static int foulCount;
    static int outCount;
    static int inning;
    static String currentPlay;

    private final ClickerFragmentContract.View mClickerFragmentView;
    private GameCountState gameCountState;
    private boolean botOfInning;

    public ClickerPresenter(@NonNull ClickerFragmentContract.View clickerFragmentView) {
        mClickerFragmentView = clickerFragmentView;
        mClickerFragmentView.setPresenter(this);
    }

    @Override
    public void calculateCount() {

    }

    @Override
    public void incrementBall() {
        ballCount++;
        updatedFields();
    }

    @Override
    public void incrementStrike() {
        strikeCount++;
        updatedFields();
    }

    @Override
    public void incrementFoul() {
        foulCount++;
        updatedFields();
    }

    @Override
    public void incrementOut() {
        if (outCount == 3) {
            outCount = 0;
            changeInning();
        } else
            outCount++;
        resetCount();
    }

    private void changeInning() {
        if(botOfInning){
            botOfInning = true;
        } else
            inning++;
    }

    @Override
    public void resetCount() {
        ballCount = 0;
        strikeCount = 0;
        foulCount = 0;
        updatedFields();
    }

    @Override
    public void incrementRun() {
       if(botOfInning)
           homeTeamScore++;
        else
           awayTeamScore++;

        updatedFields();
    }

    @Override
    public void updatedFields() {

        mClickerFragmentView.updateAwayScoreTextView(Integer.toString(awayTeamScore));
        mClickerFragmentView.updateHomeScoreTextView(Integer.toString(homeTeamScore));
        mClickerFragmentView.updateBallCountTextView(Integer.toString(ballCount));
        mClickerFragmentView.updateStrikeCountTextView(Integer.toString(strikeCount));
        mClickerFragmentView.updateOutCountTextView(Integer.toString(outCount));
        mClickerFragmentView.updateFoulCountTextView(Integer.toString(foulCount));
        mClickerFragmentView.updateInningTextView(Integer.toString(inning));
        mClickerFragmentView.updatePlayViewTextView(currentPlay);


    }
}
