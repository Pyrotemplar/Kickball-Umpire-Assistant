package com.pyrotemplar.refereehelper.LeagueFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Adapters.LeagueRecyclerAdapter;
import com.pyrotemplar.refereehelper.DialogFragments.AddNewTeamDialogFragment;
import com.pyrotemplar.refereehelper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class LeagueView extends Fragment implements LeagueContract.View, LeagueRecyclerAdapter.ClickListener  {

    private AddNewTeamDialogFragment addNewTeamDialogFragment;
    private LeagueRecyclerAdapter leagueRecyclerAdapter;
    private static LeagueContract.Presenter mPresenter;

    private Bundle mArgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.league_layout, null);
        ButterKnife.bind(this, rootView);

        addNewTeamDialogFragment = new AddNewTeamDialogFragment();
        addNewTeamDialogFragment.setTargetFragment(this, 2);

        mArgs = new Bundle();

        new LeaguePresenter(this);

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
        addNewTeamDialogFragment.setArguments(mArgs);
        addNewTeamDialogFragment.show(getFragmentManager(), "TAG");

    }

    @Override
    public void setPresenter(LeaguePresenter presenter) {
        mPresenter = presenter;
    }
}
