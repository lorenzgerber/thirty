package com.loge.thirthy.controller;

import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new GameFragment();

    }
}
