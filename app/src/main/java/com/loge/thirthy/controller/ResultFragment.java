/*
 * ResultFragment
 *
 * Thirty Project,
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Sumemr Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 *
 */

package com.loge.thirthy.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.loge.thirthy.R;

/**
 * Created by loge on 2017-06-27.
 */

public class ResultFragment extends Fragment {

    private static final String ARG_RESULT_ARRAY = "result_array";

    Button mPlayAgainButton;
    int[] resultArray;
    int totalPoints;

    public static ResultFragment newInstance(int[] resultArray){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESULT_ARRAY, resultArray);

        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        resultArray = new int[10];
        resultArray = (int[]) getArguments().getSerializable(ARG_RESULT_ARRAY);
        totalPoints = 0;
        for(int points:resultArray){
            totalPoints += points;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_result, container, false);

        TextView mResultLow = (TextView) v.findViewById(R.id.label_result_low);
        TextView mResultFour = (TextView) v.findViewById(R.id.label_result_four);
        TextView mResultFive = (TextView) v.findViewById(R.id.label_result_five);
        TextView mResultSix = (TextView) v.findViewById(R.id.label_result_six);
        TextView mResultSeven = (TextView) v.findViewById(R.id.label_result_seven);
        TextView mResultEight = (TextView) v.findViewById(R.id.label_result_eight);
        TextView mResultNine= (TextView) v.findViewById(R.id.label_result_nine);
        TextView mResultTen = (TextView) v.findViewById(R.id.label_result_ten);
        TextView mResultEleven = (TextView) v.findViewById(R.id.label_result_eleven);
        TextView mResultTvelve = (TextView) v.findViewById(R.id.label_result_tvelve);
        TextView mResultTotal = (TextView) v.findViewById(R.id.label_result_total);

        mResultLow.setText(getString(R.string.low) + getString(R.string.ten_tabs) + String.valueOf(resultArray[0]));
        mResultFour.setText(getString(R.string.four) + getString(R.string.ten_tabs) + String.valueOf(resultArray[1]));
        mResultFive.setText(getString(R.string.five) + getString(R.string.ten_tabs) + String.valueOf(resultArray[2]));
        mResultSix.setText(getString(R.string.six) + getString(R.string.ten_tabs) + String.valueOf(resultArray[3]));
        mResultSeven.setText(getString(R.string.seven) + getString(R.string.ten_tabs) + String.valueOf(resultArray[4]));
        mResultEight.setText(getString(R.string.eight) + getString(R.string.ten_tabs) + String.valueOf(resultArray[5]));
        mResultNine.setText(getString(R.string.nine) + getString(R.string.ten_tabs) + String.valueOf(resultArray[6]));
        mResultTen.setText(getString(R.string.ten) + getString(R.string.ten_tabs) + String.valueOf(resultArray[7]));
        mResultEleven.setText(getString(R.string.eleven) + getString(R.string.ten_tabs) + String.valueOf(resultArray[8]));
        mResultTvelve.setText(getString(R.string.tvelve) + getString(R.string.ten_tabs) + String.valueOf(resultArray[9]));
        mResultTotal.setText(getString(R.string.total_score) + getString(R.string.ten_tabs) + String.valueOf(totalPoints));


        mPlayAgainButton = (Button) v.findViewById(R.id.button_play_again);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                getActivity().finish();
            }
        });

        return v;
    }

}
