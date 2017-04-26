package com.pyrotemplar.refereehelper.View;

import com.pyrotemplar.refereehelper.Presenter.ClickerPresenter;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public interface ClickerFragmentContract {

    interface View {
        void ballButtonClicked();

        void strikeButtonClicked();

        void foulButtonClicked();

        void outButtonClicked();

        void kickerIsSafeButtonClicked();

        void runnerScoredButtonClicked();

        void updateBallCountTextView(String ballCount);

        void updateStrikeCountTextView(String StrikeCount);

        void updateFoulCountTextView(String foulCount);

        void updateOutCountTextView(String outCount);

        void updateHomeScoreTextView(String homeScore);

        void updateAwayScoreTextView(String awayScore);

        void updateInningTextView(String inning);

        void updateGameClockTextView(String gameClock);

        void updatePlayViewTextView(String playString);

        void setPresenter(ClickerPresenter clickerPresenter);
    }

    interface Presenter {
        void calculateCount();

        void incrementBall();

        void incrementStrike();

        void incrementFoul();

        void incrementOut();

        void resetCount();

        void incrementRun();

        void updatedFields();
    }

}
