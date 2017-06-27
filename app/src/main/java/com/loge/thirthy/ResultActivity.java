package com.loge.thirthy;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ResultFragment();
    }
}
