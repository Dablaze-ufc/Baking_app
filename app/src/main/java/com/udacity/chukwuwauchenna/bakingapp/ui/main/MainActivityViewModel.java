package com.udacity.chukwuwauchenna.bakingapp.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.udacity.chukwuwauchenna.bakingapp.database.Repository;
import com.udacity.chukwuwauchenna.bakingapp.model.Recipe;
import com.udacity.chukwuwauchenna.bakingapp.util.State;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ChukwuwaUchenna
 */

public class MainActivityViewModel extends AndroidViewModel {

    private Repository mRepo;
    private MutableLiveData<List<Recipe>> _recipeList = new MutableLiveData<>();
    private MutableLiveData<String> _message = new MutableLiveData<>();
    private MutableLiveData<State> _state = new MutableLiveData<>();
    public LiveData<List<Recipe>> recipeList = _recipeList;
    public LiveData<String> message = _message;
    public LiveData<State> state = _state;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repository(application);
        getRecipe();

    }

    private void saveIngredients(List<Recipe> mRecipe) {
        mRepo.insertIngredients(mRecipe);
    }


    private void getRecipe() {
            _state.setValue(State.LOADING);

        mRepo.getRecipe().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NotNull Call<List<Recipe>> call, @NotNull Response<List<Recipe>> response) {
                    _recipeList.setValue(response.body());
                    _state.setValue(State.SUCCESS);
                    _message.setValue("Success");
                    saveIngredients(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Recipe>> call, @NotNull Throwable t) {
                _state.setValue(State.ERROR);
                _message.setValue(t.getLocalizedMessage());
            }
        });

    }

//    private boolean isMainThread() {
//        return Looper.myLooper() == Looper.getMainLooper();
//    }

}