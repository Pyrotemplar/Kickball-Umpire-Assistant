package com.pyrotemplar.refereehelper;

import android.content.Context;
import android.support.design.widget.TabLayout;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

interface TabActivityContract {
    interface View {
        void showTabIcons(int location, int resId);
    }

    interface Presenter {
        void setTabIcons();
    }
}
