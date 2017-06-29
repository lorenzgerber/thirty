package com.loge.thirthy;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends SingleFragmentActivity {

    public static final String EXTRA_RESULT_ARRAY = "com.loge.thirty.results";

    public static Intent newIntent(Context packageContext, int[] resultArray){
        Intent intent = new Intent(packageContext, GameActivity.class);
        intent.putExtra(EXTRA_RESULT_ARRAY, resultArray);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new GameFragment();

    }
}
