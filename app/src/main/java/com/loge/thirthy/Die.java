package com.loge.thirthy;

import java.util.Random;

/**
 * Created by loge on 2017-06-16.
 */

public class Die {

    private int mValue;
    private int mPosition;
    private int mMode;

    public Die(int position, int value){
        if(value < 1 || value > 6 ){
            throw new IllegalArgumentException();
        }
        mValue = value;
        mPosition = position;
        mMode = 0;
    }

    public int getValue(){
        return mValue;
    }
    public int getPosition() {return mPosition; }
    public int getMode(){return mMode; }
    public void setMode(int mode) {
        if (mode < 0 || mode > 2){
            throw new IllegalArgumentException();
        }
        mMode = mode;
    }
}
