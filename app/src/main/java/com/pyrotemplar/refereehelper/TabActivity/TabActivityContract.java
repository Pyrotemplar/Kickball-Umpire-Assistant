package com.pyrotemplar.refereehelper.TabActivity;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

interface TabActivityContract {
    interface View {
        void showTabIcons(int location, int tabIconId, String IconText);
    }

    interface Presenter {
        void setTabIcons();
    }
}
