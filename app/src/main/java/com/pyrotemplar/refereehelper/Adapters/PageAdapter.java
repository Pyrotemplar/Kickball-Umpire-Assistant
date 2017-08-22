package com.pyrotemplar.refereehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.RulesFragment.RuleBookView;
import com.pyrotemplar.refereehelper.Settings.SettingsView;
import com.pyrotemplar.refereehelper.TabAcivity.TabActivityPresenter;
import com.pyrotemplar.refereehelper.LeagueFragment.LeagueView;
import com.pyrotemplar.refereehelper.View.GamesHistoryFragment;

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
        LeagueView leagueFragment = new LeagueView();
        GamesHistoryFragment gamesHistoryFragment = new GamesHistoryFragment();
        RuleBookView rulesFragment = new RuleBookView();
        SettingsView settingsView = new SettingsView();

        if (position == 0)
            return clickerView;
        else if (position == 1)
            return leagueFragment;
        /*else if (position == 2)
            return clickerView;*/
        else if (position == 2)
            return rulesFragment;
        else if (position == 3)
            return settingsView;
        else
            return clickerView;

    }

    @Override
    public int getCount() {
        return TabActivityPresenter.TAB_ICON_ID.length;
    }

}
