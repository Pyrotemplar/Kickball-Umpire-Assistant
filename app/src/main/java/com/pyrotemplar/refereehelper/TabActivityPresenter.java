package com.pyrotemplar.refereehelper;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class TabActivityPresenter implements TabActivityView {

    public static final int[] TAB_RES_ID = {
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher
    };
    private final TabActivity view;

    public TabActivityPresenter(TabActivity view) {
        this.view = view;
    }

    @Override
    public void setTabIcons(TabLayout tabLayout, Context context) {

        // Populates Tabs with icons
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(TAB_RES_ID[i]);
            tabLayout.getTabAt(i).setCustomView(imageView);
        }
    }
}
