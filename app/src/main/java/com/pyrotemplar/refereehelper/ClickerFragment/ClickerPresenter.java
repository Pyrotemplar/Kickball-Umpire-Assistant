package com.pyrotemplar.refereehelper.ClickerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.Utils.GameCountState;
import com.pyrotemplar.refereehelper.Utils.GameTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter implements ClickerContract.Presenter {

    public static final String AWAY = "Away";
    public static final String HOME = "Home";
    public static final String TIME_S_UP = "Time's Up";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong!";
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
    private int startingBallCount;
    private int startingStrikeCount;
    private int startingFoulCount;
    private int startingOutCount;
    private int inning;
    private int maxInning;
    private int gameClockTime;
    private String GameClockString;
    private boolean isBottomOfInning;
    private boolean isThreeFoulOptionEnabled;


    private GameCountState gameCountState;
    private GameTimer gameTimer;
    private Stack<GameCountState> undoStack;
    private Stack<GameCountState> redoStack;
    private final ClickerContract.View mClickerFragmentView;
    private boolean isGameClockRunning;


    ClickerPresenter(@NonNull ClickerContract.View clickerFragmentView) {
        mClickerFragmentView = clickerFragmentView;
        mClickerFragmentView.setPresenter(this);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        initializeCountFields();
        updatedFields();
    }

    public void saveState(Bundle outState, Context context) {

        outState.putString(context.getString(R.string.Away_Team_Name), awayTeamName);
        outState.putInt(context.getString(R.string.Away_Team_Score), awayTeamScore);
        outState.putInt(context.getString(R.string.Away_Team_Color), awayTeamColor);
        outState.putString(context.getString(R.string.Home_Team_Name), homeTeamName);
        outState.putInt(context.getString(R.string.Home_Team_Score), homeTeamScore);
        outState.putInt(context.getString(R.string.Home_Team_Color), homeTeamColor);

        outState.putInt(context.getString(R.string.Current_Inning), inning);
        outState.putBoolean(context.getString(R.string.Is_Bottom_of_Inning), isBottomOfInning);

        outState.putInt(context.getString(R.string.Number_Of_Balls), ballCount);
        outState.putInt(context.getString(R.string.Number_Of_Strikes), strikeCount);
        outState.putInt(context.getString(R.string.Number_Of_Fouls), foulCount);
        outState.putInt(context.getString(R.string.Number_Of_Outs), outCount);

        outState.putInt(context.getString(R.string.Game_Clock), (int) gameTimer.millisUntilFinished);
        outState.putBoolean(context.getString(R.string.Is_Game_Clock_Running), isGameClockRunning);

        outState.putSerializable(context.getString(R.string.Undo_Stack), undoStack);
        outState.putSerializable(context.getString(R.string.Redo_Stack), redoStack);

    }

    public void loadState(Bundle savedInstanceState, Context context) {
        awayTeamName = savedInstanceState.getString(context.getString(R.string.Away_Team_Name));
        awayTeamScore = savedInstanceState.getInt(context.getString(R.string.Away_Team_Score));
        awayTeamColor = savedInstanceState.getInt(context.getString(R.string.Away_Team_Color));
        homeTeamName = savedInstanceState.getString(context.getString(R.string.Home_Team_Name));
        homeTeamScore = savedInstanceState.getInt(context.getString(R.string.Home_Team_Score));
        homeTeamColor = savedInstanceState.getInt(context.getString(R.string.Home_Team_Color));

        inning = savedInstanceState.getInt(context.getString(R.string.Current_Inning));
        isBottomOfInning = savedInstanceState.getBoolean(context.getString(R.string.Is_Bottom_of_Inning));

        ballCount = savedInstanceState.getInt(context.getString(R.string.Number_Of_Balls));
        strikeCount = savedInstanceState.getInt(context.getString(R.string.Number_Of_Strikes));
        foulCount = savedInstanceState.getInt(context.getString(R.string.Number_Of_Fouls));
        outCount = savedInstanceState.getInt(context.getString(R.string.Number_Of_Outs));

        gameClockTime = savedInstanceState.getInt(context.getString(R.string.Game_Clock));
        isGameClockRunning = savedInstanceState.getBoolean(context.getString(R.string.Is_Game_Clock_Running));
        try {
            undoStack = (Stack<GameCountState>) savedInstanceState.getSerializable(context.getString(R.string.Undo_Stack));
            redoStack = (Stack<GameCountState>) savedInstanceState.getSerializable(context.getString(R.string.Redo_Stack));
        } catch (Exception e) {
            Toast.makeText(context, SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
        }
        updatedFields();
        initializeGameClock();
        startStopGameClock(true);
        mClickerFragmentView.updateAwayTeamBannerView(awayTeamName, awayTeamColor);
        mClickerFragmentView.updateHomeTeamBannerView(homeTeamName, homeTeamColor);
    }

    private void initializeCountFields() {


        awayTeamName = AWAY;
        homeTeamName = HOME;
        awayTeamColor = 0;
        homeTeamColor = 0;
        awayTeamScore = 0;
        homeTeamScore = 0;
        strikeCount = startingStrikeCount;
        ballCount = startingBallCount;
        foulCount = startingFoulCount;
        outCount = startingOutCount;
        inning = 1;
        gameClockTime = 2700 * 1000;
        isBottomOfInning = false;
        mClickerFragmentView.updateAwayTeamBannerView(awayTeamName, awayTeamColor);
        mClickerFragmentView.updateHomeTeamBannerView(homeTeamName, homeTeamColor);
        initializeGameClock();
        updateGameCountState();
        undoStack.clear();
        redoStack.clear();
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
        ballCount = startingBallCount;
        strikeCount = startingStrikeCount;
        foulCount = startingFoulCount;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    @Override
    public void resetCount() {
        updateGameCountState();
        undoStack.push(gameCountState);
        redoStack.clear();

        ballCount = startingBallCount;
        strikeCount = startingStrikeCount;
        foulCount = startingFoulCount;

        updateGameCountState();
        countLogic();
        updatedFields();
    }

    public void setGameClockString(int newTime) {
        gameClockTime = newTime * 60 * 1000;
        initializeGameClock();
    }

    @Override
    public boolean incrementRun(int id) {
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

            return true;
        }
        return false;
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

    @Override
    public void startStopGameClock(boolean newTime) {

        if (isGameClockRunning) {
            gameTimer.cancel();
            gameClockTime = (int) gameTimer.millisUntilFinished;

            gameTimer = new GameTimer(gameClockTime, 1000, this);
            isGameClockRunning = false;
        } else {
            if (!newTime && (!GameClockString.equals("Time's Up"))) {
                gameTimer.start();
                isGameClockRunning = true;
            }
        }

    }

    @Override
    public void updateGameClock(long millisUntilFinished) {

        if (millisUntilFinished != 0L) {
            GameClockString = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
        } else {
            GameClockString = TIME_S_UP;
            isGameClockRunning = false;
        }

        mClickerFragmentView.updateGameClockTextView(GameClockString);
    }

    private String generateInningString(int inning) {

        int mod100 = inning % 100;
        int mod10 = inning % 10;
        if (mod10 == 1 && mod100 != 11) {
            return inning + "st";
        } else if (mod10 == 2 && mod100 != 12) {
            return inning + "nd";
        } else if (mod10 == 3 && mod100 != 13) {
            return inning + "rd";
        } else {
            return inning + "th";
        }
    }

    private void countLogic() {

        if (ballCount == 4) {
            ballCount = startingBallCount;
            strikeCount = startingStrikeCount;
            foulCount = startingFoulCount;
        }
        if (isThreeFoulOptionEnabled) {
            if (foulCount == 3) {
                outCount++;
                ballCount = startingBallCount;
                strikeCount = startingStrikeCount;
                foulCount = startingFoulCount;
            }
        } else {
            if (foulCount == 4) {
                outCount++;
                ballCount = startingBallCount;
                strikeCount = startingStrikeCount;
                foulCount = startingFoulCount;
            }
        }
        if (strikeCount == 3) {
            outCount++;
            ballCount = startingBallCount;
            strikeCount = startingStrikeCount;
            foulCount = startingFoulCount;
        }
        if (outCount == 3) {
            changeInning();
            outCount = startingOutCount;
            ballCount = startingBallCount;
            strikeCount = startingStrikeCount;
            foulCount = startingFoulCount;
        }
    }

    private void changeInning() {

        if (isBottomOfInning) {
            if (inning < maxInning) {
                inning++;
                isBottomOfInning = false;
            }
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

    private void initializeGameClock() {
        //isGameClockRunning = false;
        gameTimer = new GameTimer(gameClockTime, 1000, this);
        gameTimer.start();
        gameTimer.cancel();
        gameClockTime = (int) gameTimer.millisUntilFinished;
    }

    public void setStartingCount(int startingBallCount, int startingStrikeCount, int startingFoulCount, int startingOutCount) {
        this.startingBallCount = startingBallCount;
        this.startingStrikeCount = startingStrikeCount;
        this.startingFoulCount = startingFoulCount;
        this.startingOutCount = startingOutCount;

        strikeCount = startingStrikeCount;
        ballCount = startingBallCount;
        foulCount = startingFoulCount;
        outCount = startingOutCount;

        updatedFields();

    }

    public void setMaxInnings(int maxInnings) {
        this.maxInning = maxInnings;
    }

    public void resetData() {
        startStopGameClock(true);
        initializeCountFields();
        updatedFields();

    }

}
