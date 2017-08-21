package com.pyrotemplar.refereehelper;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Manuel Montes de Oca on 8/18/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
      //  Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
