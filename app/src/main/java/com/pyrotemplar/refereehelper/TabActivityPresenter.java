package com.pyrotemplar.refereehelper;


/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class TabActivityPresenter implements TabActivityContract.Presenter {

    public static final int[] TAB_RES_ID = {
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher
    };
    private final TabActivity view;

    public TabActivityPresenter(TabActivity view) {
        this.view = view;
    }

    @Override
    public void setTabIcons() {
        for (int i = 0; i < TAB_RES_ID.length; i++) {
            view.showTabIcons(i, TAB_RES_ID[i]);
        }
    }
}

