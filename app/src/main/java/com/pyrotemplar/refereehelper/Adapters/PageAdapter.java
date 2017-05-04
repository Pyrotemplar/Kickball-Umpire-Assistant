package com.pyrotemplar.refereehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pyrotemplar.refereehelper.TabAcivity.TabActivityPresenter;
import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.View.ClockFragment;
import com.pyrotemplar.refereehelper.View.GamesHistoryFragment;
import com.pyrotemplar.refereehelper.View.RulesFragment;
import com.pyrotemplar.refereehelper.View.SettingsView;

/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        ClickerView clickerView = new ClickerView();
        ClockFragment clockFragment = new ClockFragment();
        GamesHistoryFragment gamesHistoryFragment = new GamesHistoryFragment();
        RulesFragment rulesFragment = new RulesFragment();
        SettingsView settingsView = new SettingsView();

        if (position == 0)
            return gamesHistoryFragment;
        else if(position == 1)
            return clockFragment;
        else if(position == 2)
            return clickerView;
        else if(position == 3)
            return rulesFragment;
        else if(position == 4)
            return settingsView;
        else
            return clickerView;

    }

    @Override
    public int getCount() {
        return TabActivityPresenter.TAB_RES_ID.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

    return null;
    }
}
