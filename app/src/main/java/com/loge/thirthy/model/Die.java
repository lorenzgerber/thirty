/*
 * Die
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

import java.util.Random;

/**
 * Die
 *
 * Model class that hosts the logic for
 * the a die. This includes the value
 * and the current mode of the die.
 */
public class Die implements Parcelable {

    private int mValue;
    private int mMode;

    /**
     * Die
     *
     * Constructor that will generate a
     * die object with random value.
     */
    public Die(){
        Random random = new Random();
        mValue = random.nextInt(6) + 1;
        mMode = 0;
    }

    /**
     * Die
     *
     * Constructor that will generate a
     * die object and assign the provided
     * value from 1 to 6 to it.
     * @param value
     */
    public Die(int value){
        if(value < 1 || value > 6 ){
            throw new IllegalArgumentException();
        }
        mValue = value;
        mMode = 0;
    }

    /**
     * getValue
     *
     * Getter for face value
     * @return
     */
    public int getValue(){
        return mValue;
    }

    /**
     * getMode
     *
     * Returns the current mode of the
     * die (0 = Show, 1 = Highlighted,
     * 2 = Selected)
     * @return
     */
    public int getMode(){return mMode; }
    public void setMode(int mode) {
        if (mode < 0 || mode > 2){
            throw new IllegalArgumentException();
        }
        mMode = mode;
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
     * method that writes the value and mode to a parcel
     * for state transfer during configuration change.
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mValue);
        dest.writeInt(mMode);
    }

    /**
     * Die
     *
     * private constructor to recreate state
     * after configuration change using parcels
     * for transient state storage.
     * @param in
     */
    private Die(Parcel in){
        mValue = in.readInt();
        mMode = in.readInt();
    }

    /**
     * Parcelable.Creator
     *
     * static method that calls special contstructor to
     * recreate state after configuration change.
     */
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
