package com.loge.thirthy.view;

import android.view.View;
import android.widget.ImageButton;

import com.loge.thirthy.R;
import com.loge.thirthy.model.Dice;

import java.util.ArrayList;

/**
 * Created by lgerber on 7/1/17.
 */

public class DiceImageButtons {

    int[][] mImageIds;
    Dice mDice;
    ArrayList<ImageButton> mImageButtons = new ArrayList<>();

    public DiceImageButtons(View v){

        mImageIds = new int[3][6];
        initImageIds();

        mImageButtons.add((ImageButton) v.findViewById(R.id.die_one));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_two));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_three));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_four));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_five));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_six));
    }

    public void updateImages(Dice dice){
        for (int i = 0; i < dice.size(); i++){
            mImageButtons.get(i).setImageResource(mImageIds[dice.
                    getDie(i).getMode()][dice.getDie(i).getValue()-1]);
        }

    }

    public void attachListeners(Dice dice){

        mDice = dice;

        mImageButtons.get(0).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(0).getMode() > 0 ){
                    mDice.getDie(0).setMode(0);
                } else {
                    mDice.getDie(0).setMode(2);
                }
                updateImages(mDice);
            }
        });

        mImageButtons.get(1).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(1).getMode() > 0 ){
                    mDice.getDie(1).setMode(0);
                } else {
                    mDice.getDie(1).setMode(2);
                }
                updateImages(mDice);
            }
        });
        mImageButtons.get(2).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(2).getMode() > 0 ){
                    mDice.getDie(2).setMode(0);
                } else {
                    mDice.getDie(2).setMode(2);
                }
                updateImages(mDice);
            }
        });
        mImageButtons.get(3).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(3).getMode() > 0 ){
                    mDice.getDie(3).setMode(0);
                } else {
                    mDice.getDie(3).setMode(2);
                }
                updateImages(mDice);
            }
        });
        mImageButtons.get(4).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(4).getMode() > 0 ){
                    mDice.getDie(4).setMode(0);
                } else {
                    mDice.getDie(4).setMode(2);
                }
                updateImages(mDice);
            }
        });
        mImageButtons.get(5).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(5).getMode() > 0 ){
                    mDice.getDie(5).setMode(0);
                } else {
                    mDice.getDie(5).setMode(2);
                }
                updateImages(mDice);
            }
        });
    }

    private void initImageIds(){
        mImageIds[0][0] = R.drawable.white1;
        mImageIds[0][1] = R.drawable.white2;
        mImageIds[0][2] = R.drawable.white3;
        mImageIds[0][3] = R.drawable.white4;
        mImageIds[0][4] = R.drawable.white5;
        mImageIds[0][5] = R.drawable.white6;
        mImageIds[1][0] = R.drawable.grey1;
        mImageIds[1][1] = R.drawable.grey2;
        mImageIds[1][2] = R.drawable.grey3;
        mImageIds[1][3] = R.drawable.grey4;
        mImageIds[1][4] = R.drawable.grey5;
        mImageIds[1][5] = R.drawable.grey6;
        mImageIds[2][0] = R.drawable.red1;
        mImageIds[2][1] = R.drawable.red2;
        mImageIds[2][2] = R.drawable.red3;
        mImageIds[2][3] = R.drawable.red4;
        mImageIds[2][4] = R.drawable.red5;
        mImageIds[2][5] = R.drawable.red6;
    }

}