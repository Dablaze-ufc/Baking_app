package com.udacity.chukwuwauchenna.bakingapp.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.udacity.chukwuwauchenna.bakingapp.database.Repository;

public class DetailsActivityViewModel extends AndroidViewModel {
    private Repository mRepo;
    public DetailsActivityViewModel(@NonNull Application application) {
        super(application);

        mRepo = new Repository(application);

    }

}
