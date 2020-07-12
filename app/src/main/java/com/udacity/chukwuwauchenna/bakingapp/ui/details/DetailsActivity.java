package com.udacity.chukwuwauchenna.bakingapp.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.adapters.StepAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.ActivityDetailsBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;
import com.udacity.chukwuwauchenna.bakingapp.ui.SharedViewModel;
import com.udacity.chukwuwauchenna.bakingapp.ui.steps.StepsDetailsActivity;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_KEY;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.isTablet;

/**
 * Created by ChukwuwaUchenna
 */

public class DetailsActivity extends AppCompatActivity implements StepAdapter.OnStepItemClickListener {

    private ActivityDetailsBinding binding;
    private SharedViewModel mViewModel;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        if (getIntent() != null) {
            Intent intent = getIntent();
            recipe = (Recipe) intent.getSerializableExtra(INTENT_KEY);

             mViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
            mViewModel.setRecipeMutableLiveData(recipe);
            mViewModel.saveIngredients(recipe);
            binding.setRecipe(recipe);
        }

        if (!isTablet(this)){
            if (recipe != null && getSupportActionBar() != null){
                getSupportActionBar().setTitle(recipe.getName());
                mViewModel.getRecipeMutableLiveData().observe(this, recipeList ->{
                    StepAdapter adapter = new StepAdapter(recipeList.getSteps(),this);
                    assert binding.stepsRecyclerView != null;
                    binding.stepsRecyclerView.setAdapter(adapter);
                });
            }
        }




    }

    @Override
    public void onStepItemClicked(Step step) {
        Intent intent = new Intent(DetailsActivity.this, StepsDetailsActivity.class);
        intent.putExtra(INTENT_KEY, step);
        startActivity(intent);
    }
}