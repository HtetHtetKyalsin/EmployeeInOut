package com.hhksa.employeetimeinout.Model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hhksa.employeetimeinout.Presenter.TimeInOutPresenter;
import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.ErrorData;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;
import com.hhksa.employeetimeinout.general.entity.StaffRequestDataset;
import com.hhksa.employeetimeinout.general.model.TimeInOutBaseModel;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimeInOutModel extends TimeInOutBaseModel {

    public static TimeInOutModel objInstance;

    public TimeInOutModel(Context context) {
        super(context);
    }

    public static TimeInOutModel getObjInstance(Context context) {
        if (objInstance == null) {
            objInstance = new TimeInOutModel(context);
        }
        return objInstance;
    }


    public void loadUserData(final MutableLiveData<StaffRequestDataset> staffRequestDatasetMutableLiveData, MutableLiveData<ErrorData> mErrorLD) {
        theApi.getStaffRequest().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StaffRequestDataset>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StaffRequestDataset result) {
                        if (result != null) {
                            staffRequestDatasetMutableLiveData.setValue(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Result:", "Error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void staffTimeIn(StaffLocation staffLocation, MutableLiveData<ClockInDataset> clockInDatasetMutableLiveData, MutableLiveData<ErrorData> mErrorLD) {

        theApi.staffTimeIn(staffLocation).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClockInDataset>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClockInDataset result) {
                        if (result != null) {
                            clockInDatasetMutableLiveData.setValue(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpException error = (HttpException)e;
                        String errorBody = error.response().errorBody().toString();
                        if(String.valueOf(error.code()).equalsIgnoreCase("400"))
                        mErrorLD.setValue(new ErrorData(TimeInOutPresenter.ALREADYCLOCKIN,errorBody));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void staffTimeOut(StaffLocation staffLocation, MutableLiveData<ClockOutDataSet> clockOutDatasetMutableLiveData, MutableLiveData<ErrorData> mErrorLD) {

        theApi.staffTimeOut(staffLocation).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClockOutDataSet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClockOutDataSet result) {
                        if (result != null) {
                            clockOutDatasetMutableLiveData.setValue(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpException error = (HttpException)e;
                        String errorBody = error.response().errorBody().toString();
                        if(errorBody.equalsIgnoreCase("HTTP 400"))
                            mErrorLD.setValue(new ErrorData(TimeInOutPresenter.ALREADYCLOCKOUT,errorBody));  }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
