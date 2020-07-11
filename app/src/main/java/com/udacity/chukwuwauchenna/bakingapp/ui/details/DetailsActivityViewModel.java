package com.udacity.chukwuwauchenna.bakingapp.ui.details;

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

import java.util.ArrayList;
import java.util.List;

public class DetailsActivityViewModel extends AndroidViewModel {
    private Repository mRepo;
    public Recipe mRecipe;
    private MutableLiveData<Recipe> _reciepe = new MutableLiveData<>();
    public DetailsActivityViewModel(@NonNull Application application, Recipe recipe) {
        super(application);
        mRepo = new Repository(application);
        this.mRecipe = recipe;
//        saveIngredients();
        }

        private void saveIngredients(){
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
    public LiveData<Recipe> recipeLiveData(){
        _reciepe.setValue(mRecipe);
        return _reciepe;
    }

    static class DetailsViewModelFactory implements ViewModelProvider.Factory {
        private Application app;
        private Recipe recipe;
        public DetailsViewModelFactory(@NonNull Application application, Recipe recipe){
            this.app = application;
            this.recipe = recipe;

        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.equals(DetailsActivityViewModel.class))
                return (T) new DetailsActivityViewModel(app, recipe);
            else {
           return null;
        }
        }
    }
}
