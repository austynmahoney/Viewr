package com.austynmahoney.model;

/**
 * Base class for all Flickr JSON pojos
 */
public class FlickrRestResponse {

    protected String stat;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
