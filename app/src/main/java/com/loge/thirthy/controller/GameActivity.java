package com.loge.thirthy.controller;

import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity {

    public static String FRAGMENT_KEY = "com.loge.thirty.game";
    public static final int MODE_SHOW = 0;
    public static final int MODE_HIGHLIGHTED = 1;
    public static final int MODE_SELECTED = 2;

    @Override
    protected Fragment createFragment(){
        return new GameFragment();

    }
}
