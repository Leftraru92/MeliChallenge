package com.leftraru.melichallenge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.repository.database.model.ItemEntity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<ItemEntity> mValues;
    private final SeachListener seachListener;

    public SearchAdapter(List<ItemEntity> items, SeachListener seachListener) {
        mValues = items;
        this.seachListener = seachListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_previous_search, parent, false);
        return new ViewHolder(view, seachListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvText.setText(mValues.get(position).searchKey);
    }

    @Override
    public int getItemCount() {
        if (mValues == null)
            return 0;
        else
            return mValues.size();
    }

    public void setItems(List<ItemEntity> previusSearch) {
        this.mValues = previusSearch;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView tvText;
        SeachListener seachListener;

        public ViewHolder(View view, SeachListener seachListener) {
            super(view);
            mView = view;
            tvText = (TextView) view.findViewById(R.id.tvText);
            this.seachListener = seachListener;

            if (seachListener != null) {
                mView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            seachListener.onSearchClick(mValues.get(getAdapterPosition()).searchKey);
        }
    }

    public interface SeachListener {
        void onSearchClick(String value);
    }

}