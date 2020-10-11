package com.example.doubleslash.refrigerator;


class Ingredient{
    private String ingredients_name;
//    private String thumbnail;

    public Ingredient(String ingredients_name, String thumbnail){
        this.ingredients_name = ingredients_name;
//        this.thumbnail = thumbnail;
    }

    public String getIngredients_name() {
        return ingredients_name;
    }

    public void setIngredients_name(String ingredients_name) {
        this.ingredients_name = ingredients_name;
    }

//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }
}

class RequestUser{
    private String userId;
    public RequestUser(String userId){
        this.userId = userId;
    }
}