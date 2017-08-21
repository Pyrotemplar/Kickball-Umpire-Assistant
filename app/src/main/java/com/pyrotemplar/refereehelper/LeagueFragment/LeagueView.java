package com.pyrotemplar.refereehelper.LeagueFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Adapters.LeagueRecyclerAdapter;
import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.DataObjects.dataHelper;
import com.pyrotemplar.refereehelper.DialogFragments.AddNewTeamDialogFragment;
import com.pyrotemplar.refereehelper.R;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class LeagueView extends Fragment implements LeagueContract.View, LeagueRecyclerAdapter.ClickListener {

    @BindView(R.id.LeagueRecyclerView)
    RecyclerView leagueRecycler;

    private AddNewTeamDialogFragment addNewTeamDialogFragment;
    private LeagueRecyclerAdapter leagueRecyclerAdapter;
    private Realm realm;
    private RealmResults<Team> results;
    private static LeagueContract.Presenter mPresenter;

    private Bundle mArgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.league_layout, null);
        ButterKnife.bind(this, rootView);
        realm = Realm.getDefaultInstance();
        results = realm.where(Team.class).findAllAsync();
        addNewTeamDialogFragment = new AddNewTeamDialogFragment();
        addNewTeamDialogFragment.setTargetFragment(this, 2);

        mArgs = new Bundle();

        new LeaguePresenter(this);
        setUpRecyclerView();

        return rootView;

    }

    private RealmChangeListener changeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object o) {
            leagueRecyclerAdapter.update(results);
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        results = realm.where(Team.class).findAll();
        results.addChangeListener(changeListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        results.removeChangeListener(changeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        leagueRecycler.setAdapter(null);
        realm.close();
    }

    @Override
    public void itemClicked(View view, int position) {

    }

    @Override
    public void itemLongClicked(View view, int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {


            Team team = new Team(data.getStringExtra("name"), "", 0, "", 0);
           // realm.beginTransaction();
           // realm.copyToRealm(team);
           // realm.commitTransaction();
            dataHelper.addItem(realm, team);
            //realm.beginTransaction();
            //realm.copyToRealm(drop);
            // realm.commitTransaction();


            //  int position = data.getIntExtra(RULE_BOOK_POSITION, -1);
            if (-1 == -1) {
                //   mPresenter.saveRuleBook(mPresenter.createRuleBook(data.getStringExtra(RULE_BOOK_TITLE), data.getStringExtra(RULE_BOOK_URL), mPresenter.getRuleBookList().size()));
            } else {
            }
            //  mPresenter.editRuleBook(position, data.getStringExtra(RULE_BOOK_TITLE), data.getStringExtra(RULE_BOOK_URL));

            //rulesRecyclerAdapter.updateList();

            // saveRuleBookList();
        }

    }

    // private Team newTeam(String name) {
    // Team team = new Team();
    //team.setName(name);
    // return team;

    //}

    private ItemTouchHelper.Callback helperCallBack() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
              //  dataHelper.deleteItem();
                // mPresenter.removeRuleBook(viewHolder.getAdapterPosition());
                // rulesRecyclerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                //  viewHolder.setIsRecyclable(false);
                // rulesRecyclerAdapter.updateList();
            }
        };
        return simpleCallback;
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

    private void setUpRecyclerView() {

        leagueRecyclerAdapter = new LeagueRecyclerAdapter(results, true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        leagueRecycler.setLayoutManager(layoutManager);
        leagueRecyclerAdapter.setListener(this);
        leagueRecycler.setAdapter(leagueRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(leagueRecycler.getContext(), layoutManager.getOrientation());
        leagueRecycler.addItemDecoration(dividerItemDecoration);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helperCallBack());
        itemTouchHelper.attachToRecyclerView(leagueRecycler);

    }
}
