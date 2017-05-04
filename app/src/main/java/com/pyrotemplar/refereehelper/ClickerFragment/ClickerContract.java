package com.pyrotemplar.refereehelper.ClickerFragment;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerPresenter;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

interface ClickerContract {

    interface View {
        void ballButtonClicked();

        void strikeButtonClicked();

        void foulButtonClicked();

        void outButtonClicked();

        void undoButtonClicked();

        void redoButtonClicked();

        void kickerIsSafeButtonClicked();

        void runnerScoredButtonClicked();

        void updateBallCountTextView(String ballCount);

        void updateStrikeCountTextView(String StrikeCount);

        void updateFoulCountTextView(String foulCount);

        void updateOutCountTextView(String outCount);

        void updateHomeScoreTextView(String homeScore);

        void updateAwayScoreTextView(String awayScore);

        void updateInningTextView(String inning);

        void updateUndoLayoutVisibility(boolean isNotClickable);

        void updateRedoLayoutVisibility(boolean isNotClickable);

        void updateInningArrowImageView(boolean isBottomOfInning);

        void updateAwayArrowImageView(boolean isBottomOfInning);

        void updateHomeArrowImageView(boolean isBottomOfInning);

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

        void undo();

        void redo();

    }

    interface Model {
        void calculateCount();
    }

}
