package com.udacity.chukwuwauchenna.bakingapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.adapters.RecipeAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.ActivityMainBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.ui.details.DetailsActivity;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_KEY;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.recipeList.observe(this, recipes -> {
            RecipeAdapter adapter = new RecipeAdapter(recipes, this);
            binding.recipeRecyclerView.setAdapter(adapter);
        });

        viewModel.state.observe(this, state -> {
            switch (state){
                case ERROR:
                    binding.progBar.setVisibility(View.GONE);
                case LOADING:
                    binding.progBar.setVisibility(View.VISIBLE);
                case SUCCESS:
                    binding.progBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(INTENT_KEY, recipe);
        startActivity(intent);
    }
}