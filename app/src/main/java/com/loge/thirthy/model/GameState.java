package com.loge.thirthy.model;

import android.content.Context;

/**
 * Created by loge on 2017-06-24.
 */

public class GameState {

    private static int NUBMER_OF_ROUNDS = 10;

    private static GameState sGameState;

    private int mRound;
    private int mThrow;
    private int[] mPoints;
    private boolean[] mRoundCompleted;

    public static GameState get(Context context){
        if (sGameState == null) {
            sGameState = new GameState(context);
        }
        return sGameState;
    }

    private GameState(Context context) {
        mRound = 0;
        mThrow = 0;
        mPoints = new int[NUBMER_OF_ROUNDS];
        mRoundCompleted = new boolean[NUBMER_OF_ROUNDS];
    }

    public int getRound(){
        mRound = 0;
        for (Boolean round:mRoundCompleted){
            if(round){
                mRound++;
            }
        }
        return mRound;
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
        mRoundCompleted[index] = true;
    }


    public int[] getPointsArray(){
        return mPoints;
    }

    public void resetGame(){
        this.resetPoints();
        this.resetThrow();
    }

    public void resetPoints(){
        for(int i = 0; i < NUBMER_OF_ROUNDS; i++){
            mPoints[i] = 0;
            mRoundCompleted[i] = false;
        }
    }

}
