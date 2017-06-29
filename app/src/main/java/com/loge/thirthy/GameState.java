package com.loge.thirthy;

import android.content.Context;

/**
 * Created by loge on 2017-06-24.
 */

public class GameState {
    private static GameState sGameState;

    private int mRound;
    private int mThrow;
    private int[] mPoints;


    public static GameState get(Context context){
        if (sGameState == null) {
            sGameState = new GameState(context);
        }
        return sGameState;
    }


    private GameState(Context context) {
        mRound = 1;
        mThrow = 1;
        mPoints = new int[10];
    }

    public int getRound(){
        return mRound;
    }

    public void nextRound(){
        mRound++;
    }

    public void resetRound() {
        mRound = 1;
    }

    public int getThrow(){
        return mThrow;
    }

    public void nextThrow(){
        mThrow++;
    }

    public void resetThrow(){
        mThrow = 0;
    }

    public void setPoints(int index, int points){
        mPoints[index] = points;
    }

    public int getPoints(int index){
        return mPoints[index];
    }

    public int[] getPointsArray(){
        return mPoints;
    }

    public int getTotalPoints(){
        int mTotal = 0;
        for (int value:mPoints){
            mTotal += value;
        }
        return mTotal;
    }

    public void resetPoints(){
        for(int i = 0; i < 10; i++){
            mPoints[i] = 0;
        }
    }

}
