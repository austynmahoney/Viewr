package com.austynmahoney.viewr;

import android.app.Activity;
import android.os.Bundle;

import com.austynmahoney.ViewrApp;
import com.austynmahoney.flickr.FlickrService;

/**
 * Base class for any activity using the FlickrService API
 */
public class FlickrRestActivity extends Activity {

    protected FlickrService mFlickrService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFlickrService = ((ViewrApp) getApplication()).getFlickrService();
    }
}
