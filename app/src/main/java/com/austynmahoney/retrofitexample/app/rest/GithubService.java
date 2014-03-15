package com.austynmahoney.retrofitexample.app.rest;


import com.austynmahoney.retrofitexample.app.model.Issue;
import com.austynmahoney.retrofitexample.app.model.IssueResponse;
import com.austynmahoney.retrofitexample.app.model.Repository;
import com.austynmahoney.retrofitexample.app.model.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Used by Retrofit to generate your REST client class.
 */
public interface GithubService {

    public static final String API_URL = "https://api.github.com";

    /**
     * Find repositories via various criteria. This method returns up to 100 results per page.
     */
    @GET("/search/repositories")
    public List<SearchResponse> searchRepositories(@Query("q") String query);

    /**
     * List public repositories for the specified user.
     */
    @GET("/users/{user}/repos")
    public void listUserRepositories(@Path("user") String user, @QueryMap Map<String, String> options, Callback<List<Repository>> cb);

    @POST("/repos/{owner}/{repo}/issues")
    public IssueResponse createIssue(@Body Issue issue);

}
