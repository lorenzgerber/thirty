/*
 * ResultActivity
 *
 * Thirty Project,
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Sumemr Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 *
 */

package com.loge.thirthy.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class ResultActivity extends SingleFragmentActivity {

    public static final String EXTRA_RESULT_ARRAY = "com.loge.thirty.results";

    public static Intent newIntent(Context packageContext, int[] resultArray){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_RESULT_ARRAY, resultArray);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        int[] resultArray = (int[]) getIntent().getSerializableExtra(EXTRA_RESULT_ARRAY);
        return ResultFragment.newInstance(resultArray);
    }


}
