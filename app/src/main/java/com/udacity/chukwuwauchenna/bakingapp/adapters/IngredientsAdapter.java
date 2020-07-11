package com.udacity.chukwuwauchenna.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;

/**
 * Created by ChukwuwaUchenna
 */
public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> ingredients;

    public IngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Ingredient ingredient) {
            TextView ingredientText = itemView.findViewById(R.id.text_ingredient);
            TextView quantityText = itemView.findViewById(R.id.text_quantity);
            TextView measureText = itemView.findViewById(R.id.text_measure);

            ingredientText.setText(ingredient.getIngredient());
            quantityText.setText(String.valueOf(ingredient.getQuantity()));
            measureText.setText(ingredient.getMeasure());
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_ingredient_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        if (ingredients == null) {
            return 0;
        } else
            return ingredients.size();
    }
}