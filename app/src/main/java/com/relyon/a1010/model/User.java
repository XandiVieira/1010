package com.relyon.a1010.model;

import java.util.List;

public class User {

    private String id;
    private String username;
    private String photoUrl;
    private List<Experiment> experimentList;
    private int power;

    public User() {
    }

    public User(String id, String username, String photoUrl, List<Experiment> experimentList, int power) {
        this.id = id;
        this.username = username;
        this.photoUrl = photoUrl;
        this.experimentList = experimentList;
        this.power = power;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}