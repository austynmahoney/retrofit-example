package com.austynmahoney.retrofitexample.app.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.austynmahoney.retrofitexample.app.R;
import com.austynmahoney.retrofitexample.app.model.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RepoAdapter extends BaseAdapter {

    private List<Repository> mRepos = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    // Standard constructor
    public RepoAdapter(List<Repository> repos) {
        if (repos != null) mRepos = repos;
        else mRepos = new ArrayList<>(0);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Repository item = getItem(position);
        if (convertView == null) {
            // Lazy load layout inflater
            if (mLayoutInflater == null) mLayoutInflater = LayoutInflater.from(parent.getContext());

            convertView = mLayoutInflater.inflate(R.layout.repo_item, parent, false);
            RepoViewHolder holder = new RepoViewHolder(convertView);
            convertView.setTag(holder);
        }

        RepoViewHolder holder = (RepoViewHolder) convertView.getTag();

        holder.name.setText(item.name);
        holder.id.setText("Id: " + item.id);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return mRepos.size();
    }

    @Override
    public Repository getItem(int position) {
        return mRepos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Sets a new comments list and calls {@link #notifyDataSetChanged()}. If a null is passed in, the list is cleared
     */
    public void setRepos(List<Repository> repos) {
        if (repos != null) {
            mRepos = repos;
        } else mRepos.clear(); // null passed in, clear out all comments

        notifyDataSetChanged();
    }

    final class RepoViewHolder {
        @InjectView(R.id.repo_name)
        public TextView name;
        @InjectView(R.id.repo_id)
        public TextView id;

        public RepoViewHolder(View v) {
            ButterKnife.inject(this, v);
        }

    }

}