package com.example.maxfitness;

import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foodList = new ArrayList<>();

    public FoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public FoodList() {
    }

    public ArrayList<Food> getWordList() {
        return foodList;
    }

    public void setWordList(ArrayList<Food> denemeArrayList) {
        this.foodList = denemeArrayList;
    }
}
