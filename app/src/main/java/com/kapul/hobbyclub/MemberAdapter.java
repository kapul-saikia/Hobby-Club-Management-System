package com.kapul.hobbyclub;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Member> mMembers;

    public MemberAdapter(Context context, List<Member> uploads) {
        mContext = context;
        mMembers = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.member_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Member uploadCurrent = mMembers.get(position);
        holder.tvName.setText(uploadCurrent.getName());
        holder.tvEmail.setText(uploadCurrent.getEmail());
        holder.tvDept.setText(uploadCurrent.getDept());
        holder.tvRoll.setText(uploadCurrent.getRoll());
        holder.tvPhone.setText(uploadCurrent.getPhone());

    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvEmail, tvDept, tvRoll, tvPhone;


        public ImageViewHolder( View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvMemberName);
            tvEmail = itemView.findViewById(R.id.tvMemberEmail);
            tvDept = itemView.findViewById(R.id.tvMemberDept);
            tvRoll = itemView.findViewById(R.id.tvMemberRoll);
            tvPhone = itemView.findViewById(R.id.tvMemberPhone);
        }
    }
}
