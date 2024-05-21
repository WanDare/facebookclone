package com.example.mad_lab_testing.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.databinding.ViewHolderProvinceBinding;
import com.example.mad_lab_testing.model.Province;

public class ProvinceViewHolder extends RecyclerView.ViewHolder {

    private final ViewHolderProvinceBinding binding;

    public ProvinceViewHolder(ViewHolderProvinceBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void bind(Province province) {

        binding.txtName.setText(province.getName());

    }


}
