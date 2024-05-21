package com.example.mad_lab_testing.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.databinding.ViewHolderPhotoBinding;
import com.squareup.picasso.Picasso;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private ViewHolderPhotoBinding binding;

    public PhotoViewHolder(ViewHolderPhotoBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bind(String imageUrl) {
        Picasso.get().load(imageUrl).into(binding.imgPlace);
    }
}
