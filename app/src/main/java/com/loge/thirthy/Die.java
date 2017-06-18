package com.loge.thirthy;

import java.util.Random;

/**
 * Created by loge on 2017-06-16.
 */

public class Die {

    private int mValue;

    public Die(int value){
        if(value < 1 || value > 6 ){
            throw new IllegalArgumentException();
        }
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }


}
