package com.hhksa.employeetimeinout.Presenter;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.hhksa.employeetimeinout.Model.TimeInOutModel;
import com.hhksa.employeetimeinout.general.base.BasePresenter;
import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;
import com.hhksa.employeetimeinout.general.entity.StaffRequestDataset;

public class TimeInOutPresenter extends BasePresenter {

    TimeInOutModel timeInOutModel;
    MutableLiveData<StaffRequestDataset> staffRequestDatasetMutableLiveData;
    MutableLiveData<ClockInDataset> clockInDatasetMutableLiveData;
    MutableLiveData<ClockOutDataSet> clockOutDataSetMutableLiveData;
    public static String ALREADYCLOCKIN="alreadyclockin";
    public static String ALREADYCLOCKOUT="alreadyclockout";

    @Override
    public void initPresenter(Context context) {
        super.initPresenter(context);
        staffRequestDatasetMutableLiveData=new MutableLiveData<>();
        clockInDatasetMutableLiveData=new MutableLiveData<>();
        clockOutDataSetMutableLiveData=new MutableLiveData<>();
        timeInOutModel = TimeInOutModel.getObjInstance(context);
    }

    public void getUserData(Context applicationContext) {
        timeInOutModel.loadUserData(staffRequestDatasetMutableLiveData,mErrorLD);
    }

    public MutableLiveData<StaffRequestDataset> getStaffRequestDatasetMutableLiveData() {
        return staffRequestDatasetMutableLiveData;
    }

    public void performTimeIn(StaffLocation staffLocation) {
timeInOutModel.staffTimeIn(staffLocation,clockInDatasetMutableLiveData,mErrorLD);
    }

    public void performTimeOut(StaffLocation staffLocation)
    {
        timeInOutModel.staffTimeOut(staffLocation,clockOutDataSetMutableLiveData,mErrorLD);
    }

    public MutableLiveData<ClockInDataset> getClockInDatasetMutableLiveData() {
        return clockInDatasetMutableLiveData;
    }

    public MutableLiveData<ClockOutDataSet> getClockOutDataSetMutableLiveData() {
        return clockOutDataSetMutableLiveData;
    }
}
