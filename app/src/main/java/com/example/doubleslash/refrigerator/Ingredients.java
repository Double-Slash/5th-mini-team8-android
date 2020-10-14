package com.example.doubleslash.refrigerator;
//디비이름 이상해서 하나 더만든클래스
public class Ingredients {
    private String ingredient_name;

    public Ingredients(String ingredient_name){
        this.ingredient_name = ingredient_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }
}
