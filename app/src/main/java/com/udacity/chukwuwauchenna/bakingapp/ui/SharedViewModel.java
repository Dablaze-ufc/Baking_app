package com.udacity.chukwuwauchenna.bakingapp.ui;


import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

import static android.content.Context.MODE_PRIVATE;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.ID_PREF;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.NAME_PREF;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.WIDGET_PREF;

public class SharedViewModel extends AndroidViewModel {

    private MutableLiveData<Recipe> _reciepe = new MutableLiveData<>();
    private MutableLiveData<Step> _steps = new MutableLiveData<>();
    private Application application;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
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

    public void addToPrefsForWidget(Recipe recipe) {
        SharedPreferences preferences = application.getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID_PREF, recipe.getId());
        editor.putString(NAME_PREF, recipe.getName());
        editor.apply();
    }

    public void setStepMutableLiveData(Step step) {
        _steps.setValue(step);
    }
}



