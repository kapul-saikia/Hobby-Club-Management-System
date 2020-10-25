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

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Art> mArts;

    public ArtAdapter(Context context, List<Art> uploads) {
        mContext = context;
        mArts = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.art_image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Art uploadCurrent = mArts.get(position);
        holder.tvCaptionArt.setText(uploadCurrent.getArtname());
        Picasso.with(mContext)
                .load(uploadCurrent.getArtimageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.ivArt);
    }

    @Override
    public int getItemCount() {
        return mArts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCaptionArt;
        public ImageView ivArt;


        public ImageViewHolder( View itemView) {
            super(itemView);

            tvCaptionArt = itemView.findViewById(R.id.tvCaptionArt);
            ivArt = itemView.findViewById(R.id.ivArt);
        }
    }
}
