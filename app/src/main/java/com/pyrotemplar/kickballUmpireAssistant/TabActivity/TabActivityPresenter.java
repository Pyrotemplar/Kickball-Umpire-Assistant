package com.pyrotemplar.kickballUmpireAssistant.TabActivity;


import com.pyrotemplar.kickballUmpireAssistant.R;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class TabActivityPresenter implements TabActivityContract.Presenter {

    public static final String CLICKER = "Clicker";
    public static final String TEAMS = "Teams";
    public static final String RULE_BOOK = "Rule Book";
    public static final String SETTINGS = "Settings";
    private final TabActivity view;
    public static final String[] TAB_ICON_TEXT = {CLICKER, TEAMS, RULE_BOOK, SETTINGS};

    public static final int[] TAB_ICON_ID = {
            R.drawable.ic_cicker_tab_icon,
            R.drawable.ic_teams_tab_icon,
            R.drawable.ic_rulebook_tab_icon,
            R.drawable.ic_settings_tab_icon
    };


    public TabActivityPresenter(TabActivity view) {
        this.view = view;
    }

    @Override
    public void setTabIcons() {
        for (int i = 0; i < TAB_ICON_ID.length; i++) {
            view.showTabIcons(i, TAB_ICON_ID[i],TAB_ICON_TEXT[i]);
        }
    }
}

