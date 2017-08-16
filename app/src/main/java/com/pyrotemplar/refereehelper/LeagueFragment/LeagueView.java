package com.pyrotemplar.refereehelper.LeagueFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Adapters.LeagueRecyclerAdapter;
import com.pyrotemplar.refereehelper.Adapters.RulesRecyclerAdapter;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.RulesFragment.RuleBooksCotract;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class LeagueView extends Fragment implements LeagueContract.View, LeagueRecyclerAdapter.ClickListener  {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.league_layout, null);
        ButterKnife.bind(this, rootView);

        return rootView;

    }

    @Override
    public void itemClicked(View view, int position) {

    }

    @Override
    public void itemLongClicked(View view, int position) {

    }
    @OnClick(R.id.AddNewTeamButtonView)
    @Override
    public void AddNewTeamButtonClicked(View view) {

        Toast.makeText(getContext(), "helo test", Toast.LENGTH_SHORT).show();
        //addNewRulesBookLinkDialogFragment.setArguments(mArgs);
       // addNewRulesBookLinkDialogFragment.show(getFragmentManager(), "TAG");
    }
}
