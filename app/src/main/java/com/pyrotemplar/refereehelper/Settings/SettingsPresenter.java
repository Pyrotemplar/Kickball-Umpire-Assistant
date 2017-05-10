package com.pyrotemplar.refereehelper.Settings;

import android.support.annotation.NonNull;

/**
 * Created by Manuel Montes de Oca on 5/9/2017.
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    SettingsContract.View mSettingsView;

    SettingsPresenter(@NonNull SettingsContract.View settingView ) {
        this.mSettingsView = settingView;
        mSettingsView.setPresenter(this);
    }

}
