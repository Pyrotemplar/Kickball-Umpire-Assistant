package com.pyrotemplar.refereehelper.ClickerFragment;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

interface ClickerContract {

    interface View {
        void ballButtonClicked(android.view.View view);

        void strikeButtonClicked(android.view.View view);

        void foulButtonClicked(android.view.View view);

        void outButtonClicked(android.view.View view);

        void undoButtonClicked(android.view.View view);

        void redoButtonClicked(android.view.View view);

        void kickerIsSafeButtonClicked(android.view.View view);

        void runnerScoredButtonClicked(android.view.View view);

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

        void updateSharePreferences();

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

        void setThreeFoulOption(boolean isThreeFoulOptionEnabled);
    }

    interface Model {
        void calculateCount();
    }

}
