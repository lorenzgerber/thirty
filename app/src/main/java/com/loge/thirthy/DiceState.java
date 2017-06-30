package com.loge.thirthy;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by loge on 2017-06-22.
 */

public class DiceState {
    private static DiceState sDiceState;

    private Dice mDice;
    private ArrayList<CombinationListItem> mCombinationsLeft;
    private Random random;

    public static DiceState get(Context context){
        if (sDiceState == null) {
            sDiceState = new DiceState(context);
        }
        return sDiceState;
    }

    private DiceState(Context context){
        random = new Random();
        mDice = new Dice(6);
        buildCombinationsList();

    }

    public Dice getDice(){
        return mDice;
    }

    public ArrayList<CombinationListItem> getCombinationsLeft(){
        return mCombinationsLeft;
    }

    public void rollAllDice(){
        mDice = new Dice(6);
    }


    public void rollDice(int index){
        mDice.setDie(index, new Die());
    }

    public void buildCombinationsList(){
        mCombinationsLeft = new ArrayList<>();
        mCombinationsLeft.add(new CombinationListItem(2,"Choose Combination..."));
        mCombinationsLeft.add(new CombinationListItem(3, "Low"));
        mCombinationsLeft.add(new CombinationListItem(4, "Four"));
        mCombinationsLeft.add(new CombinationListItem(5, "Five"));
        mCombinationsLeft.add(new CombinationListItem(6, "Six"));
        mCombinationsLeft.add(new CombinationListItem(7, "Seven"));
        mCombinationsLeft.add(new CombinationListItem(8, "Eight"));
        mCombinationsLeft.add(new CombinationListItem(9, "Nine"));
        mCombinationsLeft.add(new CombinationListItem(10, "Ten"));
        mCombinationsLeft.add(new CombinationListItem(11, "Eleven"));
        mCombinationsLeft.add(new CombinationListItem(12, "Twelve"));
    }

    public void resetCombinationsList(){
        mCombinationsLeft.add(new CombinationListItem(3, "Low"));
        mCombinationsLeft.add(new CombinationListItem(4, "Four"));
        mCombinationsLeft.add(new CombinationListItem(5, "Five"));
        mCombinationsLeft.add(new CombinationListItem(6, "Six"));
        mCombinationsLeft.add(new CombinationListItem(7, "Seven"));
        mCombinationsLeft.add(new CombinationListItem(8, "Eight"));
        mCombinationsLeft.add(new CombinationListItem(9, "Nine"));
        mCombinationsLeft.add(new CombinationListItem(10, "Ten"));
        mCombinationsLeft.add(new CombinationListItem(11, "Eleven"));
        mCombinationsLeft.add(new CombinationListItem(12, "Twelve"));
    }

}
