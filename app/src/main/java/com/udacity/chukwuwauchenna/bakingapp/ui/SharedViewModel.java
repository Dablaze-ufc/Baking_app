package com.udacity.chukwuwauchenna.bakingapp.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.udacity.chukwuwauchenna.bakingapp.database.Repository;
import com.udacity.chukwuwauchenna.bakingapp.model.Ingredient;
import com.udacity.chukwuwauchenna.bakingapp.model.IngredientsForWidget;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends AndroidViewModel {
    private Repository mRepo;
    private MutableLiveData<Recipe> _reciepe = new MutableLiveData<>();
    private MutableLiveData<Step> _steps = new MutableLiveData<>();
    public SharedViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repository(application);

        }

    public LiveData<Recipe> getRecipeMutableLiveData() {
        return _reciepe;
    }

    public void setRecipeMutableLiveData(Recipe recipe) {
        _reciepe.setValue(recipe);
    }

    public LiveData<Step> getStepMutableLiveData() {
        return _steps;
    }

    public void setStepMutableLiveData(Step step) {
        _steps.setValue(step);
    }

        public void saveIngredients(Recipe mRecipe){
            List<Ingredient> ingredients = mRecipe.getIngredients();
            List<String> ingredientsForWidget = new ArrayList<>();
            for (Ingredient a : ingredients) {
                ingredientsForWidget.add(a.getIngredient() + "\n" +
                        "Quantity: " + a.getQuantity() + "\n" +
                        "Measure: " + a.getMeasure() + "\n");
                IngredientsForWidget forWidget = new IngredientsForWidget();
                forWidget.setIngredients(ingredientsForWidget);
                mRepo.insertIngredients(forWidget);
        }
    }
//    public LiveData<Recipe> recipeLiveData(){
//        _reciepe.setValue(mRecipe);
//        return _reciepe;
//    }


}
