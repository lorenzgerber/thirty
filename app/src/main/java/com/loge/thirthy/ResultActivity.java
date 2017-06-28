package com.loge.thirthy;

import android.support.v4.app.Fragment;

public class ResultActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ResultFragment();
    }
}
