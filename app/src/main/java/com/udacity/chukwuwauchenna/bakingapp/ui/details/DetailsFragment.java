package com.udacity.chukwuwauchenna.bakingapp.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.chukwuwauchenna.bakingapp.adapters.IngredientsAdapter;
import com.udacity.chukwuwauchenna.bakingapp.adapters.StepAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.FragmentDetailsBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;
import com.udacity.chukwuwauchenna.bakingapp.ui.SharedViewModel;

/**
 * Created by ChukwuwaUchenna
 */
public class DetailsFragment extends Fragment implements StepAdapter.OnStepItemClickListener {
    private FragmentDetailsBinding binding;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater);
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getRecipeMutableLiveData().observe(getViewLifecycleOwner(),recipe -> {
            binding.setRecipe(recipe);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(recipe.getName());
            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients());
            Log.d("TAG", "onViewCreated: " + recipe.getIngredients());
            binding.detailsRecyclerView.setAdapter(ingredientsAdapter);
            binding.detailsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.detailsRecyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL));

            StepAdapter stepAdapter = new StepAdapter(recipe.getSteps(), this);
            binding.stepsRecyclerView.setAdapter(stepAdapter);
            binding.stepsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.stepsRecyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));

        });
    }

    @Override
    public void onStepItemClicked(Step step) {
        viewModel.setStepMutableLiveData(step);
    }
}