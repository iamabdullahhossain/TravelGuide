package com.example.travelguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.RowDestinationsLayoutBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.ShowPackagesModel;

import java.util.List;

public class ShowPackagesAdapter extends RecyclerView.Adapter<ShowPackagesAdapter.ViewHolder> {

    List<ShowPackagesModel> list;
    Context context;

    OnClick onClick;

    public ShowPackagesAdapter(List<ShowPackagesModel> list, Context context, OnClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowDestinationsLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShowPackagesModel model = list.get(position);
        Glide.with(context).load(CONSTANTS.packageImageURL + model.getImageName()).placeholder(R.drawable.picture).into(holder.binding.destinationImageIV);
        holder.binding.destinationNameTV.setText(model.getPackageName());
        holder.itemView.setOnClickListener(view -> {
            onClick.onClick(model);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowDestinationsLayoutBinding binding;

        public ViewHolder(@NonNull RowDestinationsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClick {
        void onClick(ShowPackagesModel model);
    }
}
