package com.pyrotemplar.refereehelper;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by mmontesd on 4/21/2017.
 */

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {




        View rootView = inflater.inflate(R.layout.umpire_clicker_layout,null);

        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);

        View rightHandLayoutView =  inflater.inflate(R.layout.right_hand_clicker_layout,null);
        frameLayout.addView(rightHandLayoutView);


        return rootView;
    }
}
