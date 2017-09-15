package com.pyrotemplar.kickballUmpireAssistant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyrotemplar.kickballUmpireAssistant.DataObjects.Team;
import com.pyrotemplar.kickballUmpireAssistant.R;

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
            name.setTextColor(team.getTeamColor());

        return convertView;
    }
}
