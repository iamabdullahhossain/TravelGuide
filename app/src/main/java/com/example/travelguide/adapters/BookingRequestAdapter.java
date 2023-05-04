package com.example.travelguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.RowBookingRequestBinding;
import com.example.travelguide.models.BookingModel;
import com.example.travelguide.models.CONSTANTS;

import java.util.List;

public class BookingRequestAdapter extends RecyclerView.Adapter<BookingRequestAdapter.ViewHolder> {

    List<BookingModel> list;
    Context context;
    onClick onClick;

    public BookingRequestAdapter(List<BookingModel> list, Context context, BookingRequestAdapter.onClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowBookingRequestBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BookingModel model = list.get(position);
        Glide.with(context).load(CONSTANTS.packageImageURL + model.getImageName()).placeholder(R.drawable.picture).into(holder.binding.vendorslogo);
        holder.binding.customerNameTV.setText(model.getName());
        holder.binding.descriptionTV.setText(model.getName() + " wants to book a package named '" + model.getPackageName() + "' on " + model.getPrice() + " taka");
        holder.itemView.setOnClickListener(view -> {
            onClick.onClick(model);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowBookingRequestBinding binding;

        public ViewHolder(@NonNull RowBookingRequestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public interface onClick {
        void onClick(BookingModel model);
    }

}
