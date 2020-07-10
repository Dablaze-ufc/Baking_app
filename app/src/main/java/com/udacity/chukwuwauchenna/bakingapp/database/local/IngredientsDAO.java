package com.udacity.chukwuwauchenna.bakingapp.database.local;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.udacity.chukwuwauchenna.bakingapp.model.IngredientsForWidget;

/**
 * Created by ChukwuwaUchenna
 */

@Dao
public interface IngredientsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredients(IngredientsForWidget ingredients);

    @Query("SELECT * FROM ingredient_for_widget")
    IngredientsForWidget getIngredients();
}
