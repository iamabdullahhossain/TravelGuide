package com.example.travelguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.RowAllCreatedPackagesBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.ShowPackagesModel;

import java.util.List;

public class AllCreatedPackagesAdapter extends RecyclerView.Adapter<AllCreatedPackagesAdapter.ViewHolder> {


    List<ShowPackagesModel> list;
    Context context;
    onClick onClick;

    public AllCreatedPackagesAdapter(List<ShowPackagesModel> list, Context context, AllCreatedPackagesAdapter.onClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowAllCreatedPackagesBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShowPackagesModel model = list.get(position);
        Glide.with(context).load(CONSTANTS.packageImageURL + model.getImageName()).placeholder(R.drawable.picture).into(holder.binding.vendorslogo);
        holder.binding.titleName.setText(model.getPackageName());
        holder.binding.body.setText(model.getArrangement());
        holder.itemView.setOnClickListener(view -> {
            onClick.onClick(model, list, position);
        });
        holder.binding.removeIconIV.setOnClickListener(view -> {
            onClick.onDelete(list, position, holder, model);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowAllCreatedPackagesBinding binding;

        public ViewHolder(@NonNull RowAllCreatedPackagesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClick {
        void onClick(ShowPackagesModel model, List<ShowPackagesModel> list, int position);
        void onDelete(List<ShowPackagesModel> list, int position, ViewHolder holder, ShowPackagesModel model);
    }

}
