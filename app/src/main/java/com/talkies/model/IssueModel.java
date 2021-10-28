package com.talkies.model;

public class IssueModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    String id ;
    String issue;


    public IssueModel(String id, String issue) {
        this.id = id;
        this.issue = issue;
    }

    public IssueModel() {
    }
}
