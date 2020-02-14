package com.hhksa.employeetimeinout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhksa.employeetimeinout.Presenter.TimeInOutPresenter;
import com.hhksa.employeetimeinout.general.entity.ClockInDataset;
import com.hhksa.employeetimeinout.general.entity.ClockOutDataSet;
import com.hhksa.employeetimeinout.general.entity.ErrorData;
import com.hhksa.employeetimeinout.general.entity.StaffLocation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorFragment extends DialogFragment {

    @BindView(R.id.error_description)
    TextView errorDesc;
    @BindView(R.id.txt_clock)
    TextView clock;
    @BindView(R.id.txt_cancel)
    TextView cancel;
    public static ErrorFragment objInstance;
    boolean isIn;

    static InOutErrorListener inOutListener;
    TimeInOutPresenter timeInOutPresenter;


    public static String MENUNAME = "status";

    public static ErrorFragment getInstance(boolean status,InOutErrorListener inOutErrorListener) {
        if (objInstance == null) {
            objInstance = new ErrorFragment();
        }
        inOutListener=inOutErrorListener;
        Bundle bundle = new Bundle();
        bundle.putBoolean(MENUNAME, status);
        objInstance.setArguments(bundle);
        return objInstance;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawableResource(R.color.ripple_black);
            //dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.GRAY));
        View v = inflater.inflate(R.layout.error_fragment, container, false);
        ButterKnife.bind(this, v);
        setCancelable(false);
        StaffLocation staffLocation=new StaffLocation("6.2446691","106.8779625");
        // Get bundle data
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            isIn = bundle.getBoolean(MENUNAME);
        }
        timeInOutPresenter = ViewModelProviders.of(this).get(TimeInOutPresenter.class);
        timeInOutPresenter.initPresenter(getContext());

        if(isIn)
        {
            errorDesc.setText("Partner already clocked in");
            clock.setText("Clock Out");
        }
        else
        {
            errorDesc.setText("Partner already clocked out");
            clock.setText("Clock In");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIn)
                {
                    timeInOutPresenter.performTimeOut(staffLocation);
                }
                else {
                    timeInOutPresenter.performTimeIn(staffLocation);
                }
                dismiss();
            }
        });

        timeInOutPresenter.getClockInDatasetMutableLiveData().observe(this, new Observer<ClockInDataset>() {
            @Override
            public void onChanged(@Nullable ClockInDataset clockInDataset) {
                dismiss();
                if (clockInDataset != null) {
                    inOutListener.performIn(clockInDataset);
                } else {
                    Log.e("Result:", "Clock in not successful");
                }
            }
        });

        timeInOutPresenter.getClockOutDataSetMutableLiveData().observe(this, new Observer<ClockOutDataSet>() {
            @Override
            public void onChanged(ClockOutDataSet clockOutDataSet) {
                dismiss();
                if(clockOutDataSet!=null)
                {
                    inOutListener.performOut(clockOutDataSet);
                }
                else {
                    Log.e("Result:", "Clock out not successful");
                }

            }
        });

        timeInOutPresenter.getErrorLD().observe(this, new Observer<ErrorData>() {
            @Override
            public void onChanged(ErrorData errorData) {
                dismiss();
                inOutListener.handleError(errorData);
            }
        });
        return v;
    }


}


