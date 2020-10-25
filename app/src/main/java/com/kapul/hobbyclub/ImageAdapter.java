package com.kapul.hobbyclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }




    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewCaption.setText(uploadCurrent.getCaption());
        holder.textViewExif.setText(uploadCurrent.getExif());
        holder.textViewDate.setText(uploadCurrent.getDate());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageViewUpload);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCaption, textViewExif, textViewDate;
        public ImageView imageViewUpload;


        public ImageViewHolder( View itemView) {
            super(itemView);

            textViewCaption = itemView.findViewById(R.id.tvCaption);
            textViewExif = itemView.findViewById(R.id.tvExif);
            textViewDate = itemView.findViewById(R.id.tvDate);
            imageViewUpload = itemView.findViewById(R.id.ivUpload);
        }
    }
}
