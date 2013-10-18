package com.austynmahoney.flickr;

import com.austynmahoney.model.PhotoResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface FlickrService {


    public static final String FLICKR_API_KEY = "ADD_API_KEY_HERE";
    public static final String FLICKR_API_URL = "http://api.flickr.com/services/rest";

    public static final String NO_JSON_CALLBACK = "&nojsoncallback=1";

    @GET("/?method=flickr.photos.search&extras=original_format" + NO_JSON_CALLBACK)
    void searchPhotos(@Query("text") String searchText, @Query("per_page") String perPage,
                      @Query("page") String page, Callback<PhotoResponse> cb);
}
