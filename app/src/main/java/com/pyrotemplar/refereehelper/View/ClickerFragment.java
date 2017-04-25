package com.pyrotemplar.refereehelper.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.Presenter.ClickerPresenter;
import com.pyrotemplar.refereehelper.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Manuel Montes de Oca on 4/21/2017.
 */

public class ClickerFragment extends Fragment implements ClickerFragmentView {

    private ClickerPresenter clickerPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clicker_layout, null);

        clickerPresenter = new ClickerPresenter(rootView);

        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);
        View rightHandLayoutView = inflater.inflate(R.layout.right_hand_clicker_layout, null);
        frameLayout.addView(rightHandLayoutView);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.ballButton)
    @Override
    public void ballButtonClicked() {
        clickerPresenter.ballButtonClicked();
    }

    @OnClick(R.id.strikeButton)
    @Override
    public void strikeButtonClicked() {
        clickerPresenter.strikeButtonClicked();
    }

    @OnClick(R.id.foulButton)
    @Override
    public void foulButtonClicked() {
        clickerPresenter.foulButtonClicked();
    }

    @OnClick(R.id.outButton)
    @Override
    public void outButtonClicked() {
        clickerPresenter.outButtonClicked();
    }

    @OnClick(R.id.kickerIsSafeButton)
    @Override
    public void kickerIsSafeButtonClicked() {
        clickerPresenter.kickerIsSafeButtonClicked();
    }

    @OnClick(R.id.runnerScoredButton)
    @Override
    public void runnerScoredButtonClicked() {
        clickerPresenter.runnerScoredButtonClicked();
    }
}