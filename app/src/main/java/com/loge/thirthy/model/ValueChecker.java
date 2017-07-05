/*
 * ValueChecker
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * ValueChecker
 *
 * Model class that contains the algorithm to determine
 * the which dice to choose for a specific round. The algorithm
 * loops through all 2^6 possible combinations using a binary
 * frame, testing for divisibility of the requested round face
 * value (bruteforce). Then sort is applied to the found combinations to find
 * those using the fewest die to reach the highest possible score.
 */
public class ValueChecker {

    private final int COMBINATION_LOW = 3;

    private Dice mDice;
    private List<Combinations> mCombinations;

    /**
     * ValueChecker
     *
     * Constructor takes the dice set
     * to check as argument.
     * @param dice
     */
    public ValueChecker(Dice dice){
        mDice = dice;
    }


    public int getPoints(int faceValue){
        int points = 0;
        boolean bitArray[] = getCombination(faceValue);
        for (int i = 0; i < mDice.size(); i++){
            if(bitArray[i]){
                points += mDice.getFaceValue(i);
            }
        }

        return points;
    }

    public boolean[] getCombination(int faceValue){

        int mFaceValue = faceValue;
        int sumCounter;
        mCombinations = new ArrayList<>();
        boolean resultFrame[] = new boolean[mDice.size()];


        /*
        Taking care of special case '3'
         */
        if (mFaceValue == COMBINATION_LOW){
            for(int i = 0; i < mDice.size(); i++){
                if(mDice.getFaceValue(i) < 4){
                   resultFrame[i] = true;
                }
            }

            return resultFrame;
        }

        boolean[] bitframe = new boolean[mDice.size()];
        final int total = mDice.size();
        for(int x=1;x<ValueChecker.pow2(total);x++)
        {
            for(int i=0;i<total;i++)
            {
                // if the ith bit of x is not 0
                if( ( ( 1 << i ) & x ) != 0 )
                    bitframe[i] = true;
                else
                    bitframe[i] = false;
            }

            // find all Die combinations that divide by facevalue
            sumCounter = 0;
            for (int i = 0;i < total; i++) {
                if( bitframe[i]) {
                    sumCounter = sumCounter + mDice.getFaceValue(i);
                }
            }
            if(sumCounter % mFaceValue == 0 && sumCounter/mFaceValue == 1){
                mCombinations.add(new Combinations(bitframe.clone()));
            }
        }

        Arrays.fill(bitframe, false);
        Collections.sort(mCombinations);

        Arrays.fill(resultFrame, false);

        boolean doesNotFit;
        for(Combinations combination : mCombinations){
            doesNotFit = false;
            for(int i = 0 ; i < mDice.size(); i++){
                if(resultFrame[i] == combination.getBitframe()[i] && resultFrame[i] == true) {
                    doesNotFit = true;
                }
            }
            if(!doesNotFit){
                for(int i = 0 ; i < mDice.size(); i++){
                    if(combination.getBitframe()[i]){
                        resultFrame[i] = true;
                    };
                }
            }
        }

        return resultFrame;
    }

    /**
     * pow2
     *
     * Helper method to calculate the n-th
     * power of 2.
     * @param power
     * @return
     */
    public static int pow2(int power)
    {
        int ret = 1;
        for(int t=0;t<power;t++)
            ret <<= 1;
        return ret;
    }

    /**
     * Combinations
     *
     * inner class of Combinations which consist of
     * a boolean bitframe and the rank. The rank depices
     * how many times the facevalue fits into the chosen
     * combination. The boolean bitframe corresponds to the
     * die, determining whether they are included or not in
     * the combination.
     */
    private class Combinations implements Comparable<Combinations> {
        int mRank = 0;
        boolean[] mBitframe;

        public Combinations(boolean[] bitframe){

            mBitframe = bitframe;

            for(boolean bool : mBitframe){
                if(bool){
                    mRank++;
                }
            }
        }

        /**
         * getRank
         *
         * Getter method for the rank
         * @return
         */
        public int getRank(){
            return this.mRank;
        }

        /**
         * getBitframe
         *
         * getter method for the boolean array 'bitframe' that
         * expresses which die is included in current combination.
         * @return
         */
        public boolean[] getBitframe() {
            return this.mBitframe;
        }

        /**
         * compareTo
         *
         * Compare function for the combinations inner class
         * to allow sorting according to the rank.
         * @param compareCombination
         * @return
         */
        @Override
        public int compareTo(Combinations compareCombination) {
            int compareRank = ((Combinations) compareCombination).getRank();
            return this.getRank() - compareRank;
        }
    }
}
