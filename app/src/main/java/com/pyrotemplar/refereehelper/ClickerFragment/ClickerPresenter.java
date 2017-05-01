package com.pyrotemplar.refereehelper.ClickerFragment;

import android.support.annotation.NonNull;

import com.pyrotemplar.refereehelper.Utils.GameCountState;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter implements ClickerContract.Presenter {

    static int awayTeamScore;
    static int homeTeamScore;
    static int strikeCount;
    static int ballCount = 0;
    static int foulCount;
    static int outCount;
    static int inning;
    static String currentPlay;
    private static int gameClockTime;

    private final ClickerContract.View mClickerFragmentView;
    private GameCountState gameCountState;
    private boolean botOfInning;
    private boolean threeFoulOption;

    public ClickerPresenter(@NonNull ClickerContract.View clickerFragmentView) {
        mClickerFragmentView = clickerFragmentView;
        mClickerFragmentView.setPresenter(this);
        initializeCountFields();
        updatedFields();
    }

    protected static void initializeCountFields() {

        awayTeamScore = 0;
        homeTeamScore = 0;
        strikeCount = 0;
        ballCount = 0;
        foulCount = 0;
        outCount = 0;
        gameClockTime = 2700;
        inning = 1;
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
        outCount++;
        updatedFields();
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

        autoMode();
        mClickerFragmentView.updateAwayScoreTextView(Integer.toString(awayTeamScore));
        mClickerFragmentView.updateHomeScoreTextView(Integer.toString(homeTeamScore));
        mClickerFragmentView.updateBallCountTextView(Integer.toString(ballCount));
        mClickerFragmentView.updateStrikeCountTextView(Integer.toString(strikeCount));
        mClickerFragmentView.updateOutCountTextView(Integer.toString(outCount));
        mClickerFragmentView.updateFoulCountTextView(Integer.toString(foulCount));
        mClickerFragmentView.updateInningTextView(Integer.toString(inning));
        mClickerFragmentView.updatePlayViewTextView(currentPlay);

    }

    private void autoMode() {
        if (ballCount == 4) {
            ballCount = 0;
            foulCount = 0;
            strikeCount = 0;
        }
        if (threeFoulOption) {
            if (foulCount == 3) {
                outCount++;
                ballCount = 0;
                foulCount = 0;
                strikeCount = 0;
            }
        } else {
            if (foulCount == 4) {
                outCount++;
                ballCount = 0;
                foulCount = 0;
                strikeCount = 0;
            }
        }
        if (strikeCount == 3) {
            outCount++;
            ballCount = 0;
            foulCount = 0;
            strikeCount = 0;
        }
        if (outCount == 3) {
            if (!botOfInning) {
                botOfInning = true;
            } else if (botOfInning) {
                if (inning < 9)
                    inning++;
                else
                    inning = 1;
                botOfInning = false;
            }
            outCount = 0;
            ballCount = 0;
            foulCount = 0;
            strikeCount = 0;
        }
    }
}
