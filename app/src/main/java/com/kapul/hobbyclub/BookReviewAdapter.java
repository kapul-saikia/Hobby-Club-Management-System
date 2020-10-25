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

public class BookReviewAdapter extends RecyclerView.Adapter<BookReviewAdapter.ImageViewHolder> {

    private Context mContext;
    private List<BookReview> mBookReviews;

    public BookReviewAdapter(Context context, List<BookReview> uploads) {
        mContext = context;
        mBookReviews = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        BookReview uploadCurrent = mBookReviews.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewReview.setText(uploadCurrent.getReview());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageViewCover);
    }

    @Override
    public int getItemCount() {
        return mBookReviews.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName, textViewReview;
        public ImageView imageViewCover;


        public ImageViewHolder( View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tvNameBook);
            textViewReview = itemView.findViewById(R.id.tvReviewBook);
            imageViewCover = itemView.findViewById(R.id.ivCoverBook);
        }
    }
}
