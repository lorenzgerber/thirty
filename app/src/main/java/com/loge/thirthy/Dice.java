package com.loge.thirthy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by loge on 2017-06-16.
 */

public class Dice implements Iterable<Die> {
    private final List<Die> mDice;

    public static class Builder {
        private final List<Die> mDice;
        private final int mSetSize;

        public Builder(int setSize){
            mSetSize = setSize;
            mDice = new ArrayList<>();
        }

        public Builder addDie(Die die){
            if(mDice.size() < mSetSize) {
                mDice.add(die);
            } else {
                throw new IllegalArgumentException();
            }
            return this; }

        public Dice build(){
            return new Dice(this);
        }

    }

    private Dice (Builder builder){
        if(builder.mDice.size() == builder.mSetSize){
            mDice = builder.mDice;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Iterator<Die> iterator() {
        Iterator<Die> iDie = mDice.iterator();
        return iDie;
    }

    public int size(){
        return mDice.size();
    }

    public int getFaceValue(int index){
        return mDice.get((index % mDice.size())).getValue();
    }

}
