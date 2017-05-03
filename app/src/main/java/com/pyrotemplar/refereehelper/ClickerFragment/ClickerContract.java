package com.pyrotemplar.refereehelper.ClickerFragment;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerPresenter;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public interface ClickerContract {

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

        void updateInningArrowImageView();

        void updateAwayArrowImageView(boolean isVisible);

        void updateHomeArrowImageView(boolean isVisible);

        void updateGameClockTextView(String gameClock);

        void updatePlayViewTextView(String playString);

        void setPresenter(ClickerPresenter clickerPresenter);
    }

    interface Presenter {

        void incrementBall();

        void incrementStrike();

        void incrementFoul();

        void incrementOut();

        void resetCount();

        void incrementRun();

        void updatedFields();

    }

    interface Model {
        void calculateCount();
    }

}
