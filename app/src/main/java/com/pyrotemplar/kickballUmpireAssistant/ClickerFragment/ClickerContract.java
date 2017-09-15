package com.pyrotemplar.kickballUmpireAssistant.ClickerFragment;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

interface ClickerContract {

    interface View {
        boolean AwayTeamButtonLongClicked(android.view.View view);

        boolean homeTeamButtonLongClicked(android.view.View view);

        void incrementRunButtonClicked(android.view.View view);

        void ballButtonClicked(android.view.View view);

        void strikeButtonClicked(android.view.View view);

        void foulButtonClicked(android.view.View view);

        void outButtonClicked(android.view.View view);

        void undoButtonClicked(android.view.View view);

        void redoButtonClicked(android.view.View view);

        void runnerScoredButtonClicked(android.view.View view);

        void kickerIsSafeButtonClicked(android.view.View view);

        void gameClockButtonClicked(android.view.View view);

        boolean gameClockButtonLongClicked(android.view.View view);

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

        void updateAwayTeamBannerView(String awayTeamName, int teamColor);

        void updateHomeTeamBannerView(String awayTeamName, int teamColor);

        void updateGameClockTextView(String gameClock);

        void setStartingCount();

        void updateSharePreferences();

        void setPresenter(ClickerPresenter clickerPresenter);
    }

    interface Presenter {

        void updateAwayTeamBanner(String teamName, int color);

        void updateHomeTeamBanner(String teamName, int color);

        void incrementBall();

        void incrementStrike();

        void incrementFoul();

        void incrementOut();

        void resetCount();

        void setGameClockString(int newTime);

        boolean incrementRun(int id);

        void updatedFields();

        void undo();

        void redo();

        void setThreeFoulOption(boolean isThreeFoulOptionEnabled);

        void startStopGameClock(boolean newTime);

        void updateGameClock(long millisUntilFinished);

        void setStartingCount(int startingBallCount, int startingStrikeCount, int startingFoulCount, int startingOutCount);

        void setMaxInnings(int maxInnings);

        void resetData();
        void saveState(Bundle outState, Context context);
        void loadState(Bundle savedInstanceState, Context context);
    }

    interface Model {
        void calculateCount();
    }

}
