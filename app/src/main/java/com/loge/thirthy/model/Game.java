/*
 * Game
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

/**
 *  Game
 *
 *  Model class that contains the logic regarding
 *  rounds, throws and points
 */
public class Game implements Parcelable {

    private static final int NUMBER_OF_ROUNDS = 10;

    private int mRound;
    private int mThrow;
    private final int[] mPoints;
    private final boolean[] mRoundCompleted;

    /**
     * Game
     *
     * Constructor
     */
    public Game() {
        mRound = 0;
        mThrow = 0;
        mPoints = new int[NUMBER_OF_ROUNDS];
        mRoundCompleted = new boolean[NUMBER_OF_ROUNDS];
    }

    /**
     * getRound
     *
     * returns an int in the range zero
     * to nine.
     * @return
     */
    public int getRound(){
        mRound = 0;
        for (Boolean round:mRoundCompleted){
            if(round){
                mRound++;
            }
        }
        return mRound;
    }

    /**
     * getThrow
     *
     * get current throw/roll nr. Return
     * values start from zero to 2.
     * @return
     */
    public int getThrow(){
        return mThrow;
    }

    /**
     * nextThrow
     *
     * Method advances counter to next throw/roll
     */
    public void nextThrow(){
        mThrow++;
    }

    /**
     * resetThrow
     *
     * Method resets throw/roll counter to zero
     */
    public void resetThrow(){
        mThrow = 0;
    }

    /**
     * setPoints
     *
     * Method sets the the number of points
     * for for the round 'index'.
     * @param index
     * @param points
     */
    public void setPoints(int index, int points){
        mPoints[index] = points;
        mRoundCompleted[index] = true;
    }

    /**
     * roundComplete
     *
     * returns a boolean whether the round with
     * 'index' has been completed
     * @param index
     * @return
     */
    public boolean roundComplete(int index){
        return mRoundCompleted[index];
    }


    /**
     * getPointsArray
     *
     * method returns the int[] array with
     * the points for each round.
     * @return
     */
    public int[] getPointsArray(){
        return mPoints;
    }

    /**
     * resetGame
     *
     * Method resets the points and throw/roll
     * variables.
     */
    public void resetGame(){
        this.resetPoints();
        this.resetThrow();
    }

    /**
     * resetPoints
     *
     * method resets the point array and the
     * boolean round completed array.
     */
    private void resetPoints(){
        for(int i = 0; i < NUMBER_OF_ROUNDS; i++){
            mPoints[i] = 0;
            mRoundCompleted[i] = false;
        }
    }

    /**
     * describeContents
     *
     * Mandatory method for Parcelable
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel
     *
     * method that writes round, throw, points array and
     * round completed array into a Parcel.
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRound);
        dest.writeInt(mThrow);
        dest.writeIntArray(mPoints);
        dest.writeBooleanArray(mRoundCompleted);
    }

    /**
     * Game
     *
     * private constructor to recreate state
     * after configuration change using parcels
     * for transient state storage.
     * @param in
     */
    private Game(Parcel in){
        mRound = in.readInt();
        mThrow = in.readInt();
        mPoints = in.createIntArray();
        mRoundCompleted = in.createBooleanArray();
    }

    /**
     * Parcelable.Creator
     *
     * static method that calls special contstructor to
     * recreate state after configuration change.
     */
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
