package com.pyrotemplar.refereehelper.Utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

// Custom ViewPager to disable horizontal scrolling of each page.

public class NonSwipeViewPager extends ViewPager {
    private boolean swipePageChangeEnabled;

    public NonSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.swipePageChangeEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipePageChangeEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.swipePageChangeEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.swipePageChangeEnabled = enabled;
    }
}

