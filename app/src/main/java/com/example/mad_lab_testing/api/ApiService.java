package com.example.mad_lab_testing.api;

import com.example.mad_lab_testing.model.PlaceAbout;
import com.example.mad_lab_testing.model.Profile;
import com.example.mad_lab_testing.model.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("profile.json")
    Call<Profile> loadProfile();

    @GET("provinces.json")
    Call<List<Province>> loadProvinceList();

    @GET("place-about.json")
    Call<PlaceAbout> loadPlaceAbout();

    @GET("place-photos.json")
    Call<List<String>> loadPlacePhotos();

}
