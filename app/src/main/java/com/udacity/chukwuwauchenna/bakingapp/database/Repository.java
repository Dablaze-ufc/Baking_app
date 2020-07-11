package com.udacity.chukwuwauchenna.bakingapp.database;

import android.app.Application;

import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientExecutors;
import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientRoomDatabase;
import com.udacity.chukwuwauchenna.bakingapp.database.local.IngredientsDAO;
import com.udacity.chukwuwauchenna.bakingapp.database.remote.RetrofitAPIService;
import com.udacity.chukwuwauchenna.bakingapp.database.remote.RetrofitClient;
import com.udacity.chukwuwauchenna.bakingapp.model.IngredientsForWidget;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;

public class Repository {
    private IngredientsDAO mDAO;
    private RetrofitAPIService mClient;

    public Repository(Application application){
        mDAO = IngredientRoomDatabase.getDatabase(application).mRecipeDao();
        mClient = RetrofitClient.getClient().create(RetrofitAPIService.class);

    }

    public Call<List<Recipe>> getRecipe(){
        return mClient.getRecipe();
    }

    public void insertIngredients(IngredientsForWidget widget){
        IngredientExecutors.getInstance().diskIO().execute(() ->{
            mDAO.insertIngredients(widget);
        });
    }
}
