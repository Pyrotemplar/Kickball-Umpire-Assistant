package com.pyrotemplar.refereehelper.DataObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Manuel Montes de Oca on 8/17/2017.
 */

public class Team extends RealmObject {

    public static final String FIELD_Name = "teamName";

    @PrimaryKey
    private String teamName;

    private String captainName;

    private int captainPhoneNumber;

    private String captainEmail;

    private int teamColor;

    public Team(String teamName, String captainName, String captainEmail, int captainPhoneNumber, int teamColor) {

        this.teamName = teamName;
        this.captainName = captainName;
        this.captainEmail = captainEmail;
        this.captainPhoneNumber = captainPhoneNumber;
        this.teamColor = teamColor;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String name) {
        this.teamName = name;
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
