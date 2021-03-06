package com.example.githubreport;


/**
 * An {@link Github} object contains information related to a single github.
 */

public class Github {
    /**
     * Title of repo
     */
    public final String mTitle;

    /**
     * Description of repo
     */
    //(title, description, nameOwner,starNumber);
    public final String mDescription;

    /**
     * Name of owner of github repository
     */
    public final String mNameOwner;

    /**
     * Star number
     */
    public final int mStarNumber;

    /**
     * Website URL of the github
     */
    private String mUrl;


    public Github(String title, String description, String nameOwner, int starNumber, String url) {
        mTitle = title;
        mDescription = description;
        mNameOwner = nameOwner;
        mStarNumber = starNumber;
        mUrl = url;
    }

    /**
     * Returns the magnitude of the github.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the location of the github.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Returns the time of the github.
     */
    public String getNameOwner() {
        return mNameOwner;
    }

    /**
     * Returns the time of the github.
     */
    public int getStarNumber() {
        return mStarNumber;
    }

    /**
     * Returns the website URL to find more information about the github.
     */
    public String getUrl() {
        return mUrl;
    }
}
