package com.kapul.hobbyclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Notification> mNotifications;

    public NotificationAdapter(Context context, List<Notification> uploads) {
        mContext = context;
        mNotifications = uploads;
    }


    @NonNull
    @Override
    public NotificationAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
        return new NotificationAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ImageViewHolder holder, int position) {
        Notification uploadCurrent = mNotifications.get(position);
        holder.textViewText.setText(uploadCurrent.getText());
        holder.textViewDate.setText(uploadCurrent.getDate());
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewText, textViewDate;
        public ImageView imageViewCover;


        public ImageViewHolder( View itemView) {
            super(itemView);

            textViewText = itemView.findViewById(R.id.tvText);
            textViewDate = itemView.findViewById(R.id.tvDate);
        }
    }
}