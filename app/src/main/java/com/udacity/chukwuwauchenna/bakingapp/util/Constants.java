package com.udacity.chukwuwauchenna.bakingapp.util;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import com.google.android.exoplayer2.ui.PlayerView;
import java.io.IOException;

public class Constants {
    public final static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public final static String INTENT_KEY = "com.udacity.chukwuwauchenna.bakingapp_MAIN_INTENT_KEY";
    public final static String INTENT_KEY_RECIPE_ID = "com.udacity.chukwuwauchenna.bakingapp_recipe_id";
    public final static String INTENT_ACTION_KEY = "intent_action_key";
    public final static String WIDGET_INTENT_KEY = "android.appwidget.action.APPWIDGET_UPDATE2";
    public final static String INTENT_FROM_ACTIVITY_INGREDIENTS_LIST = "INTENT_FROM_ACTIVITY_INGREDIENTS_LIST";
    public final static String RECIPE_IMAGE_SRC = "https://unsplash.com/@brookelark";
    public final static String USER_AGENT = "bakingtime";
    public static final String INTENT_EXTRA = "recipe";
    public static final String WIDGET_PREF = "widget_prefs";
    public static final String ID_PREF = "id";
    public static final String NAME_PREF = "name";

    public static boolean playWhenReady = true;
    public static int currentWindow = 0;
    public static long playbackPosition = 0;

    public static void hideSystemUi(PlayerView playerView) {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public static boolean isConnectionAvailable() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = process.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }
}
