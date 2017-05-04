package com.pyrotemplar.refereehelper.ClickerFragment;

import android.support.annotation.NonNull;

import com.pyrotemplar.refereehelper.Utils.GameCountState;

import java.util.Stack;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter implements ClickerContract.Presenter {

    //Game Count states
    static int awayTeamScore;
    static int homeTeamScore;
    static int strikeCount;
    static int ballCount = 0;
    static int foulCount;
    static int outCount;
    static int inning;
    private boolean botOfInning;
    private boolean rotateInningImage;
    private boolean threeFoulOption;
    private GameCountState gameCountState;
    //private static int gameClockTime;

    private Stack<GameCountState> undoStack;
    private Stack<GameCountState> redoStack;
    private final ClickerContract.View mClickerFragmentView;

    public ClickerPresenter(@NonNull ClickerContract.View clickerFragmentView) {
        mClickerFragmentView = clickerFragmentView;
        mClickerFragmentView.setPresenter(this);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        initializeCountFields();
        updatedFields();
    }

    private void initializeCountFields() {

        awayTeamScore = 0;
        homeTeamScore = 0;
        strikeCount = 0;
        ballCount = 0;
        foulCount = 0;
        outCount = 0;
        //  gameClockTime = 2700;
        inning = 1;
        gameCountState = new GameCountState();
        botOfInning = false;

        //undoStack.clear();
        // redoStack.clear();
        mClickerFragmentView.updateAwayArrowImageView(true);
        mClickerFragmentView.updateHomeArrowImageView(false);
    }

    @Override
    public void incrementBall() {
        undoStack.push(gameCountState);
        ballCount++;
        updatedFields();
    }

    @Override
    public void incrementStrike() {
        undoStack.push(gameCountState);
        strikeCount++;
        updatedFields();
    }

    @Override
    public void incrementFoul() {
        undoStack.push(gameCountState);
        foulCount++;
        updatedFields();
    }

    @Override
    public void incrementOut() {
        undoStack.push(gameCountState);
        outCount++;
        updatedFields();
    }


    @Override
    public void resetCount() {
        undoStack.push(gameCountState);
        ballCount = 0;
        strikeCount = 0;
        foulCount = 0;
        updatedFields();
    }

    @Override
    public void incrementRun() {
        undoStack.push(gameCountState);
        if (botOfInning)
            homeTeamScore++;
        else
            awayTeamScore++;

        updatedFields();
    }

    @Override
    public void updatedFields() {

        updateGameCountState();

        autoMode();
        mClickerFragmentView.updateAwayScoreTextView(Integer.toString(awayTeamScore));
        mClickerFragmentView.updateHomeScoreTextView(Integer.toString(homeTeamScore));
        mClickerFragmentView.updateBallCountTextView(Integer.toString(ballCount));
        mClickerFragmentView.updateStrikeCountTextView(Integer.toString(strikeCount));
        mClickerFragmentView.updateOutCountTextView(Integer.toString(outCount));
        mClickerFragmentView.updateFoulCountTextView(Integer.toString(foulCount));
        if (rotateInningImage) {
            mClickerFragmentView.updateInningArrowImageView();
            rotateInningImage = false;
        }
        if (redoStack.isEmpty())
            mClickerFragmentView.updateRedoLayoutVisibility(true);
        else
            mClickerFragmentView.updateRedoLayoutVisibility(false);

        if (undoStack.isEmpty())
            mClickerFragmentView.updateUndoLayoutVisibility(true);
        else
            mClickerFragmentView.updateUndoLayoutVisibility(false);

        changeTeamInningArrow();


        mClickerFragmentView.updateInningTextView(generateInningString(inning));
        // mClickerFragmentView.updatePlayViewTextView(currentPlay);


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
        rotateInningImage = gameCountState.isRotateInningImage();
        botOfInning = gameCountState.isBotOfInning();
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
        rotateInningImage = gameCountState.isRotateInningImage();
        botOfInning = gameCountState.isBotOfInning();
        updatedFields();

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
            rotateInningImage = true;
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

    private void changeTeamInningArrow() {

        if (botOfInning) {
            mClickerFragmentView.updateHomeArrowImageView(true);
            mClickerFragmentView.updateAwayArrowImageView(false);
        } else {
            mClickerFragmentView.updateHomeArrowImageView(false);
            mClickerFragmentView.updateAwayArrowImageView(true);
        }
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
        gameCountState.setBotOfInning(botOfInning);
        gameCountState.setRotateInningImage(rotateInningImage);
    }
}
