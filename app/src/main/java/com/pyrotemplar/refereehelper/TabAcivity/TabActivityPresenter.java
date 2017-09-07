package com.pyrotemplar.refereehelper.TabAcivity;


import com.pyrotemplar.refereehelper.R;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class TabActivityPresenter implements TabActivityContract.Presenter {

    public static final int[] TAB_ICON_ID = {
            R.drawable.ic_cicker_tab_icon,
            R.drawable.ic_settings_tab_icon,
            R.drawable.ic_rulebook_tab_icon,
            R.drawable.ic_settings_tab_icon
    };
    public static final String[] TAB_ICON_TEXT = {
            "Clicker",
            "Teams",
            "Rule Book",
            "Settings"
    };
    private final TabActivity view;

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

