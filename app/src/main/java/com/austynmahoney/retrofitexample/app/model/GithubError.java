package com.austynmahoney.retrofitexample.app.model;


import java.util.List;

public class GithubError {

    public String message;
    public List<GithubErrorItem> errors;

    private class GithubErrorItem {
        public String resouce;
        public String field;
        public String code;
    }
}
