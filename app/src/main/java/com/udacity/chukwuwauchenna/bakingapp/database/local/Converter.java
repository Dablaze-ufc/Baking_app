package com.udacity.chukwuwauchenna.bakingapp.database.local;


import java.lang.reflect.Type;
import java.util.List;
import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

/**
 * Created by ChekwuwaUchenna
 */

final class Converter {

    @TypeConverter
    public String convertListToString(List<Ingredient> ingredients) {
        return new Gson().toJson(ingredients);
    }

    @TypeConverter
    public List<Ingredient> convertStringToList(String item) {
        Type itemType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(item, itemType);
    }
}
