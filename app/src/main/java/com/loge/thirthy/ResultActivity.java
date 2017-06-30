package com.loge.thirthy;

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
