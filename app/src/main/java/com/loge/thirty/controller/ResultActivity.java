/*
 * ResultActivity
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
package com.loge.thirty.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * ResultActivity
 *
 * Activity that hosts the Result Fragment.
 * Defines the ID for the results extra that is used
 * to transfer an int array with points from the Game
 * Activity/Fragment to the Result Fragment.
 */
public class ResultActivity extends SingleFragmentActivity {

    private static final String EXTRA_RESULT_ARRAY = "com.loge.thirty.results";

    /**
     * newIntent
     *
     * Used to Transfer result array as extra from Game to Result. This
     * method is called from Game and the data is handed over as argument.
     * @param packageContext
     * @param resultArray
     * @return
     */
    public static Intent newIntent(Context packageContext, int[] resultArray){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_RESULT_ARRAY, resultArray);
        return intent;
    }

    /**
     * CreateFragment
     *
     * Concrete implementation to create a
     * Result Fragment.
     * @return
     */
    @Override
    protected Fragment createFragment() {
        int[] resultArray = (int[]) getIntent().getSerializableExtra(EXTRA_RESULT_ARRAY);
        return ResultFragment.newInstance(resultArray);
    }


}
