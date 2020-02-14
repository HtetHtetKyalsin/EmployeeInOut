package com.hhksa.employeetimeinout.general.api;

import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;
import com.hhksa.employeetimeinout.general.entity.StaffRequestDataset;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TimeInOutService {

    @GET("26074/")
    Observable<StaffRequestDataset> getStaffRequest();

    @POST("26074/clock-in/")
    Observable<ClockInDataset> staffTimeIn(@Body StaffLocation staffLocation);

    @POST("26074/clock-out/")
    Observable<ClockOutDataSet> staffTimeOut(@Body StaffLocation staffLocation);
}
