package com.loge.thirthy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by loge on 2017-06-24.
 */

public class Game implements Parcelable {

    private static int NUBMER_OF_ROUNDS = 10;

    private int mRound;
    private int mThrow;
    private int[] mPoints;
    private boolean[] mRoundCompleted;

    public Game() {
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

    public boolean roundComplete(int index){
        return mRoundCompleted[index];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRound);
        dest.writeInt(mThrow);
        dest.writeIntArray(mPoints);
        dest.writeBooleanArray(mRoundCompleted);
    }

    private Game(Parcel in){
        mRound = in.readInt();
        mThrow = in.readInt();
        mPoints = in.createIntArray();
        mRoundCompleted = in.createBooleanArray();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {

        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

}
