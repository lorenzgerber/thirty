package com.loge.thirthy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by loge on 2017-06-16.
 */

public class Dice implements Iterable<Die> {
    private final List<Die> mDice;

    private int mMode;

    public static final int MODE_SHOW = 0;
    public static final int MODE_HIGHLIGHTED = 1;
    public static final int MODE_SELECTED = 2;

    public static class Builder {
        private final List<Die> mDice;
        private final int mSetSize;

        public Builder(int setSize){
            mSetSize = setSize;
            mDice = new ArrayList<>();
        }

        public Builder addDie(Die die){

            for(Die lDie:mDice){
                if(lDie.getPosition() == die.getPosition()){
                    throw new IllegalArgumentException();
                }
            }

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
            mMode = MODE_SHOW;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Iterator<Die> iterator() {
        Iterator<Die> iDie = mDice.iterator();
        return iDie;
    }

    public int getMode(){
        return mMode;
    }

    public Die getDie(int index){
        return mDice.get(index);
    }

    public void setDie(int index, Die die){
        mDice.set(index, die);
    }

    public int size(){
        return mDice.size();
    }

    public int getFaceValue(int index){
        return mDice.get((index % mDice.size())).getValue();
    }

    public void setMode(int mode){
        if(mode == MODE_SHOW){
            mMode = mode;
            for(Die die:mDice){
                die.setMode(MODE_SHOW);
            }
        } else if(mode == MODE_HIGHLIGHTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 2){
                    die.setMode(0);
                }
            }
        } else if (mode == MODE_SELECTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 1 ){
                    die.setMode(0);
                }
            }
        }
    }

    public void unselectAll(){
        for(Die die:mDice){
            die.setMode(0);
        }
    }

}
