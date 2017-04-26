package com.pyrotemplar.refereehelper.Utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manuel Montes de Oca on 4/26/2017.
 */

public class GameTimer extends CountDownTimer {
    TextView timerView;
    long UntilFinished;

    public GameTimer(long millisInFuture, long countDownInterval, TextView timerView) {
        super(millisInFuture, countDownInterval);
        this.timerView = timerView;
        onTick(millisInFuture);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long millis = millisUntilFinished;
        String ms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        UntilFinished = millis;
        timerView.setText(ms);

    }

    @Override
    public void onFinish() {
        timerView.setText("Times Up");
    }
}