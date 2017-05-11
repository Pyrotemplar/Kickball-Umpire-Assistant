package com.pyrotemplar.refereehelper.ClickerFragment;

import android.support.annotation.NonNull;

import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.Utils.GameCountState;

import java.util.Stack;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter implements ClickerContract.Presenter {

    //Game Count states
    private String awayTeamName;
    private String homeTeamName;
    private int awayTeamColor;
    private int homeTeamColor;
    private int awayTeamScore;
    private int homeTeamScore;
    private int strikeCount;
    private int ballCount;
    private int foulCount;
    private int outCount;
    private int inning;
    private boolean isBottomOfInning;
    public static boolean isThreeFoulOptionEnabled;
    private GameCountState gameCountState;

    //private static int gameClockTime;
    private Stack<GameCountState> undoStack;
    private Stack<GameCountState> redoStack;
    private final ClickerContract.View mClickerFragmentView;


    ClickerPresenter(@NonNull ClickerContract.View clickerFragmentView) {
        mClickerFragmentView = clickerFragmentView;
        mClickerFragmentView.setPresenter(this);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        initializeCountFields();
        updatedFields();
    }

    private void initializeCountFields() {

        awayTeamName = "Away";
        homeTeamName = "Home";
        awayTeamColor = 0;
        homeTeamColor = 0;
        awayTeamScore = 0;
        homeTeamScore = 0;
        strikeCount = 0;
        ballCount = 0;
        foulCount = 0;
        outCount = 0;
        inning = 1;
        isBottomOfInning = false;
        mClickerFragmentView.updateAwayTeamBannerView(awayTeamName, awayTeamColor);
        mClickerFragmentView.updateHomeTeamBannerView(homeTeamName, homeTeamColor);
        updateGameCountState();
    }

    @Override
    public void updateAwayTeamBanner(String teamName, int color) {
        awayTeamColor = color;
        awayTeamName = teamName;
        mClickerFragmentView.updateAwayTeamBannerView(awayTeamName, awayTeamColor);

    }

    @Override
    public void updateHomeTeamBanner(String teamName, int color) {

        homeTeamColor = color;
        homeTeamName = teamName;
        mClickerFragmentView.updateHomeTeamBannerView(homeTeamName, homeTeamColor);
    }

    @Override
    public void incrementBall() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        ballCount++;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void incrementStrike() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        strikeCount++;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void incrementFoul() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        foulCount++;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void incrementOut() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        outCount++;
        ballCount = 0;
        strikeCount = 0;
        foulCount = 0;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void resetCount() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        ballCount = 0;
        strikeCount = 0;
        foulCount = 0;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void incrementRun(int id) {
        if ((isBottomOfInning && id == R.id.homeTeamBannerLayout) ||
                (!isBottomOfInning && id == R.id.awayTeamBannerLayout) || id == R.id.runnerScoredButton) {
            updateGameCountState();
            undoStack.push(gameCountState);
            redoStack.clear();

            if (isBottomOfInning)
                homeTeamScore++;
            else
                awayTeamScore++;

            updateGameCountState();
            updatedFields();
        }
    }

    @Override
    public void updatedFields() {

        //todo: rework logic so the team name and color is not updated after each action and only at the start and when user changes name or color

        mClickerFragmentView.updateAwayScoreTextView(Integer.toString(awayTeamScore));
        mClickerFragmentView.updateHomeScoreTextView(Integer.toString(homeTeamScore));
        mClickerFragmentView.updateBallCountTextView(Integer.toString(ballCount));
        mClickerFragmentView.updateStrikeCountTextView(Integer.toString(strikeCount));
        mClickerFragmentView.updateFoulCountTextView(Integer.toString(foulCount));
        mClickerFragmentView.updateOutCountTextView(Integer.toString(outCount));
        mClickerFragmentView.updateInningTextView(generateInningString(inning));
        mClickerFragmentView.updateRedoLayoutVisibility(redoStack.isEmpty());
        mClickerFragmentView.updateUndoLayoutVisibility(undoStack.isEmpty());
        mClickerFragmentView.updateAwayArrowImageView(isBottomOfInning);
        mClickerFragmentView.updateHomeArrowImageView(isBottomOfInning);
        mClickerFragmentView.updateInningArrowImageView(isBottomOfInning);
    }

    @Override
    public void undo() {

        redoStack.push(gameCountState);
        gameCountState = undoStack.pop();
        awayTeamScore = gameCountState.getAwayTeamScore();
        homeTeamScore = gameCountState.getHomeTeamScore();
        strikeCount = gameCountState.getStrikeCount();
        ballCount = gameCountState.getBallCount();
        foulCount = gameCountState.getFoulCount();
        outCount = gameCountState.getOutCount();
        inning = gameCountState.getInning();
        isBottomOfInning = gameCountState.isBotOfInning();

        updatedFields();
    }

    @Override
    public void redo() {

        undoStack.push(gameCountState);
        gameCountState = redoStack.pop();
        awayTeamScore = gameCountState.getAwayTeamScore();
        homeTeamScore = gameCountState.getHomeTeamScore();
        strikeCount = gameCountState.getStrikeCount();
        ballCount = gameCountState.getBallCount();
        foulCount = gameCountState.getFoulCount();
        outCount = gameCountState.getOutCount();
        inning = gameCountState.getInning();
        isBottomOfInning = gameCountState.isBotOfInning();

        updatedFields();
    }

    @Override
    public void setThreeFoulOption(boolean isThreeFoulOptionEnabled) {
        this.isThreeFoulOptionEnabled = isThreeFoulOptionEnabled;
    }

    private String generateInningString(int inning) {
        String inningString;
        if (inning == 1) {
            inningString = "1st";
        } else if (inning == 2) {
            inningString = "2nd";
        } else if (inning == 3) {
            inningString = "3rd";
        } else
            inningString = inning + "th";
        return inningString;
    }

    private void countLogic() {

        if (ballCount == 4) {
            ballCount = 0;
            foulCount = 0;
            strikeCount = 0;
        }
        if (isThreeFoulOptionEnabled) {
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
            changeInning();
            outCount = 0;
            ballCount = 0;
            foulCount = 0;
            strikeCount = 0;
        }
    }

    private void changeInning() {

        if (isBottomOfInning) {
            inning++;
            isBottomOfInning = false;
        } else
            isBottomOfInning = true;
    }

    private void updateGameCountState() {

        gameCountState = new GameCountState();
        gameCountState.setAwayTeamScore(awayTeamScore);
        gameCountState.setHomeTeamScore(homeTeamScore);
        gameCountState.setStrikeCount(strikeCount);
        gameCountState.setBallCount(ballCount);
        gameCountState.setFoulCount(foulCount);
        gameCountState.setOutCount(outCount);
        gameCountState.setInning(inning);
        gameCountState.setBotOfInning(isBottomOfInning);
    }

}
