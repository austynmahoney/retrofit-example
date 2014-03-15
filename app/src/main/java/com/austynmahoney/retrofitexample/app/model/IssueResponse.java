package com.austynmahoney.retrofitexample.app.model;

import java.util.List;


public class IssueResponse {
    public String number;
    public String state;
    public String title;
    public String body;
    public User user;
    public String assignee;
    public int milestone;
    public List<String> labels;
}
