package com.example.mad_lab_testing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.databinding.ViewHolderProvinceBinding;
import com.example.mad_lab_testing.model.Province;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceViewHolder> {

    private List<Province> provinces;

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolderProvinceBinding binding = ViewHolderProvinceBinding.inflate(inflater, parent, false);
        return new ProvinceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {
        Province province = provinces.get(position);
        holder.bind(province);
    }

    @Override
    public int getItemCount() {
       if (provinces == null ) {
           return 0;
       } else {
           return provinces.size();
       }
    }
}
