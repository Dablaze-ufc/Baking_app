package com.udacity.chukwuwauchenna.bakingapp.database.local;

import android.content.Context;

import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Created by ChukwuwaUchenna
 */
@Database(entities = {Recipe.class}, exportSchema = false, version = 1)
@TypeConverters({Converter.class, Converter.StepsConverter.class})
public abstract class IngredientRoomDatabase extends RoomDatabase {
    public abstract IngredientsDAO mRecipeDao();

    private static final Object LOCK = new Object();
    private static volatile IngredientRoomDatabase INSTANCE;

    public static IngredientRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            IngredientRoomDatabase.class,
                            "recipe-database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
