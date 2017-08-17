package com.pyrotemplar.refereehelper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyrotemplar.refereehelper.R;

import butterknife.ButterKnife;

/**
 * Created by Manuel Montes de Oca on 8/16/2017.
 */

public class LeagueRecyclerAdapter extends RecyclerView.Adapter<LeagueRecyclerAdapter.ViewHolder>  {
    private final LayoutInflater inflator;
    private LeagueRecyclerAdapter.ClickListener clickListener;


    public LeagueRecyclerAdapter(Context context) {
        inflator = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.team_entree_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                clickListener.itemLongClicked(v, getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
        void itemLongClicked(View view, int position);
    }

    public void setListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}


