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


import android.support.annotation.NonNull;

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

    private final Dice mDice;

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


    /**
     * getPoints
     *
     * Method that calculates
     * the the current point value based on
     * the selected combination.
     * @param faceValue
     * @return
     */
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

    /**
     * getCombination
     *
     * Main algorithm that returns the best
     * combination for the chosen Facevalue
     * @param faceValue
     * @return
     */
    public boolean[] getCombination(int faceValue){

        /*
        Special case selection 'LOW'
         */
        int COMBINATION_LOW = 3;
        if (faceValue == COMBINATION_LOW){
            return getLowCombination(mDice);
        }

        List<Combinations> combinations = new ArrayList<>();
        buildCombinationsList(faceValue, combinations);

        boolean[] bitframe = new boolean[mDice.size()];
        Arrays.fill(bitframe, false);
        Collections.sort(combinations);

        return findBestCombination(combinations);

    }

    /**
     * findBestCombination
     *
     * returns the combination that reaches
     * the highest total score with the fewest
     * dice.
     * @param combinations
     * @return
     */
    private boolean[] findBestCombination(List<Combinations> combinations) {
        boolean resultFrame[] = new boolean[mDice.size()];
        Arrays.fill(resultFrame, false);
        boolean doesNotFit;
        for(Combinations combination : combinations){
            doesNotFit = false;
            for(int i = 0 ; i < mDice.size(); i++){
                if(resultFrame[i] == combination.getBitframe()[i] && resultFrame[i]) {
                    doesNotFit = true;
                }
            }
            if(!doesNotFit){
                for(int i = 0 ; i < mDice.size(); i++){
                    if(combination.getBitframe()[i]){
                        resultFrame[i] = true;
                    }
                }
            }
        }
        return resultFrame;
    }

    /**
     * buildCombinationsList
     *
     * method that populates a combinations List
     * with valid die combinations for the chose
     * faceValue. Bruteforce, iterating over all
     * possibilities.
     * @param faceValue
     * @param combinations
     */
    private void buildCombinationsList(int faceValue, List<Combinations> combinations) {
        int sumCounter;
        boolean[] bitframe = new boolean[mDice.size()];
        final int total = mDice.size();
        for(int x=1;x<ValueChecker.pow2(total);x++) {
            for(int i=0;i<total;i++)
            {
                // if the ith bit of x is not 0
                bitframe[i] = ((1 << i) & x) != 0;
            }

            // find all Die combinations that divide by facevalue
            sumCounter = 0;
            for (int i = 0;i < total; i++) {
                if( bitframe[i]) {
                    sumCounter = sumCounter + mDice.getFaceValue(i);
                }
            }
            if(sumCounter % faceValue == 0 && sumCounter/ faceValue == 1){
                combinations.add(new Combinations(bitframe.clone()));
            }
        }
    }

    /**
     * getLowCombination
     *
     * Method that returns the dice combination
     * for the selection 'Low' (all dice with
     * a face value below 4)
     * @param dice
     * @return
     */
    private boolean[] getLowCombination(Dice dice) {
        boolean resultFrame[] = new boolean[dice.size()];
        for(int i = 0; i < dice.size(); i++){
            if(dice.getFaceValue(i) < 4){
               resultFrame[i] = true;
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
    private static int pow2(int power)
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
        final boolean[] mBitframe;

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
        public int compareTo(@NonNull Combinations compareCombination) {
            int compareRank = compareCombination.getRank();
            return this.getRank() - compareRank;
        }
    }
}
