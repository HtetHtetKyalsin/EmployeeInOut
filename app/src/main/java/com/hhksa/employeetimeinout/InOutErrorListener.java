package com.hhksa.employeetimeinout;

import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.ErrorData;

public interface InOutErrorListener {

    public void performIn(ClockInDataset clockInDataset);
    public void performOut(ClockOutDataSet clockOutDataSet);
    public void handleError(ErrorData errorData);
}
