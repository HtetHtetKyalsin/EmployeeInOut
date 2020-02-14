package com.hhksa.employeetimeinout.general.entity;

public class ClockOutDataSet {
    ClockInDataset timesheet;
    boolean require_feedback;

    public ClockInDataset getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(ClockInDataset timesheet) {
        this.timesheet = timesheet;
    }

    public boolean isRequire_feedback() {
        return require_feedback;
    }

    public void setRequire_feedback(boolean require_feedback) {
        this.require_feedback = require_feedback;
    }
}
