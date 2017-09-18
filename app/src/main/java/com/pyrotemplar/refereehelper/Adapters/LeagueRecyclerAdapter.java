package com.pyrotemplar.refereehelper.Adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by Manuel Montes de Oca on 8/16/2017.
 */

public class LeagueRecyclerAdapter extends RealmRecyclerViewAdapter<Team, LeagueRecyclerAdapter.ViewHolder> {


    private LeagueRecyclerAdapter.ClickListener clickListener;
    private List<Team> teamList = new ArrayList<>();

    public LeagueRecyclerAdapter(@Nullable OrderedRealmCollection<Team> data, boolean autoUpdate) {
        super(data, autoUpdate);
        update(data);
    }

    public void update(OrderedRealmCollection<Team> data) {
        teamList = data;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_entree_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.teamPositionTextView.setText(Integer.toString(position + 1));
        holder.teamNameTextView.setText(team.getTeamName());
        holder.teamColorView.setBackgroundColor(team.getTeamColor());
    }


    @Override
    public int getItemCount() {
        return teamList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.teamPositionTextView)
        TextView teamPositionTextView;
        @BindView(R.id.teamNameTextView)
        TextView teamNameTextView;
        @BindView(R.id.teamColorView)
        View teamColorView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClicked(v, teamList.get(getAdapterPosition()).getTeamName());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                clickListener.itemLongClicked(v, teamList.get(getAdapterPosition()).getTeamName());
                return true;
            }
            return false;
        }
    }

    public interface ClickListener {
        void itemClicked(View view, String teamName);

        void itemLongClicked(View view, String teamName);
    }

    public void setListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}


