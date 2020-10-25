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

public class FilmReviewAdapter extends RecyclerView.Adapter<FilmReviewAdapter.ImageViewHolder> {

    private Context mContext;
    private List<FilmReview> mFilmReviews;

    public FilmReviewAdapter(Context context, List<FilmReview> uploads) {
        mContext = context;
        mFilmReviews = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.film_image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        FilmReview uploadCurrent = mFilmReviews.get(position);
        holder.tvfName.setText(uploadCurrent.getFilmname());
        holder.tvfReview.setText(uploadCurrent.getFilmreview());
        Picasso.with(mContext)
                .load(uploadCurrent.getFilmimageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.ivfCover);
    }

    @Override
    public int getItemCount() {
        return mFilmReviews.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView tvfName, tvfReview;
        public ImageView ivfCover;


        public ImageViewHolder( View itemView) {
            super(itemView);

            tvfName = itemView.findViewById(R.id.tvNameFilm);
            tvfReview= itemView.findViewById(R.id.tvReviewFilm);
            ivfCover = itemView.findViewById(R.id.ivCoverFilm);
        }
    }
}
