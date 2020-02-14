package com.hhksa.employeetimeinout.general.entity;

import java.util.ArrayList;

public class StaffRequestDataset {
    long id;
    String created_date;
    String modified_date;
    String status;
    String uid;
    String title;
    String cover_image;
    String description;
    String wage_amount;
    String wage_type;
    long staff_required;
    String timezone;
    String gender;
    int min_age;
    int max_age;
    boolean require_experience;
    boolean require_english;
    String interview_time;
    String interview_info;
    OfferStatistics offer_statistics;
    OfferCount offer_counts;
    ApplicationCount application_counts;
    EmploymentCount employment_counts;
    String start_time;
    String end_time;
    boolean fixed_location;
    ArrayList<ScheduleData> schedules;
    Client client;
    Location location;
    Position position;
    Manager manager;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWage_amount() {
        return wage_amount;
    }

    public void setWage_amount(String wage_amount) {
        this.wage_amount = wage_amount;
    }

    public String getWage_type() {
        return wage_type;
    }

    public void setWage_type(String wage_type) {
        this.wage_type = wage_type;
    }

    public long getStaff_required() {
        return staff_required;
    }

    public void setStaff_required(long staff_required) {
        this.staff_required = staff_required;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public boolean isRequire_experience() {
        return require_experience;
    }

    public void setRequire_experience(boolean require_experience) {
        this.require_experience = require_experience;
    }

    public boolean isRequire_english() {
        return require_english;
    }

    public void setRequire_english(boolean require_english) {
        this.require_english = require_english;
    }

    public String getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(String interview_time) {
        this.interview_time = interview_time;
    }

    public String getInterview_info() {
        return interview_info;
    }

    public void setInterview_info(String interview_info) {
        this.interview_info = interview_info;
    }

    public OfferStatistics getOffer_statistics() {
        return offer_statistics;
    }

    public void setOffer_statistics(OfferStatistics offer_statistics) {
        this.offer_statistics = offer_statistics;
    }

    public OfferCount getOffer_counts() {
        return offer_counts;
    }

    public void setOffer_counts(OfferCount offer_counts) {
        this.offer_counts = offer_counts;
    }

    public ApplicationCount getApplication_counts() {
        return application_counts;
    }

    public void setApplication_counts(ApplicationCount application_counts) {
        this.application_counts = application_counts;
    }

    public EmploymentCount getEmployment_counts() {
        return employment_counts;
    }

    public void setEmployment_counts(EmploymentCount employment_counts) {
        this.employment_counts = employment_counts;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isFixed_location() {
        return fixed_location;
    }

    public void setFixed_location(boolean fixed_location) {
        this.fixed_location = fixed_location;
    }

    public ArrayList<ScheduleData> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<ScheduleData> schedules) {
        this.schedules = schedules;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
