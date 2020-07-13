package com.udacity.chukwuwauchenna.bakingapp.database;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientExecutors;
import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientRoomDatabase;
import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientsDAO;
import com.udacity.chukwuwauchenna.bakingapp.database.remote.RetrofitAPIService;
import com.udacity.chukwuwauchenna.bakingapp.database.remote.RetrofitClient;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import java.util.List;
import retrofit2.Call;

public class Repository {
    private IngredientsDAO mDAO;
    private RetrofitAPIService mClient;


    public Repository(Context context){
        mDAO = IngredientRoomDatabase.getDatabase(context).mRecipeDao();
        mClient = RetrofitClient.getClient().create(RetrofitAPIService.class);

    }

    public Call<List<Recipe>> getRecipe(){
        return mClient.getRecipe();
    }

    public void insertRecipe(List<Recipe> recipeListForWidget){
        IngredientExecutors.getInstance().diskIO().execute(() -> mDAO.insertRecipe(recipeListForWidget));
    }

    public LiveData<List<Recipe>> recipeList() {
        return mDAO.getRecipe();
    }

}
