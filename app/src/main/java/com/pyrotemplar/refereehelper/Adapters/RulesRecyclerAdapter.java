package com.pyrotemplar.refereehelper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.DataObjects.RuleBook;
import com.pyrotemplar.refereehelper.R;


import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Manuel Montes de Oca on 7/9/2017.
 */

public class RulesRecyclerAdapter extends RecyclerView.Adapter<RulesRecyclerAdapter.ViewHolder> {

    private final LayoutInflater inflator;
    private ClickListener clickListener;
    List<RuleBook> ruleBooks = Collections.emptyList();

    public RulesRecyclerAdapter(Context context, List<RuleBook> rulebooks) {
        inflator = LayoutInflater.from(context);
        this.ruleBooks = rulebooks;
    }

    public void updateList() {
        notifyDataSetChanged();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.rule_entre_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RuleBook ruleBook = ruleBooks.get(position);
        holder.ruleTitleTextView.setText(ruleBook.getTittle());
        holder.positionTextView.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return ruleBooks.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.ruleTitleTextView)
        TextView ruleTitleTextView;
        @BindView(R.id.positionTextView)
        TextView positionTextView;

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
