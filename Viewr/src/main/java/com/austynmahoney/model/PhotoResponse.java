package com.austynmahoney.model;

/**
 * Created by Austyn on 10/18/13.
 */
public class PhotoResponse extends FlickrRestResponse {

    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "PhotoResponse{" +
                "photos=" + photos +
                "stats=" + stat +
                '}';
    }
}
