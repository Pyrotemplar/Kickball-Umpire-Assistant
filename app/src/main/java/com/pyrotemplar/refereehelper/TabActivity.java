package com.pyrotemplar.refereehelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.pyrotemplar.refereehelper.Adapters.PageAdapter;
import com.pyrotemplar.refereehelper.Utils.NonSwipeViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class TabActivity extends AppCompatActivity implements TabActivityView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.pager)
    NonSwipeViewPager mPager;

    private PageAdapter mPageAdapter;
    private TabActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_layout);
        ButterKnife.bind(this);

        presenter = new TabActivityPresenter(this);

        mPageAdapter = new PageAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPageAdapter);
        //disable the scrolling of views.
        mPager.setPagingEnabled(false);

        mTabLayout.setupWithViewPager(mPager);

        setTabIcons(mTabLayout, getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void setTabIcons(TabLayout tabLayout, Context context) {
        presenter.setTabIcons(mTabLayout, context);
    }
}

