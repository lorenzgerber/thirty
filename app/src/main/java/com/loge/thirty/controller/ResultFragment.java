/*
 * ResultFragment
 *
 * Thirty Project, an Android implementation
 * of the Dice game 'thirty'.
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Summer Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 */
package com.loge.thirty.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.loge.thirty.R;

/**
 * ResultFragment
 *
 * This is the Fragment based controller class for
 * presenting the results to the user.
 */
public class ResultFragment extends Fragment {

    private static final String ARG_RESULT_ARRAY = "result_array";

    private int[] resultArray;
    private int totalPoints;

    /**
     * ResultFragment
     *
     * Constructor to construct ResultFragment
     * @param resultArray
     * @return
     */
    public static ResultFragment newInstance(int[] resultArray){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESULT_ARRAY, resultArray);

        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * onCreate
     *
     * The transient data from the bundle
     * is retrieved and written into local
     * variables. Further total points are
     * calculated and stored.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        resultArray = new int[10];
        resultArray = (int[]) getArguments().getSerializable(ARG_RESULT_ARRAY);
        totalPoints = 0;
        assert resultArray != null;
        for(int points:resultArray){
            totalPoints += points;
        }

    }

    /**
     * onCreateView
     *
     * Here the result view is prepared for rendering.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_result, container, false);

        TextView mLabelLow = (TextView) v.findViewById(R.id.label_low);
        TextView mLabelFour = (TextView) v.findViewById(R.id.label_four);
        TextView mLabelFive = (TextView) v.findViewById(R.id.label_five);
        TextView mLabelSix = (TextView) v.findViewById(R.id.label_six);
        TextView mLabelSeven = (TextView) v.findViewById(R.id.label_seven);
        TextView mLabelEight = (TextView) v.findViewById(R.id.label_eight);
        TextView mLabelNine= (TextView) v.findViewById(R.id.label_nine);
        TextView mLabelTen = (TextView) v.findViewById(R.id.label_ten);
        TextView mLabelEleven = (TextView) v.findViewById(R.id.label_eleven);
        TextView mLabelTwelve = (TextView) v.findViewById(R.id.label_twelve);
        TextView mLabelTotal = (TextView) v.findViewById(R.id.label_total);
        
        
        TextView mResultLow = (TextView) v.findViewById(R.id.result_low);
        TextView mResultFour = (TextView) v.findViewById(R.id.result_four);
        TextView mResultFive = (TextView) v.findViewById(R.id.result_five);
        TextView mResultSix = (TextView) v.findViewById(R.id.result_six);
        TextView mResultSeven = (TextView) v.findViewById(R.id.result_seven);
        TextView mResultEight = (TextView) v.findViewById(R.id.result_eight);
        TextView mResultNine= (TextView) v.findViewById(R.id.result_nine);
        TextView mResultTen = (TextView) v.findViewById(R.id.result_ten);
        TextView mResultEleven = (TextView) v.findViewById(R.id.result_eleven);
        TextView mResultTwelve = (TextView) v.findViewById(R.id.result_twelve);
        TextView mResultTotal = (TextView) v.findViewById(R.id.result_total);

        mLabelLow.setText(getString(R.string.low));
        mLabelFour.setText(getString(R.string.four));
        mLabelFive.setText(getString(R.string.five));
        mLabelSix.setText(getString(R.string.six));
        mLabelSeven.setText(getString(R.string.seven));
        mLabelEight.setText(getString(R.string.eight));
        mLabelNine.setText(getString(R.string.nine));
        mLabelTen.setText(getString(R.string.ten));
        mLabelEleven.setText(getString(R.string.eleven));
        mLabelTwelve.setText(getString(R.string.twelve));
        mLabelTotal.setText(getString(R.string.total_score));

        mResultLow.setText(String.valueOf(resultArray[0]));
        mResultFour.setText(String.valueOf(resultArray[1]));
        mResultFive.setText(String.valueOf(resultArray[2]));
        mResultSix.setText(String.valueOf(resultArray[3]));
        mResultSeven.setText(String.valueOf(resultArray[4]));
        mResultEight.setText(String.valueOf(resultArray[5]));
        mResultNine.setText(String.valueOf(resultArray[6]));
        mResultTen.setText(String.valueOf(resultArray[7]));
        mResultEleven.setText(String.valueOf(resultArray[8]));
        mResultTwelve.setText(String.valueOf(resultArray[9]));
        mResultTotal.setText(String.valueOf(totalPoints));


        Button playAgainButton = (Button) v.findViewById(R.id.button_play_again);
        playAgainButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                getActivity().finish();
            }
        });

        return v;
    }

}
