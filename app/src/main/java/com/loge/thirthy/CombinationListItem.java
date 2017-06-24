package com.loge.thirthy;

/**
 * Created by loge on 2017-06-23.
 */

public class CombinationListItem {

    private int mId;
    private String mText;

    public CombinationListItem(int id, String text){
        mId = id;
        mText = text;
    }

    public int getId(){ return mId; }

    @Override
    public String toString(){
        return mText;
    }
}
