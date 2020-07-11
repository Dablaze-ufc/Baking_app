package com.udacity.chukwuwauchenna.bakingapp.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.adapters.StepAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.ActivityDetailsBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;
import com.udacity.chukwuwauchenna.bakingapp.ui.steps.StepsDetailsActivity;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_KEY;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.isTablet;

/**
 * Created by ChukwuwaUchenna
 */

public class DetailsActivity extends AppCompatActivity implements StepAdapter.OnStepItemClickListener {

    private ActivityDetailsBinding binding;
    private DetailsActivityViewModel mViewModel;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        if (getIntent() != null) {
            Intent intent = getIntent();
            recipe = (Recipe) intent.getSerializableExtra(INTENT_KEY);
            Log.d("TAG", "onCreate: " + recipe.getSteps().toString());
            DetailsActivityViewModel.DetailsViewModelFactory factory = new DetailsActivityViewModel.DetailsViewModelFactory(this.getApplication(), recipe);
            mViewModel = new ViewModelProvider(this, factory).get(DetailsActivityViewModel.class);
            binding.setViewModel(mViewModel);
        }

        if (!isTablet(this)){
            if (recipe != null && getSupportActionBar() != null){
                getSupportActionBar().setTitle(recipe.getName());
            }
        }


        mViewModel.recipeLiveData().observe(this, recipeList ->{
         StepAdapter adapter = new StepAdapter(recipeList.getSteps(),this);
         binding.stepsRecyclerView.setAdapter(adapter);
        });

    }

    @Override
    public void onStepItemClicked(Step step) {
        Intent intent = new Intent(DetailsActivity.this, StepsDetailsActivity.class);
        intent.putExtra(INTENT_KEY, step);
        startActivity(intent);
    }
}