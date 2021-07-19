package com.leftraru.melichallenge.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.repository.retrofit.model.ItemModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private List<ItemModel.Pictures> mImageIds;
    Context context;
    OnImageSelectListener onImageSelectListener;

    public ImageAdapter(Context context, List<ItemModel.Pictures> imagenes){
        this.context = context;
        this.mImageIds = imagenes;
        //this.onImageSelectListener = onImageSelectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_article_images, parent, false);
        context = parent.getContext();
        return new ViewHolder(view, onImageSelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.imageView.setImageResource(mImageIds.get(position));
        if(mImageIds.get(position) != null) {
            Glide.with(context).load(mImageIds.get(position).getUrl()).into(holder.imageView);
        }else
            holder.imageView.setImageResource(R.drawable.ic_emoji_picture);
       // holder.imageView.setClipToOutline(true);
        holder.tvQty.setText(String.valueOf(position + 1) + " / " + getItemCount());

    }

    @Override
    public int getItemCount() {
        return mImageIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvQty;
        OnImageSelectListener onImageSelectListener;

        public ViewHolder(@NonNull View itemView, OnImageSelectListener onImageSelectListener) {
            super(itemView);
            this.onImageSelectListener = onImageSelectListener;
            imageView = itemView.findViewById(R.id.myimage);
            tvQty = itemView.findViewById(R.id.tvQty);

            //imageView.setOnClickListener(this);
        }

    }

    public interface OnImageSelectListener{
        void onImageClick(int position);
    }
}

