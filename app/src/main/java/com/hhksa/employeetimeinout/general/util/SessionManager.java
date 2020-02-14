package com.hhksa.employeetimeinout.general.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    public static SessionManager objectInstance;
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private String PREF_NAME = "FarmerPref";
    // All Shared Preferences Keys
    private String IS_CLOCKIN = "IsClockIn";
    private String CLOCKIN_TIME="clockInTime";
    private String CLOCKOUT_TIME="clockoutTime";


    public SessionManager() {
    }

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
    }

    public void initSession(){
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static SessionManager getObjectInstance(Context context) {
        if (objectInstance == null) {
            objectInstance = new SessionManager(context);
            objectInstance.initSession();
        }
        return objectInstance;
    }


    public boolean checkClockInStatus() {
        boolean status = true;
        if (!this.isClockedIn()) {
            status = false;
        }
        return status;
    }

public void setClockInStatus(boolean isClockIn)
{
    editor.putBoolean(IS_CLOCKIN, isClockIn);
    editor.commit();
}

public void setClockInTine(String inTime)
{
    editor.putString(CLOCKIN_TIME,inTime);
    editor.commit();
}
public void setClockOutTime(String outTime)
{
    editor.putString(CLOCKOUT_TIME,outTime);
    editor.commit();
}

    public void clearSession() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    public boolean isClockedIn() {
        return pref.getBoolean(IS_CLOCKIN, false);
    }

    public String getClockInTime()
    {
        return pref.getString(CLOCKIN_TIME,"");
    }

    public String getClockOutTime()
    {
        return pref.getString(CLOCKOUT_TIME,"");
    }
}
