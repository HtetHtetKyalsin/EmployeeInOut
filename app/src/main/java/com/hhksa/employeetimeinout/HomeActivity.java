package com.hhksa.employeetimeinout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhksa.employeetimeinout.Presenter.TimeInOutPresenter;
import com.hhksa.employeetimeinout.general.base.BaseActivity;
import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.ErrorData;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;
import com.hhksa.employeetimeinout.general.entity.StaffRequestDataset;
import com.hhksa.employeetimeinout.general.presenter.GeneralPresenter;
import com.hhksa.employeetimeinout.general.util.SessionManager;
import com.hhksa.employeetimeinout.general.util.UtilHttp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements InOutErrorListener{
    @BindView(R.id.job_title)
    TextView jobTitle;
    @BindView(R.id.salary)
    TextView salary;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.salary_label)
    TextView salaryLabel;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.manager_name)
    TextView managerName;
    @BindView(R.id.phone)
    TextView contactNumber;
    @BindView(R.id.clockin_time)
    TextView clockinTime;
    @BindView(R.id.clockout_time)
    TextView clockoutTime;
    @BindView(R.id.clock_btn)
    TextView clockBtn;
    TimeInOutPresenter timeInOutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(SessionManager.getObjectInstance(this).isClockedIn())
                clockBtn.setText("Clock Out");
        else
            clockBtn.setText("Clock In");

        if(SessionManager.getObjectInstance(getApplicationContext()).getClockInTime()!=null)
            clockinTime.setText(SessionManager.getObjectInstance(getApplicationContext()).getClockInTime());
        if(SessionManager.getObjectInstance(getApplicationContext()).getClockOutTime()!=null)
            clockoutTime.setText(SessionManager.getObjectInstance(getApplicationContext()).getClockOutTime());

        initPresenter();
        loadUserData();
     //   listenObserver();

        StaffLocation staffLocation=new StaffLocation("6.2446691","106.8779625");

        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilHttp.isNetworkAvailable(HomeActivity.this)) {
                    CountDownTimerFragment countDownTimerFragment=CountDownTimerFragment.getInstance(SessionManager.getObjectInstance(getApplicationContext()).isClockedIn(),HomeActivity.this);
                    countDownTimerFragment.show(getSupportFragmentManager(), "timer");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Network connection is not available",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void listenObserver() {
//        timeInOutPresenter.getStaffRequestDatasetMutableLiveData().observe(this, new Observer<StaffRequestDataset>() {
//            @Override
//            public void onChanged(@Nullable StaffRequestDataset staffRequestDataset) {
//                if (staffRequestDataset != null) {
//                    BindData(staffRequestDataset);
//                    Log.e("Result:", "Exist");
//                } else {
//                    Log.e("Result:", "Not Exist");
//                }
//            }
//        });
//
//        timeInOutPresenter.getClockInDatasetMutableLiveData().observe(this, new Observer<ClockInDataset>() {
//            @Override
//            public void onChanged(@Nullable ClockInDataset clockInDataset) {
//                if (clockInDataset != null) {
//                    SessionManager.getObjectInstance(getApplicationContext()).setClockInStatus(true);
//                        clockBtn.setText("Clock Out");
//                    if (clockInDataset.getClock_in_time()!=null)
//                        SessionManager.getObjectInstance(getApplicationContext()).setClockInTine(clockInDataset.getClock_in_time());
//                        clockinTime.setText(clockInDataset.getClock_in_time());
//                } else {
//                    Log.e("Result:", "Clock in not successful");
//                }
//            }
//        });
//
//        timeInOutPresenter.getClockOutDataSetMutableLiveData().observe(this, new Observer<ClockOutDataSet>() {
//            @Override
//            public void onChanged(ClockOutDataSet clockOutDataSet) {
//                if(clockOutDataSet!=null)
//                {
//                        clockBtn.setText("Clock In");
//                    SessionManager.getObjectInstance(getApplicationContext()).setClockInStatus(false);
//                    clockinTime.setText("");
//                    clockoutTime.setText("");
//                    SessionManager.getObjectInstance(getApplicationContext()).clearSession();
//                    if(clockOutDataSet.getTimesheet().getClock_in_time()!=null && clockOutDataSet.getTimesheet().getClock_out_time()!=null)
//                    {
//                        SessionManager.getObjectInstance(getApplicationContext()).setClockInTine(clockOutDataSet.getTimesheet().getClock_in_time());
//                        SessionManager.getObjectInstance(getApplicationContext()).setClockOutTime(clockOutDataSet.getTimesheet().getClock_out_time());
//                        clockinTime.setText(clockOutDataSet.getTimesheet().getClock_in_time());
//                        clockoutTime.setText(clockOutDataSet.getTimesheet().getClock_out_time());
//                    }
//                    else {
//                        Log.e("Result:", "Clock out not successful");
//                    }
//                }
//            }
//        });
//
//        timeInOutPresenter.getErrorLD().observe(this, new Observer<ErrorData>() {
//            @Override
//            public void onChanged(ErrorData errorData) {
//                if(errorData.getCode().equalsIgnoreCase(TimeInOutPresenter.ALREADYCLOCKIN))
//                {
//                    ErrorFragment errorFragment=ErrorFragment.getInstance(true);
//                    errorFragment.show(getSupportFragmentManager(), "alreadyin");
//                }
//                else
//                {
//                    ErrorFragment errorFragment=ErrorFragment.getInstance(false);
//                    errorFragment.show(getSupportFragmentManager(), "alreadyout");
//                }
//            }
//        });
    }

    private void BindData(StaffRequestDataset staffRequestDataset) {
        if (staffRequestDataset.getPosition().getName() != null)
            jobTitle.setText(staffRequestDataset.getPosition().getName());

        if (staffRequestDataset.getWage_amount() != null)
            salary.setText(staffRequestDataset.getWage_amount());

        if (staffRequestDataset.getClient().getName() != null)
            companyName.setText(staffRequestDataset.getClient().getName());

        if (staffRequestDataset.getWage_type() != null)
            salaryLabel.setText(staffRequestDataset.getWage_type());

        if (staffRequestDataset.getLocation().getAddress().getStreet_1() != null)
            address.setText(staffRequestDataset.getLocation().getAddress().getStreet_1());

        if (staffRequestDataset.getManager().getName() != null)
            managerName.setText(staffRequestDataset.getManager().getName());

        if (staffRequestDataset.getManager().getPhone() != null)
            contactNumber.setText(staffRequestDataset.getManager().getPhone());

    }

    private void loadUserData() {
        if (UtilHttp.isNetworkAvailable(HomeActivity.this)) {
            timeInOutPresenter.getUserData(getApplicationContext());
        }
        else
        {

        }
    }

    private void initPresenter() {
        timeInOutPresenter = ViewModelProviders.of(this).get(TimeInOutPresenter.class);
        timeInOutPresenter.initPresenter(getApplicationContext());
    }

    @Override
    public void performIn(ClockInDataset clockInDataset) {
        if (clockInDataset != null) {
            SessionManager.getObjectInstance(getApplicationContext()).setClockInStatus(true);
            clockBtn.setText("Clock Out");
            if (clockInDataset.getClock_in_time()!=null) {
                String inTime = clockInDataset.getClock_in_time();
                String resultInTime = changeTimeFormat(inTime);
                SessionManager.getObjectInstance(getApplicationContext()).setClockInTine(resultInTime);

                clockinTime.setText(resultInTime);
                clockoutTime.setText("-");
            }
        } else {
            Log.e("Result:", "Clock in not successful");
        }
    }

    private String changeTimeFormat(String inTime) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(inTime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Thailand"));
            Log.e("In Time is:",outputFormat.format(date));
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void performOut(ClockOutDataSet clockOutDataSet) {
        if(clockOutDataSet!=null)
        {
            clockBtn.setText("Clock In");
            SessionManager.getObjectInstance(getApplicationContext()).setClockInStatus(false);
            clockinTime.setText("");
            clockoutTime.setText("");
            SessionManager.getObjectInstance(getApplicationContext()).clearSession();
            if(clockOutDataSet.getTimesheet().getClock_in_time()!=null && clockOutDataSet.getTimesheet().getClock_out_time()!=null)
            {
               String resultInTime=changeTimeFormat(clockOutDataSet.getTimesheet().getClock_in_time());
                String resultOutTime=changeTimeFormat(clockOutDataSet.getTimesheet().getClock_out_time());
                SessionManager.getObjectInstance(getApplicationContext()).setClockInTine(resultInTime);
                SessionManager.getObjectInstance(getApplicationContext()).setClockOutTime(resultOutTime);

                clockinTime.setText(resultInTime);
                clockoutTime.setText(resultOutTime);
            }
            else {
                Log.e("Result:", "Clock out not successful");
            }
        }
    }

    @Override
    public void handleError(ErrorData errorData) {
        if(errorData.getCode().equalsIgnoreCase(TimeInOutPresenter.ALREADYCLOCKIN))
        {
            ErrorFragment errorFragment=ErrorFragment.getInstance(true,this);
            errorFragment.show(getSupportFragmentManager(), "alreadyin");
        }
        else
        {
            ErrorFragment errorFragment=ErrorFragment.getInstance(false,this);
            errorFragment.show(getSupportFragmentManager(), "alreadyout");
        }
    }
}
