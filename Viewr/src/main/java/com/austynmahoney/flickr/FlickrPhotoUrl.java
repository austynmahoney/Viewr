package com.austynmahoney.flickr;

import com.austynmahoney.model.Photo;

/**
 * Creates a photo URL from Flickr API response values.
 * <p/>
 * You can construct the source URL to a photo once you know its ID, server ID, farm ID and secret,
 * as returned by many API methods.
 * Example: http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_{sizeidentifier}.jpg
 */
public class FlickrPhotoUrl {

    private static final String FORMAT_FLICKR_PHOTO_URL = "http://farm%s.staticflickr.com/%s/%s_%s_%s.jpg";
    private static final String FORMAT_FLICKR_ORIGINAL_PHOTO_URL = "http://farm%s.staticflickr.com/%s/%s_%s_o.%s";
    /**
     * These could probably be in an enum that associates the letter with the value. You could then have one function
     * that takes an enum and then creates the correct URL.
     */
    private static final String SIZE_THUMBNAIL = "t";
    private static final String SIZE_LARGE = "b";


    private long farmId;
    private String serverId;
    private String id;
    private String secret;
    private String originalFormat;
    private String originalSecret;

    public FlickrPhotoUrl(Photo photo) {
        this.farmId = photo.getFarm();
        this.serverId = photo.getServer();
        this.id = photo.getId();
        this.secret = photo.getSecret();
        this.originalSecret = photo.getOriginalSecret();
        this.originalFormat = photo.getOriginalFormat();
    }

    public String getThumbnailUrl() {
        return String.format(FORMAT_FLICKR_PHOTO_URL, String.valueOf(farmId), serverId, id, secret, SIZE_THUMBNAIL);
    }

    public String getLargeUrl() {
        return String.format(FORMAT_FLICKR_PHOTO_URL, String.valueOf(farmId), serverId, id, secret, SIZE_LARGE);
    }

    public String getOriginalUrl() {
        return String.format(FORMAT_FLICKR_ORIGINAL_PHOTO_URL, String.valueOf(farmId), serverId, id, originalSecret,
                originalFormat);
    }
}
