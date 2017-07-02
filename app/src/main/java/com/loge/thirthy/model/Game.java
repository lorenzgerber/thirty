package com.loge.thirthy.model;

import android.content.Context;

/**
 * Created by loge on 2017-06-24.
 */

public class Game {

    private static int NUBMER_OF_ROUNDS = 10;

    private static Game sGame;

    private int mRound;
    private int mThrow;
    private int[] mPoints;
    private boolean[] mRoundCompleted;

    public static Game get(Context context){
        if (sGame == null) {
            sGame = new Game(context);
        }
        return sGame;
    }

    private Game(Context context) {
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
