package com.example.mad_lab_testing.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.databinding.ViewHolderPlaceBinding;
import com.example.mad_lab_testing.model.Place;
import com.squareup.picasso.Picasso;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    private final ViewHolderPlaceBinding binding;

    public PlaceViewHolder(ViewHolderPlaceBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bind(Place place) {
        Picasso.get().load(place.getImageUrl()).into(binding.imgThumbnail);
        binding.txtName.setText(place.getName());
    }

}
