package com.example.githubreport;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of github repos by using an AsyncTask to perform the
 * network request to the given URL.
 */


public class GithubLoader extends AsyncTaskLoader<List<Github>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = GithubLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link GithubLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public GithubLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Github> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of githubs.
        List<Github> githubs = QueryUtils.fetchGithubData(mUrl);
        return githubs;
    }
}
