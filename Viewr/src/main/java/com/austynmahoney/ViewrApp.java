package com.austynmahoney;

import android.app.Application;

import com.austynmahoney.flickr.FlickrService;
import com.austynmahoney.util.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


public class ViewrApp extends Application {

    private FlickrService mFlickrService;

    public synchronized FlickrService getFlickrService() {

        if (mFlickrService == null) {
            // Create a REST adapter which points to the Flickr API.
            RestAdapter restAdapter = new RestAdapter.Builder().setServer(FlickrService.FLICKR_API_URL)
                    //.setConverter(new GsonConverter(GsonUtil.getGsonInstance()))
                    .setClient(new OkClient())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {

                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            // Get responses in JSON
                            requestFacade.addQueryParam("format", "json");
                            requestFacade.addQueryParam("api_key", FlickrService.FLICKR_API_KEY);

                            requestFacade.addHeader(Constants.HTTP_HEADER_ACCEPT, Constants.ACCEPT_APPLICATION_JSON);
                        }
                    })
                    .build();

            mFlickrService = restAdapter.create(FlickrService.class);
        }
        return mFlickrService;
    }
}
