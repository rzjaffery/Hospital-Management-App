package com.rzjaffery.hms.data.model;

public class Doctor {
    private String id;
    private String name;
    private String specialization;
    private String timings;
    private String email;

    public Doctor() {
    }

    public Doctor(String id, String name, String specialization, String timings, String email) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.timings = timings;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getEmail() {
        return email;
    }
}
