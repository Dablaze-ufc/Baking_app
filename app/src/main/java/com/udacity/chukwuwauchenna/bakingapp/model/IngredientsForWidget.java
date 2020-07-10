package com.udacity.chukwuwauchenna.bakingapp.model;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by chukwuwaUchenna
 */
@Entity(tableName = "ingredient_for_widget")
public class IngredientsForWidget {

    @PrimaryKey
    private long id = 0;
    private List<String> ingredients;

    public IngredientsForWidget(long id, List<String> ingredients) {
        this.id = id;
        this.ingredients = ingredients;
    }

    @Ignore
    public IngredientsForWidget() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
