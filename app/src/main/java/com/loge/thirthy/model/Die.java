package com.loge.thirthy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by loge on 2017-06-16.
 */

public class Die implements Parcelable {

    private Random random;

    private int mValue;
    private int mMode;

    public Die(int value){
        if(value < 1 || value > 6 ){
            throw new IllegalArgumentException();
        }
        mValue = value;
        mMode = 0;
    }

    public Die(){
        random = new Random();
        mValue = random.nextInt(6) + 1;
        mMode = 0;
    }

    public int getValue(){
        return mValue;
    }
    public int getMode(){return mMode; }
    public void setMode(int mode) {
        if (mode < 0 || mode > 2){
            throw new IllegalArgumentException();
        }
        mMode = mode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mValue);
        dest.writeInt(mMode);
    }

    private Die(Parcel in){
        mValue = in.readInt();
        mMode = in.readInt();
    }

    public static final Parcelable.Creator<Die> CREATOR = new Parcelable.Creator<Die>() {

        @Override
        public Die createFromParcel(Parcel source) {
            return new Die(source);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };

    

}
