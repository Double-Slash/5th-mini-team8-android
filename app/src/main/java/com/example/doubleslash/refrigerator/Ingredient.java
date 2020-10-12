package com.example.doubleslash.refrigerator;

public class Ingredient {
    private String ingredients_name;

    public Ingredient(String ingredients_name){
        this.ingredients_name = ingredients_name;
    }

    public String getIngredients_name() {
        return ingredients_name;
    }

    public void setIngredients_name(String ingredients_name) {
        this.ingredients_name = ingredients_name;
    }
}
