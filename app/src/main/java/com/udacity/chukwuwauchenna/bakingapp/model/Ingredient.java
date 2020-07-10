package com.udacity.chukwuwauchenna.bakingapp.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by chukwuwaUchenna
 */
public class Ingredient implements Serializable {

    @Json(name = "quantity")
    private double quantity;

    @Json(name = "measure")
    private String measure;

    @Json(name = "ingredient")
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
