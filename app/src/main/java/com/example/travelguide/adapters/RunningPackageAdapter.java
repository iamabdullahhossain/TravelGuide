package com.example.travelguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguide.databinding.RowRunningpackageLayoutBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.RunningPackageModel;

import java.util.List;

public class RunningPackageAdapter extends RecyclerView.Adapter<RunningPackageAdapter.ViewHolder> {

    List<RunningPackageModel> list;
    Context context;

    public RunningPackageAdapter(List<RunningPackageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowRunningpackageLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RunningPackageModel model = list.get(position);
        Glide.with(context).load(CONSTANTS.packageImageURL + model.getImage()).into(holder.binding.vendorslogo);
        holder.binding.customerNameTV.setText(model.getPackageName());
        holder.binding.descriptionTV.setText(model.getArrangement());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RowRunningpackageLayoutBinding binding;

        public ViewHolder(@NonNull RowRunningpackageLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
