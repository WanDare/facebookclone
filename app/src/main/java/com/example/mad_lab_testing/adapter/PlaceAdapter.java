package com.example.mad_lab_testing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.databinding.ViewHolderPlaceBinding;
import com.example.mad_lab_testing.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private List<Place> dataSet;

    public void setDataSet(List<Place> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolderPlaceBinding binding = ViewHolderPlaceBinding.inflate(inflater, parent, false);
        return new PlaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = dataSet.get(position);
        holder.bind(place);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null) {
            return 0;
        } else {
            return dataSet.size();
        }
    }
}
