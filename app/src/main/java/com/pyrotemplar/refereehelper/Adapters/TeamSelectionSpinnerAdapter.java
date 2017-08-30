package com.pyrotemplar.refereehelper.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.DataObjects.Team;
import com.pyrotemplar.refereehelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Montes de Oca on 8/29/2017.
 */

public class TeamSelectionSpinnerAdapter extends BaseAdapter {


    Context context;
    List<Team> teamList;
    LayoutInflater inflater;

    public TeamSelectionSpinnerAdapter(Context context, List<Team> teamList) {
        this.teamList = teamList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return teamList.size();
    }

    @Override
    public Team getItem(int position) {
        return teamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_team_dropdown_spinner_layout, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.teamNameSpinnerTextView);
        Team team = getItem(position);
        if (team != null)
            name.setText(team.getTeamName());

        return convertView;
    }
}
