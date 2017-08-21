package com.pyrotemplar.refereehelper.DataObjects;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Manuel Montes de Oca on 8/18/2017.
 */

public class dataHelper {

    public static void addItem(Realm realm, final Team team) {
        realm.beginTransaction();
        realm.copyToRealm(team);
        realm.commitTransaction();
    }

    public static void deleteItem(Realm realm, final Team teamToDelete) {
        realm.beginTransaction();
        RealmResults team = realm.where(Team.class).equalTo(Team.FIELD_Name, teamToDelete.getName()).findAll();
        team.deleteAllFromRealm();
        realm.commitTransaction();

    }


}
