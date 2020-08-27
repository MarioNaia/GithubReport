package com.example.githubreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Github>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * URL for Github data
     */
    private static final String GITHUB_REQUEST_URL = "https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc";

    /**
     * Constant value for the github loader ID. We can choose any integer.
     */
    private static final int GITHUB_LOADER_ID = 1;

    /**
     * Adapter for the list of githubs
     */
    private GithubAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find a reference to the {@link ListView} in the layout
        ListView githubListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        githubListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of githubs as input
        mAdapter = new GithubAdapter(this, new ArrayList<Github>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        githubListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open github url.
        githubListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current github that was clicked on
                Github currentGithub = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri githubUri = Uri.parse(currentGithub.getUrl());

                // Create a new intent to view the github URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, githubUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Loader inicialization.  Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(GITHUB_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<Github>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new GithubLoader(this, GITHUB_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Github>> loader, List<Github> githubs) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No githubs found."
        mEmptyStateTextView.setText(R.string.no_githubs);


        // If there is a valid list of {@link Github}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (githubs != null && !githubs.isEmpty()) {
            mAdapter.addAll(githubs);
        }
    }


    @Override
    public void onLoaderReset(Loader<List<Github>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}

