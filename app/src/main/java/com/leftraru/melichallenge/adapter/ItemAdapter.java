package com.leftraru.melichallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.repository.retrofit.model.ItemsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ItemsModel> items;
    private Context context;
    private ItemListener itemListener;

    public ItemAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void addItems(List<ItemsModel> items, ItemListener itemListener){
        this.items = items;
        this.itemListener = itemListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        return new ViewHolder(view, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getTitle());
        holder.tvPrice.setText(items.get(position).getPriceFormated());
        holder.tvPriceDecimal.setText(items.get(position).getPriceDecimal());
        Glide
            .with(context)
            .load(items.get(position).getThumbnail())
            .placeholder(R.drawable.ic_emoji_picture)
            .into(holder.ivArticle);
        boolean freeShipping = items.get(position).getShipping().isFree_shipping();
        holder.tvFreeShipping.setVisibility((freeShipping) ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView  tvName, tvPrice, tvPriceDecimal, tvFreeShipping;
        public final ImageView ivArticle;
        ItemListener itemListener;

        public ViewHolder(@NonNull @NotNull View view, ItemListener itemListener) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            this.tvPriceDecimal = view.findViewById(R.id.tvPriceDecimal);
            this.ivArticle = view.findViewById(R.id.ivArticle);
            this.tvFreeShipping = view.findViewById(R.id.tvFreeShipping);

            this.itemListener = itemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.onItemClick(items.get(getAdapterPosition()).getId());
        }
    }

    public interface ItemListener {
        void onItemClick(String value);
    }
}
