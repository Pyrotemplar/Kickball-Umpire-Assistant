package com.pyrotemplar.kickballUmpireAssistant.DataObjects;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Manuel Montes de Oca on 8/18/2017.
 */

public class dataHelper {

    public static void addItem(Realm realm, final Team team) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(team);
        realm.commitTransaction();
    }

    public static void deleteItem(Realm realm, final String teamName) {
        realm.beginTransaction();
        RealmResults team = realm.where(Team.class).equalTo(Team.FIELD_Name, teamName).findAll();
        team.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static Team getItem(Realm realm, String teamName) {
        return realm.where(Team.class).equalTo(Team.FIELD_Name, teamName).findFirst();

    }


}
