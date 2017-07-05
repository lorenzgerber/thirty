/*
 * GameActivity
 *
 * Thirty Project, an Android implementation
 * of the Dice game 'thirty'.
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Sumemr Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 */
package com.loge.thirthy.controller;

import android.support.v4.app.Fragment;

/**
 * GameActivity
 *
 * Activity that hosts the GameFragment.
 * Constants that shall be accessible in multiple
 * game related classes are defined here.
 */
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
