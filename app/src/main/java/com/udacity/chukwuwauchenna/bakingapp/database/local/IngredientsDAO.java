package com.udacity.chukwuwauchenna.bakingapp.database.local;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;

import java.util.List;

/**
 * Created by ChukwuwaUchenna
 */

@Dao
public interface IngredientsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(List<Recipe> recipe);

    @Query("SELECT * FROM recipe_table")
    List<Recipe> getRecipe();
}
