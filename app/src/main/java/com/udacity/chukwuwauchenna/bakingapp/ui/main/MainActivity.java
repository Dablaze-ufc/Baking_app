package com.udacity.chukwuwauchenna.bakingapp.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.test.espresso.IdlingResource;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.adapters.RecipeAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.ActivityMainBinding;
import com.udacity.chukwuwauchenna.bakingapp.idleresource.SimpleIdlingResource;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.ui.details.DetailsActivity;
import com.udacity.chukwuwauchenna.bakingapp.util.State;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_KEY;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.isTablet;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {

    private ActivityMainBinding binding;

    @Nullable
    private SimpleIdlingResource mIdlingResource = new SimpleIdlingResource();

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        if (mIdlingResource != null){
            mIdlingResource.setIdleState(false);
        }

        viewModel.recipeList.observe(this, recipes -> {

//            Log.d("TAG", "onCreateMainActivity: " + recipes.get(0).getIngredients().get(0).getIngredient());
            if(recipes != null){
            RecipeAdapter adapter = new RecipeAdapter(recipes, this);
            binding.recipeRecyclerView.setAdapter(adapter);

            }
        });

        viewModel.state.observe(this, state -> {

//            if (mIdlingResource != null && state == State.SUCCESS){
//                mIdlingResource.setIdleState(true);
//            }

            switch (state){
                case ERROR:
                    binding.progBar.setVisibility(View.GONE);
                case LOADING:
                    binding.progBar.setVisibility(View.VISIBLE);
                    if (mIdlingResource != null){
                    mIdlingResource.setIdleState(false);}
                case SUCCESS:
                    binding.progBar.setVisibility(View.GONE);

            }
        });



        if (isTablet(MainActivity.this)) {
            binding.recipeRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
//            binding.recipeRecyclerView.addItemDecoration(new MarginItemDecorationTablet(16));
        } else {
            binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//            mBinding.recipeRecyclerView.addItemDecoration(new MarginItemDecoration(16));

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {


            mIdlingResource.setIdleState(true);

        return super.onCreateView(name, context, attrs);

    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(INTENT_KEY, recipe);
        startActivity(intent);
    }
}