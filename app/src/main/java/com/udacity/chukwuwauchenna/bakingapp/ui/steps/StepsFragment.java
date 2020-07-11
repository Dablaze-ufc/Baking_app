package com.udacity.chukwuwauchenna.bakingapp.ui.steps;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.udacity.chukwuwauchenna.bakingapp.adapters.StepAdapter;
import com.udacity.chukwuwauchenna.bakingapp.databinding.FragmentStepsBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;
import com.udacity.chukwuwauchenna.bakingapp.ui.SharedViewModel;
import org.jetbrains.annotations.NotNull;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.USER_AGENT;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.currentWindow;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.playWhenReady;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.playbackPosition;

/**
 * created by ChukwuwaUchenna
 */
public class StepsFragment extends Fragment implements StepAdapter.OnStepItemClickListener {

    private FragmentStepsBinding binding;
    private SharedViewModel viewModel;
    private SimpleExoPlayer exoPlayer;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentStepsBinding.inflate(inflater);
       viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        initializePlayer();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getStepMutableLiveData().observe(getViewLifecycleOwner(), step -> {
            binding.setSteps(step);
            if (step.getVideoURL().equals("")) {
                binding.noVideoImageView.setVisibility(View.VISIBLE);
                binding.videoView.setVisibility(View.GONE);
            } else {
                binding.noVideoImageView.setVisibility(View.INVISIBLE);
                binding.videoView.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    public void onStepItemClicked(Step step) {

    }

    private void initializePlayer() {
        exoPlayer = new SimpleExoPlayer.Builder(requireContext()).build();
        binding.videoView.setPlayer(exoPlayer);
        viewModel.getStepMutableLiveData().observe(getViewLifecycleOwner(), step -> {
            Uri uri = Uri.parse(step.getVideoURL());
            MediaSource mediaSource = buildMediaSource(uri);
            exoPlayer.setPlayWhenReady(playWhenReady);
            exoPlayer.seekTo(currentWindow, playbackPosition);
            exoPlayer.prepare(mediaSource, false, false);
        });
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(requireContext(), USER_AGENT);
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }
}