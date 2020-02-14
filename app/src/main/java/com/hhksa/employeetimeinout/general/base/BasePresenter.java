package com.hhksa.employeetimeinout.general.base;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hhksa.employeetimeinout.general.entity.ErrorData;

public class BasePresenter extends ViewModel {

    protected MutableLiveData<ErrorData> mErrorLD;

    public void initPresenter(Context context) {
        mErrorLD = new MutableLiveData<>();
    }

    public LiveData<ErrorData> getErrorLD() {
        return mErrorLD;
    }

}
