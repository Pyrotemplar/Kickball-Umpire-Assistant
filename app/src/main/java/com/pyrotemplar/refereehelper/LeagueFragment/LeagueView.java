package com.pyrotemplar.refereehelper.LeagueFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Adapters.LeagueRecyclerAdapter;
import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.DataObjects.dataHelper;
import com.pyrotemplar.refereehelper.DialogFragments.AddNewTeamDialogFragment;
import com.pyrotemplar.refereehelper.DialogFragments.ConfirmationDialogFragment;
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

    public static final String TEAM_NAME = "teamName";
    public static final String TEAM_CAPTAIN_NAME = "teamCaptainName";
    public static final String TEAM_CAPTAIN_EMAIL = "teamCaptainEmail";
    public static final String TEAM_CAPTAIN_NUMBER = "teamCaptainNumber";
    public static final String TEAM_COLOR = "teamColor";
    public static final int REQUEST_CODE_EDIT = 10;
    public static final int REQUEST_CODE_NEW = 20;
    public static final int REQUEST_CODE_DELETE = 30;
    public static final String TEAM_TO_DELETE = "teamToDelete";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String POSITIVE_BUTTON = "positiveButton";


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
        results = realm.where(Team.class).findAllSorted(TEAM_NAME);
        addNewTeamDialogFragment = new AddNewTeamDialogFragment();
        // addNewTeamDialogFragment.setTargetFragment(this, 2);


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
        results = realm.where(Team.class).findAllSorted(TEAM_NAME);
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
    public void itemClicked(View view, String teamName) {

        Team team = dataHelper.getItem(realm, teamName);
        mArgs = new Bundle();
        mArgs.putString(TEAM_NAME, team.getTeamName());
       // mArgs.putString(TEAM_CAPTAIN_NAME, team.getCaptainName());
      //  mArgs.putString(TEAM_CAPTAIN_EMAIL, team.getCaptainEmail());
      //  mArgs.putInt(TEAM_CAPTAIN_NUMBER, team.getCaptainPhoneNumber());
        mArgs.putInt(TEAM_COLOR, team.getTeamColor());
        addNewTeamDialogFragment.setArguments(mArgs);
        addNewTeamDialogFragment.setTargetFragment(this, REQUEST_CODE_EDIT);
        addNewTeamDialogFragment.show(getFragmentManager(), "TAG");
    }

    @Override
    public void itemLongClicked(View view, String teamName) {
        Bundle mArgs = new Bundle();
        mArgs.putString(TEAM_TO_DELETE, teamName);
        mArgs.putString(TITLE, "Delete Team");
        mArgs.putString(MESSAGE, "Delete Team "+ teamName +"?");
        mArgs.putString(POSITIVE_BUTTON, "Delete");
        ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
        confirmationDialogFragment.setArguments(mArgs);
        confirmationDialogFragment.setTargetFragment(this, REQUEST_CODE_DELETE);
        confirmationDialogFragment.show(getFragmentManager(), "TAG");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CODE_NEW || requestCode == REQUEST_CODE_EDIT) {
/*
                Team team = new Team(data.getStringExtra(TEAM_NAME), data.getStringExtra(TEAM_CAPTAIN_NAME),
                        data.getStringExtra(TEAM_CAPTAIN_EMAIL), Integer.parseInt(data.getStringExtra(TEAM_CAPTAIN_NUMBER)), data.getIntExtra(TEAM_COLOR, 0));
*/
                Team team = new Team(data.getStringExtra(TEAM_NAME), "Temp","TEMP", 000000000, data.getIntExtra(TEAM_COLOR, 0));

                dataHelper.addItem(realm, team);
            } else if (requestCode == REQUEST_CODE_DELETE) {
                String teamName = data.getStringExtra(TEAM_TO_DELETE);
                dataHelper.deleteItem(realm, teamName);
                leagueRecyclerAdapter.notifyDataSetChanged();
            }
        }

    }

    /*private ItemTouchHelper.Callback helperCallBack() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

              Team team = leagueRecyclerAdapter.getItem(viewHolder.getAdapterPosition());

                dataHelper.deleteItem(realm, "Test");
              //  dataHelper.deleteItem(viewHolder.itemView.);
                // mPresenter.removeRuleBook(viewHolder.getAdapterPosition());
                // rulesRecyclerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                //  viewHolder.setIsRecyclable(false);
                // rulesRecyclerAdapter.updateList();
            }
        };
        return simpleCallback;
    }*/


    @OnClick(R.id.AddNewTeamButtonView)
    @Override
    public void AddNewTeamButtonClicked(View view) {
        addNewTeamDialogFragment.setArguments(null);
        addNewTeamDialogFragment.setTargetFragment(this, REQUEST_CODE_NEW);
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

        //  ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helperCallBack());
        //  itemTouchHelper.attachToRecyclerView(leagueRecycler);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity activity = getActivity();
            if (activity != null)
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }
    }
}
