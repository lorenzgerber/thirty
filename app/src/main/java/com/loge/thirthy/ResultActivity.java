package com.loge.thirthy;

import android.support.v4.app.Fragment;

public class ResultActivity extends SingleFragmentActivity {

    public static final String EXTRA_RESULT_ARRAY = "com.loge.thirty.results";

    @Override
    protected Fragment createFragment() {
        int[] resultArray = (int[]) getIntent().getSerializableExtra(EXTRA_RESULT_ARRAY);
        return ResultFragment.newInstance(resultArray);
    }


}
