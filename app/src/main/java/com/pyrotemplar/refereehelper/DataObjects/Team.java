package com.pyrotemplar.refereehelper.DataObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Manuel Montes de Oca on 8/17/2017.
 */

public class Team extends RealmObject {

    @PrimaryKey
    private String name;

    private String captain;

    private int captainPhoneNumber;

    private String captainEmail;

    private int teamColor;


}
