package com.example.maxfitness;

public class Food {
    private String date,meal,food;

    public String getDate() {
        return date;
    }

    public void setDate(String word) {
        this.date = date;
    }

    public String getFood(){
        return food;
    }

    public void setFood(String food){
        this.food=food;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Food(String date, String meal, String food){
        this.date=date;
        this.meal=meal;
        this.food=food;
    }
}
