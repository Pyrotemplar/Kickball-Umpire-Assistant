package com.pyrotemplar.refereehelper.Settings;

import android.support.annotation.NonNull;

/**
 * Created by Manuel Montes de Oca on 5/9/2017.
 */

class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View mSettingsView;

    SettingsPresenter(@NonNull SettingsContract.View settingView ) {
        this.mSettingsView = settingView;
        mSettingsView.setPresenter(this);
    }
}
