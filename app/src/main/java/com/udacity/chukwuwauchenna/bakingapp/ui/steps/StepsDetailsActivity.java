package com.udacity.chukwuwauchenna.bakingapp.ui.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.databinding.ActivityStepsDetailsBinding;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.INTENT_KEY;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.USER_AGENT;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.currentWindow;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.playWhenReady;
import static com.udacity.chukwuwauchenna.bakingapp.util.Constants.playbackPosition;

public class StepsDetailsActivity extends AppCompatActivity {
    private ActivityStepsDetailsBinding binding;
    private SimpleExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_steps_details);
        if (getIntent() != null) {
            Step step = (Step) getIntent().getSerializableExtra(INTENT_KEY);
            StepsDetailsViewModel.StepsDetailsViewModelFactory factory = new StepsDetailsViewModel.StepsDetailsViewModelFactory(step);
            StepsDetailsViewModel viewModel = new ViewModelProvider(this, factory).get(StepsDetailsViewModel.class);
            binding.setViewModel(viewModel);
            setLandscapeOrPortrait(viewModel.mSteps);
            initializePlayer();

        }
    }

        private void setLandscapeOrPortrait (Step step) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !step.getVideoURL().equals("")) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) binding.videoView.getLayoutParams();
                params.width = FrameLayout.LayoutParams.MATCH_PARENT;
                params.height = FrameLayout.LayoutParams.MATCH_PARENT;
                binding.videoView.setLayoutParams(params);
                binding.recipeDescriptionTextView.setVisibility(View.GONE);
            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) binding.videoView.getLayoutParams();
                params.width = FrameLayout.LayoutParams.MATCH_PARENT;
                params.height = FrameLayout.LayoutParams.WRAP_CONTENT;
                binding.videoView.setLayoutParams(params);
            }
        }



    private void initializePlayer() {
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        binding.videoView.setPlayer(exoPlayer);
        Step step = (Step) getIntent().getSerializableExtra(INTENT_KEY);
        assert step != null;
        Uri uri = Uri.parse(step.getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentWindow, playbackPosition);
        exoPlayer.prepare(mediaSource, false, false);
    }
    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, USER_AGENT);
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }
    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}


