package com.austynmahoney.retrofitexample.app.rest;

import com.austynmahoney.retrofitexample.app.util.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public enum ServiceProvider {
    INSTANCE;

    private GithubService mGithubService;

    public GithubService getGithubService() {
        // Lazy load a Github singleton
        if (mGithubService == null) {
            //  Initialize GithubService

            // Custom Gson converter
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(GithubService.API_URL)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {

                        @Override
                        public void intercept(RequestFacade request) {
                            // Add these headers to each call
                            request.addHeader(Constants.HTTP_HEADER_ACCEPT, Constants.HTTP_HEADER_ACCEPT_GITHUB_V3);
                            request.addHeader(Constants.HTTP_HEADER_AUTHORIZATION, Constants.HTTP_HEADER_AUTHORIZATION_IDENTIFIER +
                                    Constants.GITHUB_ACCESS_TOKEN);
                        }

                    })
                    .build();

            mGithubService = restAdapter.create(GithubService.class);
        }

        return mGithubService;
    }
}
