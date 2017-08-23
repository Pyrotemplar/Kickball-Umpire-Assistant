package com.pyrotemplar.refereehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.pyrotemplar.refereehelper.ClickerFragment.ClickerView;
import com.pyrotemplar.refereehelper.RulesFragment.RuleBookView;
import com.pyrotemplar.refereehelper.Settings.SettingsView;
import com.pyrotemplar.refereehelper.TabAcivity.TabActivityPresenter;
import com.pyrotemplar.refereehelper.LeagueFragment.LeagueView;
import com.pyrotemplar.refereehelper.View.GamesHistoryFragment;

import java.util.ArrayList;

/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ClickerView();
            case 1:
                return new LeagueView();
            case 2:
                return new RuleBookView();
            case 3:
                return  new SettingsView();
            default:
                // This should never happen. Always account for each position above
                return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        return createdFragment;
    }

    @Override
    public int getCount() {
        return TabActivityPresenter.TAB_ICON_ID.length;
    }
}
