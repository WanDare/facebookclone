package com.example.mad_lab_testing.model;

import java.util.List;

public class PlaceAbout {

    private int id;

    private String name;

    private float rating;

    private int reviewCount;

    private String description;

    private String address;

    private List<Place> relatedPlaces;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Place> getRelatedPlaces() {
        return relatedPlaces;
    }

    public void setRelatedPlaces(List<Place> relatedPlaces) {
        this.relatedPlaces = relatedPlaces;
    }
}
