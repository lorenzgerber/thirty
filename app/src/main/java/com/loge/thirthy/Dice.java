package com.loge.thirthy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by loge on 2017-06-16.
 */

public class Dice {
    private final Die mDice[];

    private int mMode;

    public static final int MODE_SHOW = 0;
    public static final int MODE_HIGHLIGHTED = 1;
    public static final int MODE_SELECTED = 2;

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

}
