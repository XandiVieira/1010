package com.relyon.a1010.model;

import java.util.List;

public class Experiment {

    private String id;
    private String userID;
    private StatusENUM status;
    private CategoryENUM category;
    private int numberOfValuations;
    private String url;
    private List<Criteria> criteria;
    private List<String> commentList;
    private List<String> alreadyRatedBy;
    private List<Report> reportList;
    private Long createdAt;

    public Experiment() {
    }

    public Experiment(String id, String userID, StatusENUM status, CategoryENUM category, String url, List<Criteria> criteria, Long createdAt) {
        this.id = id;
        this.userID = userID;
        this.status = status;
        this.category = category;
        this.numberOfValuations = 0;
        this.url = url;
        this.criteria = criteria;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public StatusENUM getStatus() {
        return status;
    }

    public void setStatus(StatusENUM status) {
        this.status = status;
    }

    public CategoryENUM getCategory() {
        return category;
    }

    public void setCategory(CategoryENUM category) {
        this.category = category;
    }

    public int getNumberOfValuations() {
        return numberOfValuations;
    }

    public void setNumberOfValuations(int numberOfValuations) {
        this.numberOfValuations = numberOfValuations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public List<String> getAlreadyRatedBy() {
        return alreadyRatedBy;
    }

    public void setAlreadyRatedBy(List<String> alreadyRatedBy) {
        this.alreadyRatedBy = alreadyRatedBy;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}