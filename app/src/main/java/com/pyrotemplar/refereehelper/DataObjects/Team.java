package com.pyrotemplar.refereehelper.DataObjects;

import io.realm.RealmObject;

/**
 * Created by Manuel Montes de Oca on 8/17/2017.
 */

public class Team extends RealmObject {

    public static final String FIELD_Name = "name";


    private String name;

    private String captainName;

    private int captainPhoneNumber;

    private String captainEmail;

    private int teamColor;

    public Team(String name, String captainName, int captainPhoneNumber, String captainEmail, int teamColor) {

        this.name = name;
        this.captainName = captainName;
        this.captainPhoneNumber = captainPhoneNumber;
        this.captainEmail = captainEmail;
        this.teamColor = teamColor;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public int getCaptainPhoneNumber() {
        return captainPhoneNumber;
    }

    public void setCaptainPhoneNumber(int captainPhoneNumber) {
        this.captainPhoneNumber = captainPhoneNumber;
    }

    public String getCaptainEmail() {
        return captainEmail;
    }

    public void setCaptainEmail(String captainEmail) {
        this.captainEmail = captainEmail;
    }

    public int getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(int teamColor) {
        this.teamColor = teamColor;
    }
}
