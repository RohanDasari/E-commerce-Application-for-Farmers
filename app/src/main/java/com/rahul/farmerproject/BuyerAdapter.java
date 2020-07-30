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

public class BuyerAdapter extends RecyclerView.Adapter<BuyerAdapter.BuyerViewHolder>{

    private Context mContext;
    private List<product> mUploads;
    private OnItemClickListner mListener;

    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListner listner){
        mListener=listner;
    }

    public BuyerAdapter(Context Context, List<product> Uploads){
        mContext=Context;
        mUploads=Uploads;
    }
    @NonNull
    @Override
    public BuyerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new BuyerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerViewHolder holder, int position) {
        product uploadCurrent=mUploads.get(position);
        holder.name.setText(uploadCurrent.getName());
        holder.price.setText(uploadCurrent.getQuantity()+" per kg");
        //holder.NAME.setText("Name :"+uploadCurrent.getFname()+" "+uploadCurrent.getLname());
        //holder.PH.setText("Phone :"+uploadCurrent.getPhone());
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

    public class BuyerViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price,NAME,PH;
        public ImageView imageView;
        public BuyerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.prod_name);
            imageView=itemView.findViewById(R.id.image_view_upload);
            price=itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            });
        }

    }
}
