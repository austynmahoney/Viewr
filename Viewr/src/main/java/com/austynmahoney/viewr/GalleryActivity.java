package com.austynmahoney.viewr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.austynmahoney.adapter.PhotoListAdapter;
import com.austynmahoney.model.Photo;
import com.austynmahoney.model.PhotoResponse;

import java.util.List;

import butterknife.InjectView;
import butterknife.Views;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GalleryActivity extends FlickrRestActivity {
    private static final String TAG = "GalleryActivity";
    private static final String PHOTOS_PER_PAGE = "25";

    @InjectView(R.id.btnSearch)
    public Button btnSearch;
    @InjectView(R.id.txtSearch)
    public EditText txtSearch;
    @InjectView(R.id.photoGridView)
    public GridView gvPhoto;


    private PhotoListAdapter mPhotoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Views.inject(this);

        mPhotoListAdapter = new PhotoListAdapter(this, null);
        gvPhoto.setAdapter(mPhotoListAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerms = txtSearch.getText().toString();

                // Empty search term
                if (searchTerms.length() == 0) {
                    Toast.makeText(GalleryActivity.this, R.string.gallery_empty_search_terms, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                // API Call to search photos with user provided search term
                mFlickrService.searchPhotos(searchTerms, PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
                    @Override
                    public void success(PhotoResponse photoResponse, Response response) {
                        Log.d(TAG, photoResponse.toString());
                        displayPhotos(photoResponse.getPhotos().getList());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        if (retrofitError.isNetworkError()) {
                            Toast.makeText(GalleryActivity.this, R.string.error_network, Toast.LENGTH_SHORT).show();
                        }

                        Log.e(TAG, "Error retrieving photos", retrofitError);
                    }
                });

            }
        });
    }

    private void displayPhotos(List<Photo> photos) {
        mPhotoListAdapter.setPhotos(photos);
    }

}
