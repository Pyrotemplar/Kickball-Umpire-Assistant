package com.pyrotemplar.kickballUmpireAssistant.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pyrotemplar.kickballUmpireAssistant.ClickerFragment.ClickerView;
import com.pyrotemplar.kickballUmpireAssistant.RulesFragment.RuleBookView;
import com.pyrotemplar.kickballUmpireAssistant.Settings.SettingsView;
import com.pyrotemplar.kickballUmpireAssistant.TabActivity.TabActivityPresenter;
import com.pyrotemplar.kickballUmpireAssistant.LeagueFragment.LeagueView;

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
