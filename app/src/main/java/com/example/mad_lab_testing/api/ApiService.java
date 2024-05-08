package com.example.mad_lab_testing.api;

import com.example.mad_lab_testing.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("profile.json")
    Call<Profile> loadProfile();

}
