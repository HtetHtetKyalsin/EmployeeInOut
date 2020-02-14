package com.hhksa.employeetimeinout.general.entity;

public class ClockInDataset {
    long id;
    String clock_in_time;
    String clock_out_time;
    String clock_in_latitude;
    String clock_in_longitude;
    String clock_out_latitude;
    String clock_out_longitude;
    Partner partner;
    long timesheet;
    String status;
    String notes;
    StaffRequest staff_request;
    String schedule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClock_in_time() {
        return clock_in_time;
    }

    public void setClock_in_time(String clock_in_time) {
        this.clock_in_time = clock_in_time;
    }

    public String getClock_out_time() {
        return clock_out_time;
    }

    public void setClock_out_time(String clock_out_time) {
        this.clock_out_time = clock_out_time;
    }

    public String getClock_in_latitude() {
        return clock_in_latitude;
    }

    public void setClock_in_latitude(String clock_in_latitude) {
        this.clock_in_latitude = clock_in_latitude;
    }

    public String getClock_in_longitude() {
        return clock_in_longitude;
    }

    public void setClock_in_longitude(String clock_in_longitude) {
        this.clock_in_longitude = clock_in_longitude;
    }

    public String getClock_out_latitude() {
        return clock_out_latitude;
    }

    public void setClock_out_latitude(String clock_out_latitude) {
        this.clock_out_latitude = clock_out_latitude;
    }

    public String getClock_out_longitude() {
        return clock_out_longitude;
    }

    public void setClock_out_longitude(String clock_out_longitude) {
        this.clock_out_longitude = clock_out_longitude;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public long getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(long timesheet) {
        this.timesheet = timesheet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public StaffRequest getStaff_request() {
        return staff_request;
    }

    public void setStaff_request(StaffRequest staff_request) {
        this.staff_request = staff_request;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
