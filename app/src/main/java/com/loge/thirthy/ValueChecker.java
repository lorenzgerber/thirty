package com.loge.thirthy;

import android.renderscript.Sampler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by loge on 2017-06-17.
 */

public class ValueChecker {

    private Dice mDice;
    private List<Combinations> mCombinations;

    public ValueChecker(Dice dice){
        mDice = dice;

    }

    public boolean[] checkFor(int faceValue){

        int mFaceValue = faceValue;
        int sumCounter;
        mCombinations = new ArrayList<>();

        boolean[] bitframe = new boolean[mDice.size()];
        final int total = mDice.size();
        final int [] num = new int[mDice.size()];
        int arrayCounter = 0;
        for(Die die : mDice){
            num[arrayCounter] = die.getValue();
            arrayCounter++;
        }


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
                if( bitframe[i] == true ) {
                    sumCounter = sumCounter + mDice.getFaceValue(i);
                }
            }
            if(sumCounter % mFaceValue == 0 && sumCounter/mFaceValue == 1){
                mCombinations.add(new Combinations(bitframe.clone()));
            }
        }

        // implement to to through sorted list of mCombinations and get
        // the combinations that together give maximal points


        Collections.sort(mCombinations);

        return bitframe;

    }

    public static int pow2(int power)
    {
        int ret = 1;
        for(int t=0;t<power;t++)
            ret <<= 1;
        return ret;
    }




    private class Combinations implements Comparable<Combinations>{
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

        public int getRank(){
            return this.mRank;
        }

        @Override
        public int compareTo(Combinations compareCombination) {
            int compareRank = ((Combinations) compareCombination).getRank();
            return this.getRank() - compareRank;
        }


    }


}
