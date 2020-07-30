package com.rahul.farmerproject;
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
    private List<uploads> mUploads;

    public ImageAdapter(Context mContext, List<uploads> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        uploads uploadCurrent=mUploads.get(position);
        holder.name.setText(uploadCurrent.getName());
        holder.price.setText(uploadCurrent.getQuantity()+" per kg");
        //holder.imageView.setImageURI(uploadCurrent.getImagrurl());
        Picasso.with(mContext).load(uploadCurrent.getImagrurl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends  RecyclerView.ViewHolder{
        public TextView name,price;
        public ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.prod_name);
            imageView=itemView.findViewById(R.id.image_view_upload);
            price=itemView.findViewById(R.id.price);
        }
    }
}
