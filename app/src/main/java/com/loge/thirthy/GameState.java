package com.loge.thirthy;

import android.content.Context;

import java.util.Random;

/**
 * Created by loge on 2017-06-22.
 */

public class GameState {
    private static GameState sGameState;

    private Dice mDice;


    public static GameState get(Context context){
        if (sGameState == null) {
            sGameState = new GameState(context);
        }
        return sGameState;
    }

    private GameState(Context context){
        Random rn = new Random();
        mDice = new Dice.Builder(6)
                .addDie(new Die(0,rn.nextInt(6) + 1 ))
                .addDie(new Die(1, rn.nextInt(6) + 1 ))
                .addDie(new Die(2, rn.nextInt(6) + 1 ))
                .addDie(new Die(3, rn.nextInt(6) + 1 ))
                .addDie(new Die(4, rn.nextInt(6) + 1 ))
                .addDie(new Die(5, rn.nextInt(6) + 1 ))
                .build();
    }

    public Dice getDice(){
        return mDice;
    }




}
