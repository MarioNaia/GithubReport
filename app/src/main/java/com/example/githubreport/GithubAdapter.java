package com.example.githubreport;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;


/**
 * An {@link GithubAdapter} knows how to create a list item layout for each github
 * in the data source (a list of {@link Github} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class GithubAdapter extends ArrayAdapter<Github> {



    /**
     * Constructs a new {@link GithubAdapter}.
     *
     * @param context of the app
     * @param githubs is the list of github repos, which is the data source of the adapter
     */
    public GithubAdapter(Context context, List<Github> githubs) {
        super(context, 0, githubs);
    }


    /**
     * Returns a list item view that displays information about the github repo at the given position
     * in the list of githubs.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.github_list_item, parent, false);
        }

        // Find the github at the given position in the list of githubs
        Github currentGithub = getItem(position);




        //Setting the different views

        TextView numberOfStarsView = (TextView) listItemView.findViewById(R.id.numberOfStars);
        numberOfStarsView.setText(String.valueOf(currentGithub.getStarNumber()));


        TextView repoDescriptionView = (TextView) listItemView.findViewById(R.id.repoDescription);
        repoDescriptionView.setText(currentGithub.getDescription());


        TextView repoNameView = (TextView) listItemView.findViewById(R.id.repoName);
        repoNameView.setText(currentGithub.getTitle());


        TextView repoOwnerNameView = (TextView) listItemView.findViewById(R.id.repoOwnerName);
        repoOwnerNameView.setText(currentGithub.getNameOwner());


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }



}
