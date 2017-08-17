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
import com.pyrotemplar.refereehelper.Adapters.RulesRecyclerAdapter;
import com.pyrotemplar.refereehelper.DialogFragments.AddNewTeamDialogFragment;
import com.pyrotemplar.refereehelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class LeagueView extends Fragment implements LeagueContract.View, LeagueRecyclerAdapter.ClickListener {

    @BindView(R.id.LeagueRecyclerView)
    RecyclerView leagueRecycler;

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


        leagueRecyclerAdapter = new LeagueRecyclerAdapter(getContext());
        leagueRecyclerAdapter.setListener(this);
        leagueRecycler.setAdapter(leagueRecyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        leagueRecycler.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(leagueRecycler.getContext(), layoutManager.getOrientation());
        leagueRecycler.addItemDecoration(dividerItemDecoration);


        return rootView;

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

    private ItemTouchHelper.Callback helperCallBack() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
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
}
