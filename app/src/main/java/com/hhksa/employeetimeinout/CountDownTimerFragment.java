package com.hhksa.employeetimeinout;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhksa.employeetimeinout.Presenter.TimeInOutPresenter;
import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.ErrorData;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;
import com.hhksa.employeetimeinout.general.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountDownTimerFragment extends DialogFragment {


    public static CountDownTimerFragment objInstance;
    boolean isIn;

    static InOutErrorListener InOutListener;
    TimeInOutPresenter timeInOutPresenter;


    public static String MENUNAME = "status";

    @BindView(R.id.progressbar)
    ContentLoadingProgressBar mProgressBar;
    @BindView(R.id.cancel)
    TextView cancelBtn;
    @BindView(R.id.label)
    TextView label;
    CountDownTimer mCountDownTimer;
    int i = 0;
    Dialog dialog;


    public static CountDownTimerFragment getInstance(boolean status, InOutErrorListener inOutErrorListener) {
        if (objInstance == null) {
            objInstance = new CountDownTimerFragment();
        }
        InOutListener = inOutErrorListener;
        Bundle bundle = new Bundle();
        bundle.putBoolean(MENUNAME, status);
        objInstance.setArguments(bundle);
        return objInstance;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawableResource(R.color.ripple_black);
            //dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.GRAY));
        View v = inflater.inflate(R.layout.count_down_fragment, container, false);
        ButterKnife.bind(this, v);
        setCancelable(false);
        StaffLocation staffLocation = new StaffLocation("6.2446691", "106.8779625");
        // Get bundle data
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            isIn = bundle.getBoolean(MENUNAME);
        }
        timeInOutPresenter = ViewModelProviders.of(this).get(TimeInOutPresenter.class);
        timeInOutPresenter.initPresenter(getContext());

        if(isIn)
            label.setText("Clocking Out...");
        else
            label.setText("Clocking In...");

        i = 0;
        mProgressBar.setBackgroundColor(Color.WHITE);
//        mProgressBar.getProgressDrawable().setColorFilter(
//                Color.GRAY, android.graphics.PorterDuff.Mode.SRC_IN);
        mProgressBar.setProgress(i);
        mCountDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + i + millisUntilFinished);
                i++;
                mProgressBar.setProgress((int) i * 100 / (10000 / 1000));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(100);
                if (isIn) {
                    timeInOutPresenter.performTimeOut(staffLocation);
                } else {
                    timeInOutPresenter.performTimeIn(staffLocation);
                }

            }
        };
        mCountDownTimer.start();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                dismiss();

            }
        });

        timeInOutPresenter.getClockInDatasetMutableLiveData().observe(this, new Observer<ClockInDataset>() {
            @Override
            public void onChanged(@Nullable ClockInDataset clockInDataset) {
                dismiss();
                if (clockInDataset != null) {
                    InOutListener.performIn(clockInDataset);
                } else {
                    Log.e("Result:", "Clock in not successful");
                }
            }
        });

        timeInOutPresenter.getClockOutDataSetMutableLiveData().observe(this, new Observer<ClockOutDataSet>() {
            @Override
            public void onChanged(ClockOutDataSet clockOutDataSet) {
                dismiss();
                if (clockOutDataSet != null) {
                    InOutListener.performOut(clockOutDataSet);
                } else {
                    Log.e("Result:", "Clock out not successful");
                }

            }
        });

        timeInOutPresenter.getErrorLD().observe(this, new Observer<ErrorData>() {
            @Override
            public void onChanged(ErrorData errorData) {
                dismiss();
                InOutListener.handleError(errorData);
            }
        });
        return v;
    }


}


