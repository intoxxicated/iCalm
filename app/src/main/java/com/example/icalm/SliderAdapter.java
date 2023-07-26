package com.example.icalm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private int[] layoutResources = {R.layout.slider1, R.layout.slider2};

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResources[viewType], parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return layoutResources.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {

        SliderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
