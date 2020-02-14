package com.hhksa.employeetimeinout.general.presenter;

import android.content.Context;

import com.hhksa.employeetimeinout.general.base.BasePresenter;


public class GeneralPresenter extends BasePresenter {

    private Context context;



    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        this.context = context;

    }


}
