package com.udacity.chukwuwauchenna.bakingapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chukwuwaUchenna
 */

@Entity(tableName = "recipe_table")
public class Recipe implements Serializable {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;

    @Json(name = "name")
    private String name;

    @Json(name = "ingredients")
    private List<Ingredient> ingredients;

    @Json(name = "steps")
    private List<Step> steps;

    @Json(name = "servings")
    private Integer servings;

    @Json(name = "image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
