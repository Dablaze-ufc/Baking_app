package com.udacity.chukwuwauchenna.bakingapp.util;


import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.chukwuwauchenna.bakingapp.adapters.IngredientsAdapter;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by ChukwuwaUchenna
 */
public final class BindingAdapters {

    @BindingAdapter("loadIngredients")
    public static void loadIngredient(RecyclerView view, List<Ingredient> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        view.setAdapter(adapter);
        view.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
    }
}
