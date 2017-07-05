/*
 * Dice
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
package com.loge.thirthy.model;

import android.os.Parcel;
import android.os.Parcelable;

import static com.loge.thirthy.controller.GameActivity.MODE_HIGHLIGHTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SELECTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SHOW;

/**
 * Created by loge on 2017-06-16.
 */

public class Dice implements Parcelable {
    private final Die mDice[];
    private int mMode;

    public Dice(int size){
        mDice = new Die[size];
        for (int i = 0; i < size; i++){
            mDice[i] = new Die();
        }
    }


    public int getMode(){
        return mMode;
    }

    public Die getDie(int index){
        return mDice[index];
    }

    public void setDie(int index, Die die){
        mDice[index] = die;
    }

    public int size(){
        return mDice.length;
    }

    public int getFaceValue(int index){
        return mDice[index].getValue();
    }

    public void rollAllDice(){
        for (int i = 0 ; i < mDice.length; i++){
            mDice[i] = new Die();
        }
        setMode(0);
    }

    public void rollDie(int index){
        mDice[index] = new Die();
    }

    public void setMode(int mode){
        if(mode == MODE_SHOW){
            mMode = mode;
            for(Die die:mDice){
                die.setMode(MODE_SHOW);
            }
        } else if(mode == MODE_HIGHLIGHTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 2){
                    die.setMode(0);
                }
            }
        } else if (mode == MODE_SELECTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 1 ){
                    die.setMode(0);
                }
            }
        }
    }

    public void unselectAll(){
        for(Die die:mDice){
            die.setMode(0);
        }
    }

    public int calculatePoints(){
        int mPoints = 0;
        for (int i = 0; i < this.size(); i++){
            if (this.getDie(i).getMode()==1){
                mPoints += this.getDie(i).getValue();
            }
        }

        return mPoints;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(mDice, 0);
        dest.writeInt(mMode);
    }

    private Dice(Parcel in){
        mDice = in.createTypedArray(Die.CREATOR);
    }

    public static final Parcelable.Creator<Dice> CREATOR = new Parcelable.Creator<Dice>(){

        @Override
        public Dice createFromParcel(Parcel source) {
            return new Dice(source);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };


}
