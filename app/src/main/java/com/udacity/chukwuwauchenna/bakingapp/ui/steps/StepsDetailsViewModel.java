package com.udacity.chukwuwauchenna.bakingapp.ui.steps;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

public class StepsDetailsViewModel extends ViewModel {
    public Step mSteps;
    public StepsDetailsViewModel(Step step){
        this.mSteps = step;
    }

    static class StepsDetailsViewModelFactory implements ViewModelProvider.Factory {
        private Step step;
        public StepsDetailsViewModelFactory(Step step){
            this.step = step;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.equals(StepsDetailsViewModel.class))
                return (T) new StepsDetailsViewModel(step);
            else {
                return null;
            }
        }
    }
}

