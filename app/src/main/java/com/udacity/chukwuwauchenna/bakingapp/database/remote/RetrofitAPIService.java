package com.udacity.chukwuwauchenna.bakingapp.database.remote;


import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ChukwuwaUchenna
 */

public interface RetrofitAPIService {
    @GET("baking.json")
    Call<List<Recipe>> getRecipe();
}
