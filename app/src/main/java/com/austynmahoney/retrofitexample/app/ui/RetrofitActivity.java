package com.austynmahoney.retrofitexample.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.austynmahoney.retrofitexample.app.R;
import com.austynmahoney.retrofitexample.app.model.GithubError;
import com.austynmahoney.retrofitexample.app.model.Repository;
import com.austynmahoney.retrofitexample.app.rest.GithubService;
import com.austynmahoney.retrofitexample.app.rest.ServiceProvider;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RetrofitActivity extends Activity {

    public static final String TAG = RetrofitActivity.class.getSimpleName();

    @InjectView(android.R.id.list) ListView repoListView;
    @InjectView(R.id.repo_progress_bar) ProgressBar progressBar;

    private GithubService mGithubService;

    private RepoAdapter mRepoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.inject(this);

        // Load up our service
        mGithubService = ServiceProvider.INSTANCE.getGithubService();
        mRepoAdapter = new RepoAdapter(null);
        repoListView.setAdapter(mRepoAdapter);
    }


    @OnClick(R.id.get_repos)
    void onGetReposClicked(final Button repoButton) {
        repoButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        repoListView.setVisibility(View.GONE);

        // Call our method we declared in our interface. Retrofit has generated all the code necessary to get our
        // tweets by processing our interface.
        mGithubService.listUserRepositories("austynmahoney", null, new Callback<List<Repository>>() {
            @Override
            public void success(List<Repository> repos, Response response) {
                // This callback will occur on the main thread
                // Our request has gone through successfully
                Log.d(TAG, repos.toString());
                fillRepoAdapter(repos);

                repoButton.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                repoListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                if (!error.isNetworkError()) {
                    if (error.getResponse().getBody() != null) {
                        // Server returned an error
                        GithubError githubError = (GithubError) error.getBodyAs(GithubError.class);
                        Toast.makeText(RetrofitActivity.this, githubError.message, Toast.LENGTH_SHORT).show();
                    } else {
                        // Conversion error
                        Toast.makeText(RetrofitActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Network error
                    Toast.makeText(RetrofitActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }

                repoButton.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                repoListView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void fillRepoAdapter(List<Repository> repos) {
        mRepoAdapter.setRepos(repos);
    }

}
