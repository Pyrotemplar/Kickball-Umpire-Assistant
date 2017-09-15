package com.pyrotemplar.kickballUmpireAssistant.Utils;

import android.os.CountDownTimer;

import com.pyrotemplar.kickballUmpireAssistant.ClickerFragment.ClickerPresenter;

/**
 * Created by Manuel Montes de Oca on 4/26/2017.
 */

public class GameTimer extends CountDownTimer {
    ClickerPresenter mPresenter;
    public long millisUntilFinished;

    public GameTimer(long millisInFuture, long countDownInterval, ClickerPresenter mPresenter) {
        super(millisInFuture, countDownInterval);
        this.mPresenter=mPresenter;
        onTick(millisInFuture);
    }

    @Override
    public void onTick(long millisUntilFinished) {


        this.millisUntilFinished  = millisUntilFinished;

        mPresenter.updateGameClock(millisUntilFinished);


    }

    @Override
    public void onFinish() {
        mPresenter.updateGameClock(0l);
    }
}