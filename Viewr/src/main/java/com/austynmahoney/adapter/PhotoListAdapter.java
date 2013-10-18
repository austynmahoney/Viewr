package com.austynmahoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.austynmahoney.model.Photo;
import com.austynmahoney.util.Constants;
import com.austynmahoney.viewr.FullScreenImageActivity;
import com.austynmahoney.viewr.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Photo> mPhotos;

    private LayoutInflater mLayoutInflater;

    public PhotoListAdapter(Context context, List<Photo> photos) {
        mContext = context;
        setPhotos(photos);
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public Photo getItem(int position) {
        return mPhotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(getItem(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Photo item = getItem(position);
        if (convertView == null) {
            if (mLayoutInflater == null) mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.photo_grid_item, parent, false);
            convertView.setTag(new PhotoViewHolder(convertView));
        }

        PhotoViewHolder holder = (PhotoViewHolder) convertView.getTag();

        // Load the data into the views from the PhotoViewHolder
        Picasso.with(mContext).load(item.getFlickrPhotoUrl().getThumbnailUrl()).into(holder.photo);
        holder.title.setText(item.getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FullScreenImageActivity.class);
                i.putExtra(Constants.KEY_IMAGE_URL, item.getFlickrPhotoUrl().getOriginalUrl());
                i.putExtra(Constants.KEY_IMAGE_TITLE, item.getTitle());
                mContext.startActivity(i);
            }
        });

        return convertView;
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == null) {
            this.mPhotos = new ArrayList<Photo>(0);
        } else {
            this.mPhotos = photos;
        }

        notifyDataSetChanged();
    }


    private class PhotoViewHolder {

        private final ImageView photo;
        private final TextView title;

        public PhotoViewHolder(View view) {
            photo = (ImageView) view.findViewById(R.id.photo_grid_item_image);
            title = (TextView) view.findViewById(R.id.photo_grid_item_title);
        }

    }
}