package com.pyrotemplar.refereehelper.Presenter;

import android.view.View;
import android.widget.TextView;

import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.View.ClickerFragmentView;

import butterknife.BindView;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class ClickerPresenter  implements ClickerFragmentView{

    @BindView(R.id.awayTeamNameTextView)
    TextView getHomeTeamNameTextView;
    @BindView(R.id.homeTeamNameTextView)
    TextView homeTeamNameTextView;
    @BindView(R.id.awayTeamScoreTextView)
    TextView awayTeamScoreTextView;
    @BindView(R.id.homeTeamScoreTextView)
    TextView homeTeamScoreTextView;
    @BindView(R.id.inningTextViewCount)
    TextView inningTextViewCount;
    @BindView(R.id.ballCountTextView)
    TextView ballCountTextView;
    @BindView(R.id.strikeCountTextView)
    TextView strikeCountTextView;
    @BindView(R.id.foulCountTextView)
    TextView foulCountTextView;
    @BindView(R.id.outCountTextView)
    TextView outCountTextView;

    private View view;

    public ClickerPresenter(View view) {
        this.view = view;
    }

    @Override
    public void ballButtonClicked() {

    }

    @Override
    public void strikeButtonClicked() {

    }

    @Override
    public void foulButtonClicked() {

    }

    @Override
    public void outButtonClicked() {

    }

    @Override
    public void kickerIsSafeButtonClicked() {

    }

    @Override
    public void runnerScoredButtonClicked() {

    }
}
