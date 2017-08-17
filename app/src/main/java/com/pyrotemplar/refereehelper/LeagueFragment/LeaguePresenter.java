package com.pyrotemplar.refereehelper.LeagueFragment;

import android.support.annotation.NonNull;

/**
 * Created by Manuel Montes de Oca on 8/16/2017.
 */

public class LeaguePresenter implements LeagueContract.Presenter {

    LeaguePresenter(@NonNull LeagueContract.View leagueView) {

        leagueView.setPresenter(this);

    }
}
