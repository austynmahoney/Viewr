package com.austynmahoney.viewr;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.austynmahoney.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import butterknife.Views;

public class FullScreenImageActivity extends FlickrRestActivity {

    @InjectView(R.id.fullscreen_image)
    public ImageView ivPhoto;
    @InjectView(R.id.fullscreen_title)
    public TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Views.inject(this);

        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);

        String imageUrl = getIntent().getStringExtra(Constants.KEY_IMAGE_URL);
        String title = getIntent().getStringExtra(Constants.KEY_IMAGE_TITLE);

        if (imageUrl == null) {
            Toast.makeText(this, "Original URL is empty", Toast.LENGTH_SHORT).show();
            finish();
        }

        Picasso.with(this).load(imageUrl).into(ivPhoto);
        txtTitle.setText(title);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

