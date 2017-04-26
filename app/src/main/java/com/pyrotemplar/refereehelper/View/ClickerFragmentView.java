package com.pyrotemplar.refereehelper.View;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public interface ClickerFragmentView {

    void ballButtonClicked();
    void strikeButtonClicked();
    void foulButtonClicked();
    void outButtonClicked();
    void kickerIsSafeButtonClicked();
    void runnerScoredButtonClicked();
    void updateBallCountTextView(int ballCount);
    void updateStrikeCountTextView(int StrikeCount);
    void updateFoulCountTextView(int foulCount);
    void updateOutCountTextView(int outCount);
    void updateHomeScoreTextView(int homeScore);
    void updateAwayScoreTextView(int awayScore);
    void updateInningTextView(String inning);
    void updateGameClockTextView(String gameClock);
    void updatePlayViewTextView(String playString);
}
