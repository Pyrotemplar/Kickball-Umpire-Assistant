package com.pyrotemplar.refereehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pyrotemplar.refereehelper.TabActivityPresenter;
import com.pyrotemplar.refereehelper.View.ClickerFragment;
import com.pyrotemplar.refereehelper.View.ClockFragment;
import com.pyrotemplar.refereehelper.View.GamesHistoryFragment;
import com.pyrotemplar.refereehelper.View.RulesFragment;

/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        ClickerFragment clickerFragment = new ClickerFragment();
        ClockFragment clockFragment = new ClockFragment();
        GamesHistoryFragment gamesHistoryFragment = new GamesHistoryFragment();
        RulesFragment rulesFragment = new RulesFragment();

        if (position == 0)
            return clickerFragment;
        else if(position == 1)
            return clockFragment;
        else if(position == 2)
            return gamesHistoryFragment;
        else if(position == 3)
            return rulesFragment;
        else
            return clickerFragment;

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
